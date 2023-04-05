package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.dto.FoodRequest;
import com.mycompany.spring_mvc_project_final.dto.IngredientRequest;
import com.mycompany.spring_mvc_project_final.dto.LabelRequest;
import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.repository.TodayDietRepository;
import com.mycompany.spring_mvc_project_final.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodayDietController {
    @Autowired
    CreateFoodService createFoodService;
    @Autowired
    DetailsService detailsService;
    @Autowired
    AccountService accountService;
    @Autowired
    TodayDietService todayDietService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @RequestMapping(value = "/todayDiet", method = RequestMethod.GET)
    public String foodDetailsPage(Model model, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user= accountService.getUserByUsername(username);
        if (user.getTodayDiet()==null){
            user.setTodayDiet(new TodayDietEntity());
            todayDietService.updateUser(user);
            todayDietService.addUserToDiet(user, user.getTodayDiet());
        }
        if (user.getTodayDiet().getTodayDietUsersEntityList().size()==0){
            todayDietService.addUserToDiet(user, user.getTodayDiet());
        }
        TodayDietEntity todayDiet = todayDietService.getTodayDietByUserId(user.getId());
        //get all food in diet
        List<IngredientRequest> ingredientRequests= todayDietService.sumAllIngredient(todayDiet.getTodayDietFoodsEntityList());
        model.addAttribute("ingreList",ingredientRequests);
        model.addAttribute("todayDiet", todayDiet);
        return "todayDiet";
    }
    @GetMapping("/addFoodToDiet")
    public String addFood(@RequestParam(name="foodId") long foodId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user= accountService.getUserByUsername(username);
        for (TodayDietFoodsEntity todayDietFoods: user.getTodayDiet().getTodayDietFoodsEntityList()){
            if (todayDietFoods.getFood().getId()==foodId){
                return "redirect:/todayDiet";

            }
        }

        todayDietService.addFoodToTodayDiet(foodId, user);

        return "redirect:/details/"+foodId;
    }
    @GetMapping("/addFriendToDiet/{friendId}")
    public String addFriend(@PathVariable(name="friendId") long friendId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user= accountService.getUserByUsername(username);
        for (TodayDietUsersEntity todayDietUsersEntity: user.getTodayDiet().getTodayDietUsersEntityList()){
            if (todayDietUsersEntity.getUser().getId()==userDetailsService.findUserByAccountId(friendId).getId()){
                return "redirect:/todayDiet";

            }
        }

        todayDietService.addUserToDiet(userDetailsService.findUserByAccountId(friendId), user.getTodayDiet());

        return "redirect:/todayDiet";
    }
    @PostMapping("/increase")
    public String increaseCoeff(@RequestParam(name="foodId") long foodId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user= accountService.getUserByUsername(username);
        todayDietService.increaseCoefficientByFoodIdAndUser(foodId, user);
        return "redirect:/todayDiet";
    }
    @PostMapping("/decrease")
    public String decreaseCoeff(@RequestParam(name="foodId") long foodId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user= accountService.getUserByUsername(username);
        todayDietService.dereaseCoefficientByFoodIdAndUser(foodId, user);
        return "redirect:/todayDiet";
    }
    @RequestMapping(value = "/removeFood", method = RequestMethod.GET)
    public String deleteFood(@RequestParam(value = "id") long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user= accountService.getUserByUsername(username);
        todayDietService.deleteFoodFromTodayDietById(id,user);
        return "redirect:/todayDiet";
    }
    @RequestMapping(value = "/removeUser/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(value ="userId") long userId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user= accountService.getUserByUsername(username);
        todayDietService.deleteUserFromTodayDietById(userId,user);
        return "redirect:/todayDiet";
    }





}