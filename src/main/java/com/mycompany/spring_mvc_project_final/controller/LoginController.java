/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.dto.UserRequest;
import com.mycompany.spring_mvc_project_final.entities.FoodEntity;
import com.mycompany.spring_mvc_project_final.entities.TodayDietEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.service.AccountService;
import com.mycompany.spring_mvc_project_final.service.DetailsService;
import com.mycompany.spring_mvc_project_final.service.HomepageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    HomepageService homepageService;
    @Autowired
    AccountService accountService;

    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) boolean error) {

        if (error) {
            model.addAttribute("message", "Login Fail!!!");
        }
        return "login";
    }

    @RequestMapping(value = {"/homeIndex", "/"}, method = RequestMethod.GET)
    public String index(Model model,HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity user = accountService.getUserByUsername(username);
        if(username.equals("admin@gmail.com")){

        }else{
            session.setAttribute("dietCount", user.getTodayDiet().getTodayDietFoodsEntityList().size());
        }
        List<FoodEntity> foodEntityList = homepageService.list12Foods();
        model.addAttribute("listOfFoods", foodEntityList);
        if (foodEntityList.size() < 12) {
            return "redirect:/home";
        }
        return "homeIndex";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        List<FoodEntity> foodEntityList = homepageService.listAllFoods();
        model.addAttribute("listOfFoods", foodEntityList);
        return "home";
    }
}
