package com.mycompany.spring_mvc_project_final.dto;



import com.mycompany.spring_mvc_project_final.entities.TodayDietFoodsEntity;
import com.mycompany.spring_mvc_project_final.entities.TodayDietUsersEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


public class TodayDietResponse {

    private long id;

    private UserEntity owner;

    private List<TodayDietFoodsEntity> todayDietFoodsEntityList;

    private List<TodayDietUsersEntity> todayDietUsersEntityList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TodayDietFoodsEntity> getTodayDietFoodsEntityList() {
        return todayDietFoodsEntityList;
    }

    public void setTodayDietFoodsEntityList(List<TodayDietFoodsEntity> todayDietFoodsEntityList) {
        this.todayDietFoodsEntityList = todayDietFoodsEntityList;
    }

    public List<TodayDietUsersEntity> getTodayDietUsersEntityList() {
        return todayDietUsersEntityList;
    }

    public void setTodayDietUsersEntityList(List<TodayDietUsersEntity> todayDietUsersEntityList) {
        this.todayDietUsersEntityList = todayDietUsersEntityList;
    }
}

