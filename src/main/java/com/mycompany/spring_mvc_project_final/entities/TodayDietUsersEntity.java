package com.mycompany.spring_mvc_project_final.entities;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todayDiet_user")
public class TodayDietUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "todayDiet_id")
    private TodayDietEntity todayDiet;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TodayDietEntity getTodayDiet() {
        return todayDiet;
    }

    public void setTodayDiet(TodayDietEntity todayDiet) {
        this.todayDiet = todayDiet;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

