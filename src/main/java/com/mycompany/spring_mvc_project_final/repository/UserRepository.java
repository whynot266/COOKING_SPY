/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.FriendsEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.EmptyStackException;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query(value = "Select * from user where account_id=?1",nativeQuery = true)
    UserEntity findByAccountId(long id);

    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "delete from user where account_id=?1",nativeQuery = true)
    void deleteUserByAccountId(long id);
    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "update user set age=?1, gender=?2, height=?3, status=?4, weight=?5, activity_level=?6 where id=?7",nativeQuery = true)
    void updateUserInfo(int age,int gender, double height, int status, double weight, int activityLevel, long id);



}
