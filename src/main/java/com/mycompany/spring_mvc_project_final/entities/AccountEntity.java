/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, length = 100)
    private String email;
    private String password;
    @OneToOne(mappedBy = "account")
    private UserEntity user;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "account_role_set")
    private Set<AccountsRolesEntity> accountsRolesEntitySet;
    @Column(name = "verification_code")
    private String verificationCode;

    public AccountEntity() {
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AccountsRolesEntity> getAccountsRolesEntitySet() {
        return accountsRolesEntitySet;
    }

    public void setAccountsRolesEntitySet(Set<AccountsRolesEntity> accountsRolesEntitySet) {
        this.accountsRolesEntitySet = accountsRolesEntitySet;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
