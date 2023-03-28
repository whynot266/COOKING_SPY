package com.mycompany.spring_mvc_project_final.entities;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "today_diet")
public class TodayDietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "food_list")
    private List<FoodEntity> foodEntityList;
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "member_list")
    private List<UserEntity> userEntityList;
    @Column(name = "coefficient")
    private double coefficient;



}

