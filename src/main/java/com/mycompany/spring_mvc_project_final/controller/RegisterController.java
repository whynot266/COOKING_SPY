/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.dto.UserRequest;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.repository.AccountRepository;
import com.mycompany.spring_mvc_project_final.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RegisterController {
    @Autowired
    RegistrationService registrationService;

    @RequestMapping(value="/registrationForm", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        UserRequest userRequest = new UserRequest();
        model.addAttribute("userRequest", userRequest);
        return "registration";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String submitForm(Model model, @ModelAttribute(value = "userRequest")UserRequest user, RedirectAttributes redirectAttributes) {
        if (registrationService.checkExistenceByEmail(user.getAccountRequest().getEmail())==true){
            redirectAttributes.addFlashAttribute("userRequest", user);
            return "redirect:/registrationForm";
        }else {
            registrationService.createUserAccount(user);
            return "login";
        }

    }




}
