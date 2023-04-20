/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.FoodEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.service.AccountService;
import com.mycompany.spring_mvc_project_final.service.DetailsService;
import com.mycompany.spring_mvc_project_final.service.HomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterController {
    @Autowired
    HomepageService homepageService;
     @Autowired
    AccountService accountService;

    @GetMapping(value = "/filter/Calo")
    public String filterCalo(Model model) {
        List<FoodEntity> foodEntityList = homepageService.listAllFoods();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        List<FoodEntity> listOfFoods = new ArrayList<>();
        for (FoodEntity food : foodEntityList) {
            if (food.getCalorieSummary() >= user.getAmr() / 4) {
                listOfFoods.add(food);
            }
        }
        model.addAttribute("listOfFoods", listOfFoods);
        return "home";
    }

    @GetMapping(value = "/filter/Celluloza")
    public String filterCelluloza(Model model) {
        List<FoodEntity> foodEntityList = homepageService.listAllFoods();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        List<FoodEntity> listOfFoods = new ArrayList<>();
        for (FoodEntity food : foodEntityList) {
            if (food.getCellulozaSummary() >= user.getCellulozaNeeded() / 4) {
                listOfFoods.add(food);
            }
        }
        model.addAttribute("listOfFoods", listOfFoods);
        return "home";
    }

    @GetMapping(value = "/filter/Protein")
    public String filterProtein(Model model) {
        List<FoodEntity> foodEntityList = homepageService.listAllFoods();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        List<FoodEntity> listOfFoods = new ArrayList<>();
        for (FoodEntity food : foodEntityList) {
            if (food.getProteinSummary() >= user.getProteinNeeded() / 4) {
                listOfFoods.add(food);
            }
        }
        model.addAttribute("listOfFoods", listOfFoods);
        return "home";
    }

    @GetMapping(value = "/filter/Glucid")
    public String filterGlucid(Model model) {
        List<FoodEntity> foodEntityList = homepageService.listAllFoods();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        List<FoodEntity> listOfFoods = new ArrayList<>();
        for (FoodEntity food : foodEntityList) {
            if (food.getGlucidSummary() >= user.getGlucidNeeded() / 4) {
                listOfFoods.add(food);
            }
        }
        model.addAttribute("listOfFoods", listOfFoods);
        return "home";
    }

    @GetMapping(value = "/filter/Lipid")
    public String filterLipid(Model model) {
        List<FoodEntity> foodEntityList = homepageService.listAllFoods();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        List<FoodEntity> listOfFoods = new ArrayList<>();
        for (FoodEntity food : foodEntityList) {
            if (food.getLipidSummary() >= user.getLipidNeeded() / 4) {
                listOfFoods.add(food);
            }
        }
        model.addAttribute("listOfFoods", listOfFoods);
        return "home";
    }

    @GetMapping(value = "/filter/Water")
    public String filterWater(Model model) {
        List<FoodEntity> foodEntityList = homepageService.listAllFoods();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        List<FoodEntity> listOfFoods = new ArrayList<>();
        for (FoodEntity food : foodEntityList) {
            if (food.getWaterSummary() >= user.getWaterNeeded() / 4) {
                listOfFoods.add(food);
            }
        }
        model.addAttribute("listOfFoods", listOfFoods);
        return "home";
    }

    @GetMapping(value = "/filter/Label")
    public String filterLabbel(Model model, @RequestParam("name") String name) {
        model.addAttribute("listOfFoods", homepageService.getFoodsByLabelName(name));
        return "home";
    }
}
