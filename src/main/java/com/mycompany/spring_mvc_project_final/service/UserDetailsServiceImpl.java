/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;
import com.mycompany.spring_mvc_project_final.dto.UserResponse;
import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.mycompany.spring_mvc_project_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountsRolesRepository accountsRolesRepository;
    @Autowired
    FriendRepository friendRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByEmailLikeAndStatusLike(email, UserStatus.ACTIVE);
        System.out.println(account.getEmail());
        if (account == null) {
            throw new UsernameNotFoundException(email);
        }
        Set<String> roleNames= accountsRolesRepository.findAllByAccountId(account.getId());
        Set<GrantedAuthority> grantList = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleNames)) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        return (UserDetails) new User(account.getEmail(), account.getPassword(), grantList);
    }
    public void deleteUser(UserEntity user){
        userRepository.delete(user);
    }
    public UserEntity findUserByAccountId(long id){
        return userRepository.findByAccountId(id);
    }
    public void updateUserInfo(UserResponse user){
        userRepository.updateUserInfo(user.getAge(),user.getGender(), user.getHeight(),user.getStatus(),user.getWeight(),user.getActivityLevel(), user.getId());
    }
    public  List<FriendsEntity> getFriendListByUsername(String username){
        List<FriendsEntity> friendToCheck= friendRepository.findFriendListByAccountId(accountRepository.findByEmailLike(username).getId());
        List<FriendsEntity> connectedFriends= new ArrayList<>();
        for (FriendsEntity friendsEntity :friendToCheck){
            if (friendRepository.checkFriendOrNot(friendsEntity.getUserAccount().getId(),friendsEntity.getFriendAccount().getId()).size()==1){
                connectedFriends.add(friendsEntity);
            }
        }
        return connectedFriends;
    }
    public  List<FriendsEntity> getFriendInvitation(String username){
        List<FriendsEntity> friendRequestToCheck= friendRepository.findAllFriendRequestByAccountId(accountRepository.findByEmailLike(username).getId());
        List<FriendsEntity> friendRequestList= new ArrayList<>();
        for (FriendsEntity friendsEntity :friendRequestToCheck){
            if (friendRepository.checkFriendOrNot(friendsEntity.getUserAccount().getId(),friendsEntity.getFriendAccount().getId()).size()==0){
                friendRequestList.add(friendsEntity);
            }
        }
        return friendRequestList;
    }
    public void sendInvite(long userId, long friendId) {
        friendRepository.sendInvite(userId, friendId);
    }
    public void unfriend(long userId, long friendId) {
        friendRepository.unfriend(userId, friendId);
    }
    public boolean checkAwareOrNot(AccountEntity userAccount, AccountEntity account){
        if (friendRepository.checkFriendOrNot(userAccount.getId(),account.getId()).size()==0){
            return false;
        } else{
            return true;
        }
    }
    public boolean checkInvitedOrNot(AccountEntity userAccount, AccountEntity friendAccount){
        if (friendRepository.checkInvitedOrNot(userAccount.getId(),friendAccount.getId()).size()==0){
            return false;
        } else{
            return true;
        }
    }
    public void cleanTableFriends(){
        friendRepository.cleanTable();

    }
    public void deleteUserFriendList(long id){
        friendRepository.deleteUserFriendList(id);
    }
}
