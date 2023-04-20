/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.entities.FoodEntity;
import com.mycompany.spring_mvc_project_final.service.AccountService;
import com.mycompany.spring_mvc_project_final.service.DetailsService;
import com.mycompany.spring_mvc_project_final.service.HomepageService;
import com.mycompany.spring_mvc_project_final.service.UserDetailsServiceImpl;
import javafx.print.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    HomepageService homepageService;

    @GetMapping(value = "/search")
    public String searchFood(Model model, @RequestParam(value = "searchInput") String input) {
        List<FoodEntity> foodEntityList = homepageService.getFoodsByInput(input);
        model.addAttribute("listOfFoods", foodEntityList);
        return "home";
    }
}
