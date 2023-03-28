package com.mycompany.spring_mvc_project_final.entities;



import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foods")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "image", length = 9999)
    private String image;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy ="food", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FoodsLabelsEntity> foodsLabelsEntityList;
    @Column(name = "tutorial", columnDefinition = "MEDIUMTEXT")
    private String tutorial;
    @OneToMany(mappedBy ="food", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<FoodsIngredientsEntity> foodsIngredientsEntityList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<FoodsIngredientsEntity> getFoodsIngredientsEntityList() {
        return foodsIngredientsEntityList;
    }

    public void setFoodsIngredientsEntityList(List<FoodsIngredientsEntity> foodsIngredientsEntityList) {
        this.foodsIngredientsEntityList = foodsIngredientsEntityList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FoodsLabelsEntity> getFoodsLabelsEntityList() {
        return foodsLabelsEntityList;
    }

    public void setFoodsLabelsEntityList(List<FoodsLabelsEntity> foodsLabelsEntityList) {
        this.foodsLabelsEntityList = foodsLabelsEntityList;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public double getCalorieSummary(){
        double sum=0;
        for (FoodsIngredientsEntity foodsIngredients: this.foodsIngredientsEntityList){
            sum+=foodsIngredients.getIngredient().getCalorie()*foodsIngredients.getAmount()*foodsIngredients.getCoefficient();

        }
        return sum;
    }
    public double getWaterSummary(){
        double sum=0;
        for (FoodsIngredientsEntity foodsIngredients: this.foodsIngredientsEntityList){
            sum+=foodsIngredients.getIngredient().getWater()*foodsIngredients.getAmount()*foodsIngredients.getCoefficient();
        }
        return sum;
    }
    public double getProteinSummary(){
        double sum=0;
        for (FoodsIngredientsEntity foodsIngredients: this.foodsIngredientsEntityList){
            sum+=foodsIngredients.getIngredient().getProtein()*foodsIngredients.getAmount()*foodsIngredients.getCoefficient()*4;

        }
        return sum;
    }
    public double getLipidSummary(){
        double sum=0;
        for (FoodsIngredientsEntity foodsIngredients: this.foodsIngredientsEntityList){
            sum+=foodsIngredients.getIngredient().getLipid()*foodsIngredients.getAmount()*foodsIngredients.getCoefficient()*9;
        }
        return sum;
    }
    public double getGlucidSummary(){
        double sum=0;
        for (FoodsIngredientsEntity foodsIngredients: this.foodsIngredientsEntityList){
            sum+=foodsIngredients.getIngredient().getGlucid()*foodsIngredients.getAmount()*foodsIngredients.getCoefficient()*4;
        }
        return sum;
    }
    public double getCellulozaSummary(){
        double sum=0;
        for (FoodsIngredientsEntity foodsIngredients: this.foodsIngredientsEntityList){
            sum+=foodsIngredients.getIngredient().getCelluloza()*foodsIngredients.getAmount()*foodsIngredients.getCoefficient();
        }
        return sum;
    }
}
