/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.dto.UserRequest;
import com.mycompany.spring_mvc_project_final.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import com.mycompany.spring_mvc_project_final.repository.AccountRepository;
import com.mycompany.spring_mvc_project_final.service.RegistrationService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

@Controller
public class RegisterController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RegistrationService registrationService;

    @RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        UserRequest userRequest = new UserRequest();
        model.addAttribute("userRequest", userRequest);
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String submitForm(Model model, @ModelAttribute(value = "userRequest") UserRequest user,
                             RedirectAttributes redirectAttributes) throws IOException {
        if (registrationService.checkExistenceByEmail(user.getAccountRequest().getEmail()) == true) {
            if (registrationService.getAccountByEmail(user.getAccountRequest().getEmail()).getStatus().equals(UserStatus.UNACTIVE)) {
                redirectAttributes.addFlashAttribute("email", user.getAccountRequest().getEmail());
                return "redirect:/verificationForm";
            } else if (user.getAccountRequest().getStatus().equals(UserStatus.ACTIVE)) {
                return "redirect:/registrationForm";
            }
        } else {
            String verificationCode = generateVerificationCode();
            registrationService.createUserAccount(user, verificationCode);
            sendVerificationEmail(registrationService.getAccountByEmail(user.getAccountRequest().getEmail()).getEmail(), verificationCode);
            redirectAttributes.addFlashAttribute("email", user.getAccountRequest().getEmail());
            return "redirect:/verificationForm";
        }
        return "";
    }

    private String generateVerificationCode() {
        // Generate random verification code
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    private void sendVerificationEmail(String email, String verificationCode) {
        // Prepare email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Account Verification for [COOKING SPY]");
        message.setText("Hello,\n\n" +
                "Thank you for registering with COOKING SPY. In order to complete your registration, please enter the following verification code on the confirmation page:\n\n" +
                "Verification Code: " + verificationCode + "\n\n" +
                "If you did not register for COOKING SPY, please disregard this email.\n\n" +
                "Thank you,\nCOOKING SPY TEAM");
        // Send email
        mailSender.send(message);
    }

    @GetMapping("/verificationForm")
    public String showForm(@ModelAttribute("email") String email) {
        if (email == null || !email.contains("@gmail.com")) {
            return "redirect:/login";
        } else {
            return "verify";
        }
    }

    @PostMapping("/verify")
    public String verify(@RequestParam("verificationCode") String code, @ModelAttribute("email") String email,
                         RedirectAttributes redirectAttributes) {
        // Find user with verification code
        AccountEntity account = accountRepository.findByVerificationCode(code);
        if (account != null) {
            // Verify user and save to database
            if (account.getEmail().equals(email)) {
                account.setStatus(UserStatus.ACTIVE);
                accountRepository.save(account);
                return "login";
            } else {
                redirectAttributes.addFlashAttribute("email", email);
                return "redirect:/verificationForm";
            }
            // Return success message
        } else {
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/verificationForm";
        }
    }
}