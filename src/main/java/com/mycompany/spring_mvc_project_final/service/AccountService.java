/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spring_mvc_project_final.service;
import com.mycompany.spring_mvc_project_final.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import com.mycompany.spring_mvc_project_final.repository.AccountRepository;
import com.mycompany.spring_mvc_project_final.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 *
 * @author Admin
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    public AccountEntity getAccountByVerifiCationCode(String code){
        return accountRepository.findByVerificationCode(code);
    }
    public AccountEntity getAccountByEmail(String email) {
        return accountRepository.findByEmailLikeAndStatusLike(email, UserStatus.ACTIVE);
    }
    public AccountEntity getAccountByJustEmail(String email) {
        return accountRepository.findByJustEmailLike(email);
    }
    public UserEntity getUserByUsername(String username){
        return userRepository.findByAccountId(accountRepository.findByEmailLike(username).getId());
    }
    public List<AccountEntity> findAllAccount(){
        return accountRepository.findAll();
    }
    public AccountEntity findById(long id){
        return accountRepository.findById(id).get();
    }
    public List<AccountEntity> getListAccountByJustEmail(String email) {
        return accountRepository.findListAccountByJustEmailLike(email);
    }
}
