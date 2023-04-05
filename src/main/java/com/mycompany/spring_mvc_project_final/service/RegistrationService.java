/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.dto.UserRequest;
import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import com.mycompany.spring_mvc_project_final.repository.AccountRepository;
import com.mycompany.spring_mvc_project_final.repository.RoleRepository;
import com.mycompany.spring_mvc_project_final.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Service
public class RegistrationService {
    @Autowired
    TodayDietService todayDietService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public AccountEntity getAccountByEmail(String email){
        return accountRepository.findByEmailLike(email);
    }

    public boolean checkExistenceByEmail(String email){
        if (accountRepository.findByEmailLike(email)==null){
            return false;
        }
        return true;
    }
    public void createUserAccount(UserRequest userRequest,String verificationCode){
        // Convert UserRequest to UserEntity
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(userRequest.getAge());
        userEntity.setGender(userRequest.getGender());
        userEntity.setHeight(userRequest.getHeight());
        userEntity.setWeight(userRequest.getWeight());
        userEntity.setStatus(userRequest.getStatus());
        userEntity.setActivityLevel(userRequest.getActivityLevel());

        // Create AccountEntity and set it to UserEntity
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(userRequest.getAccountRequest().getEmail());
        accountEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getAccountRequest().getPassword()));
        accountEntity.setStatus(UserStatus.UNACTIVE);
        accountEntity.setVerificationCode(verificationCode);
        RoleEntity roleEntity = roleRepository.findByRoleName("ROLE_USER");
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleEntity);
        AccountsRolesEntity accountsRolesEntity= new AccountsRolesEntity();
        Set<AccountsRolesEntity> accountsRolesEntityList= new HashSet<>();
        for (RoleEntity role: roles){
            accountsRolesEntity.setRole(role);
            accountsRolesEntity.setAccount(accountEntity);
            accountsRolesEntityList.add(accountsRolesEntity);
        }
        accountEntity.setAccountsRolesEntitySet(accountsRolesEntityList);
        userEntity.setAccount(accountEntity);

        // Save UserEntity to the database
        userRepository.save(userEntity);
        userEntity.setTodayDiet(new TodayDietEntity());
        todayDietService.updateUser(userEntity);

    }
}
