package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.dto.FoodRequest;
import com.mycompany.spring_mvc_project_final.dto.IngredientRequest;
import com.mycompany.spring_mvc_project_final.dto.LabelRequest;
import com.mycompany.spring_mvc_project_final.entities.FoodEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodDetailsController {
    @Autowired
    TodayDietService todayDietService;
    @Autowired
    CreateFoodService createFoodService;
    @Autowired
    DetailsService detailsService;
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String foodDetailsPage(@PathVariable long id, Model model, HttpSession session) {
        FoodEntity food = detailsService.findById(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("food", food);
        return "foodDetails";
    }

    @RequestMapping(value = "/deleteFood/{id}", method = RequestMethod.GET)
    public String deleteFood(@PathVariable(value = "id") long id) {
        todayDietService.deleteFoodFromTodayDietByAdmin(id);
        detailsService.deleteFoodById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateFood/{id}", method = RequestMethod.GET)
    public String updateFood(@PathVariable(value = "id") long id, Model model) {
        FoodEntity foodEntity = detailsService.findById(id);
        model.addAttribute("id", foodEntity.getId());
        List<String> allLabels = createFoodService.getAllLabelName();
        FoodRequest food = new FoodRequest();
        food.setIngredientRequests(new ArrayList<>());
        food.setLabelRequests(new ArrayList<>());
        detailsService.foodMapperResponse(foodEntity, food);
        model.addAttribute("food", food);
        model.addAttribute("allLabels", allLabels);
        return "update";
    }

    @RequestMapping(value = "/updateFood/update-process", method = RequestMethod.POST)
    public String submit(@ModelAttribute(value = "food") FoodRequest food,
                         BindingResult foodResult,
                         RedirectAttributes redirectAttributes) {
        if (foodResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.food", foodResult);
            redirectAttributes.addFlashAttribute("food", food);
            return "redirect:/update/" + food.getId();
        }
        if (food.getIngredientRequests() == null) {
            food.setIngredientRequests(new ArrayList<>());
        }
        if (food.getLabelRequests() == null) {
            food.setLabelRequests(new ArrayList<>());
        }
        List<IngredientRequest> ingredientRequestList = food.getIngredientRequests();
        for (int i = 0; i < ingredientRequestList.size(); i++) {
            if (ingredientRequestList.get(i).getName() == null) {
                ingredientRequestList.remove(ingredientRequestList.get(i));
            }
        }
        if (ingredientRequestList.size() > 1) {
            for (int i = 0; i < ingredientRequestList.size(); i++) {
                for (int j = i + 1; j < ingredientRequestList.size(); j++) {
                    if (ingredientRequestList.get(j).getName().equals(ingredientRequestList.get(i).getName())) {
                        ingredientRequestList.get(i).setAmount(ingredientRequestList.get(i).getAmount() + ingredientRequestList.get(j).getAmount() * ingredientRequestList.get(j).getMeasureCo() / ingredientRequestList.get(i).getMeasureCo());
                        ingredientRequestList.remove(ingredientRequestList.get(j));
                    }
                }
            }
        }
        List<LabelRequest> labelRequestList = food.getLabelRequests();
        for (int i = 0; i < labelRequestList.size(); i++) {
            if (labelRequestList.get(i).getName() == null) {
                labelRequestList.remove(labelRequestList.get(i));
            }
        }
        detailsService.updateFood(food);
        return "redirect:/details/" + food.getId();
    }
}