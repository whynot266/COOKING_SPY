/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.main;
import com.mycompany.spring_mvc_project_final.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class Main {
    @Autowired
    FoodRepository foodRepository;
    public static void main(String[] args) {
        System.out.println("password===>" + encrytePassword("123123"));
    }
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
