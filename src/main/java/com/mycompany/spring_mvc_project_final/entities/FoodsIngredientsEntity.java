package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "food_ingredient")
public class FoodsIngredientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;
    @Column(name = "amount")
    private double amount;
    @Column(name = "measure")
    private int measure;

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientEntity ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMeasure() {
        if (this.measure == 1) {
            return "g";
        } else if (this.measure == 2) {
            return "thìa cà phê";
        } else if (this.measure == 3) {
            return "thìa canh";
        }
        return "";
    }

    public int getMeasureInt() {
        return this.measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    public double getCoefficient() {
        if (this.measure == 1) {
            return 0.01;
        } else if (this.measure == 2) {
            return 0.05;
        } else if (this.measure == 3) {
            return 0.15;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "FoodsIngredientsEntity{" +
                "id=" + id +
                ", foods=" + food +
                ", ingredients=" + ingredient +
                ", amount=" + amount +
                ", measure=" + measure +
                '}';
    }
}
