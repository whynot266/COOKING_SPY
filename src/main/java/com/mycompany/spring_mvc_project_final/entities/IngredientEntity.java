package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
    @Column(name = "calorie")
    private double calorie;
    @Column(name = "water")
    private double water;
    @Column(name = "protein")
    private double protein;
    @Column(name = "lipid")
    private double lipid;
    @Column(name = "glucid")
    private double glucid;
    @Column(name = "celluloza")
    private double celluloza;

    public IngredientEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getLipid() {
        return lipid;
    }

    public void setLipid(double lipid) {
        this.lipid = lipid;
    }

    public double getGlucid() {
        return glucid;
    }

    public void setGlucid(double glucid) {
        this.glucid = glucid;
    }

    public double getCelluloza() {
        return celluloza;
    }

    public void setCelluloza(double celluloza) {
        this.celluloza = celluloza;
    }
}
