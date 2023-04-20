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
import java.util.EmptyStackException;
import java.util.List;
@Repository
public interface FriendRepository extends CrudRepository<FriendsEntity, Long> {
    List<FriendsEntity> findAll();
    @Query(value = "Select * from friends where user_id=?1",nativeQuery = true)
    List<FriendsEntity> findFriendListByAccountId(long id);
    @Query(value = "Select * from friends where friend_id=?1",nativeQuery = true)
    List<FriendsEntity> findAllFriendRequestByAccountId(long id);
    @Modifying
    @Transactional
    @Query(value = "insert into friends(user_id, friend_id) values(?1,?2)",nativeQuery = true)
    void sendInvite(long userId, long friendId);
    @Query(value = "Select * from friends where user_id=?2 and friend_id=?1",nativeQuery = true)
    List<FriendsEntity> checkFriendOrNot(long userId, long friendId);
    @Query(value = "Select * from friends where user_id=?1 and friend_id=?2",nativeQuery = true)
    List<FriendsEntity> checkInvitedOrNot(long userId, long friendId);
    @Modifying
    @Transactional
    @Query(value = "delete from friends where user_id=?1 and friend_id=?2 or user_id=?2 and friend_id=?1",nativeQuery = true)
    void unfriend(long userId, long friendId);
    @Modifying
    @Transactional
    @Query(value = "delete from friends where user_id=friend_id",nativeQuery = true)
    void cleanTable();

    @Modifying
    @Transactional
    @Query(value = "delete from friends where user_id=?1 or friend_id=?1",nativeQuery = true)
    void deleteUserFriendList(long id);
}
