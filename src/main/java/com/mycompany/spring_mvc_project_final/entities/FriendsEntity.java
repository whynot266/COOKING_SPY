package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "friends")
public class FriendsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountEntity userAccount;
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private AccountEntity friendAccount;

    public FriendsEntity() {
    }

    public FriendsEntity(BigInteger id, AccountEntity userAccount, AccountEntity friendAccount) {
        this.id = id;
        this.userAccount = userAccount;
        this.friendAccount = friendAccount;
    }

    public AccountEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(AccountEntity userAccount) {
        this.userAccount = userAccount;
    }

    public AccountEntity getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(AccountEntity friendAccount) {
        this.friendAccount = friendAccount;
    }
}
