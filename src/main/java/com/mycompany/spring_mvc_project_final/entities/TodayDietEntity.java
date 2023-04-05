package com.mycompany.spring_mvc_project_final.entities;



import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todayDiet")
public class TodayDietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy ="todayDiet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TodayDietFoodsEntity> todayDietFoodsEntityList;
    @OneToMany(mappedBy ="todayDiet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
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
    public double getCalorieSummary(){
        double sum=0;
        for (TodayDietFoodsEntity todayDietFoods: this.getTodayDietFoodsEntityList()){
            sum+=todayDietFoods.getFood().getCalorieSummary()*todayDietFoods.getCoefficient();
        }
        return sum;
    }
    public double getWaterSummary(){
        double sum=0;
        for (TodayDietFoodsEntity todayDietFoods: this.getTodayDietFoodsEntityList()){
            sum+=todayDietFoods.getFood().getWaterSummary()*todayDietFoods.getCoefficient();
        }
        return sum;
    }
    public double getProteinSummary(){
        double sum=0;
        for (TodayDietFoodsEntity todayDietFoods: this.getTodayDietFoodsEntityList()){
            sum+=todayDietFoods.getFood().getProteinSummary()*todayDietFoods.getCoefficient();
        }
        return sum;
    }
    public double getLipidSummary(){
        double sum=0;
        for (TodayDietFoodsEntity todayDietFoods: this.getTodayDietFoodsEntityList()){
            sum+=todayDietFoods.getFood().getLipidSummary()*todayDietFoods.getCoefficient();
        }
        return sum;
    }
    public double getGlucidSummary(){
        double sum=0;
        for (TodayDietFoodsEntity todayDietFoods: this.getTodayDietFoodsEntityList()){
            sum+=todayDietFoods.getFood().getGlucidSummary()*todayDietFoods.getCoefficient();
        }
        return sum;
    }
    public double getCellulozaSummary(){
        double sum=0;
        for (TodayDietFoodsEntity todayDietFoods: this.getTodayDietFoodsEntityList()){
            sum+=todayDietFoods.getFood().getCellulozaSummary()*todayDietFoods.getCoefficient();
        }
        return sum;
    }
    public double getAmrSummary(){
        double sum=0;
        for (TodayDietUsersEntity todayDietUsersEntity: this.getTodayDietUsersEntityList()){
            sum+=todayDietUsersEntity.getUser().getAmr();
        }
        return sum;
    }
    public double getWaterNeededSummary(){
        double sum=0;
        for (TodayDietUsersEntity todayDietUsersEntity: this.getTodayDietUsersEntityList()){
            sum+=todayDietUsersEntity.getUser().getWaterNeeded();
        }
        return sum;
    }
    public double getProteinNeededSummary(){
        double sum=0;
        for (TodayDietUsersEntity todayDietUsersEntity: this.getTodayDietUsersEntityList()){
            sum+=todayDietUsersEntity.getUser().getProteinNeeded();
        }
        return sum;
    }
    public double getLipidNeededSummary(){
        double sum=0;
        for (TodayDietUsersEntity todayDietUsersEntity: this.getTodayDietUsersEntityList()){
            sum+=todayDietUsersEntity.getUser().getLipidNeeded();
        }
        return sum;
    }
    public double getGlucidNeededSummary(){
        double sum=0;
        for (TodayDietUsersEntity todayDietUsersEntity: this.getTodayDietUsersEntityList()){
            sum+=todayDietUsersEntity.getUser().getGlucidNeeded();
        }
        return sum;
    }
    public double getCellulozaNeededSummary(){
        double sum=0;
        for (TodayDietUsersEntity todayDietUsersEntity: this.getTodayDietUsersEntityList()){
            sum+=todayDietUsersEntity.getUser().getCellulozaNeeded();
        }
        return sum;
    }
}

