package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.dto.FoodRequest;
import com.mycompany.spring_mvc_project_final.dto.IngredientRequest;
import com.mycompany.spring_mvc_project_final.dto.LabelRequest;
import com.mycompany.spring_mvc_project_final.dto.UserResponse;
import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.repository.TodayDietFoodsRepository;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserDetailsController {

    @Autowired
    TodayDietService todayDietService;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    CreateFoodService createFoodService;
    @Autowired
    DetailsService detailsService;
    @Autowired
    AccountService accountService;
    @GetMapping("/userList")
    public String showListUser(Model model){
        List<AccountEntity> accountEntityList= new ArrayList<>();
        for (AccountEntity account: accountService.findAllAccount()){
            if(!account.getEmail().equals("admin@gmail.com")){
                accountEntityList.add(account);
            }
        }

        model.addAttribute("accountList", accountEntityList);
        return "userlist";
    }
    @RequestMapping(value = "/deleteAccount/{email}", method = RequestMethod.GET)
    public String deleteFood(@PathVariable(value = "email") String email){
        AccountEntity account=accountService.getAccountByJustEmail(email);
        UserEntity user= userDetailsServiceImpl.findUserByAccountId(account.getId());

        userDetailsServiceImpl.deleteUser(user);

        return "redirect:/userList";
    }
    @RequestMapping(value = "/updateUserInfoForm", method = RequestMethod.GET)
    public String updateFood(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        UserEntity userEntity= accountService.getUserByUsername(username);
        UserResponse user= new UserResponse(userEntity.getId(),userEntity.getHeight(), userEntity.getWeight(),
                userEntity.getAge(),userEntity.getGender(),userEntity.getActivityLevel(),userEntity.getStatus());
        model.addAttribute("user",user);

        return "userInfo";
    }
    @RequestMapping(value="/updateUserInfo/update-process", method = RequestMethod.POST)
    public String submit(@ModelAttribute(value="user") UserResponse user,
                         BindingResult userResult,
                         RedirectAttributes redirectAttributes){
        if (userResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.food", userResult);
            redirectAttributes.addFlashAttribute("user", user);

            return "redirect:/updateUserInfoForm";
        }
        userDetailsServiceImpl.updateUserInfo(user);

        return "redirect:/";
    }
    @GetMapping("/friendList")
    public String showFriendList(Model model, @RequestParam(value = "searchInput") String input){
        List<AccountEntity> accountEntityList= accountService.getListAccountByJustEmail(input);
        List<AccountEntity> strangerList= new ArrayList<>();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        for(AccountEntity accountEntity:accountEntityList){
            if (userDetailsServiceImpl.checkAwareOrNot(accountService.getAccountByJustEmail(username),accountEntity)==false){
                strangerList.add(accountEntity);
            }
        }
        model.addAttribute("listOfAccounts", strangerList);
        model.addAttribute("friendRequests", userDetailsServiceImpl.getFriendInvitation(username));
        model.addAttribute("connectedFriends", userDetailsServiceImpl.getFriendListByUsername(username));
        return "friendList";
    }
    @RequestMapping(value = "/sendInvitation", method = RequestMethod.POST)
    public void addFriend(@RequestParam("id") long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        userDetailsServiceImpl.sendInvite(accountService.getAccountByJustEmail(username).getId(),id);
    }
    @RequestMapping(value = "/unfriend/{id}", method = RequestMethod.GET)
    public String accept(@PathVariable("id") long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        userDetailsServiceImpl.unfriend(accountService.getAccountByJustEmail(username).getId(),id);
        return "redirect:/friendList?searchInput=''";
    }
    @RequestMapping(value = "/acceptInvitation/{id}", method = RequestMethod.GET)
    public String acceptFriend(@PathVariable("id") long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        userDetailsServiceImpl.sendInvite(accountService.getAccountByJustEmail(username).getId(),id);
        return "redirect:/friendList?searchInput=''";
    }

}