package com.mycompany.spring_mvc_project_final.entities;



import com.mycompany.spring_mvc_project_final.repository.FoodRepository;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todayDiet_food")
public class TodayDietFoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "todayDiet_id")
    private TodayDietEntity todayDiet;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;
    @Column(name = "coefficient")
    private double coefficient;

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

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}

