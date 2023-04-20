package com.mycompany.spring_mvc_project_final.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "todayDiet_id", referencedColumnName = "id")
    private TodayDietEntity todayDiet;
    @Column(name = "height")
    private double height;
    @Column(name = "weight")
    private double weight;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private int gender;
    @Column(name = "activity_level")
    private int activityLevel;
    @Column(name = "status")
    private int status;

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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getR() {
        double R = 0;
        if (this.activityLevel == 1) {
            R = 1.2;
        } else if (this.activityLevel == 2) {
            R = 1.375;
        } else if (this.activityLevel == 3) {
            R = 1.55;
        } else if (this.activityLevel == 4) {
            R = 1.725;
        } else if (this.activityLevel == 5) {
            R = 1.9;
        }
        return R;
    }

    public double getAdjustment() {
        double adjust = 0;
        if (this.status == 1) {
            adjust = 0;
        } else if (this.status == 2) {
            adjust = 400 * this.getR();
        } else if (this.status == 3) {
            adjust = -300 * this.getR();
        }
        return adjust;
    }

    public int getAmr() {
        double bmr = 0;
        double amr = 0;
        if (this.gender == 1) {
            bmr = (13.397 * this.weight) + (4.799 * this.height) - (5.667 * this.age) + 88.362;
        } else if (this.gender == 2) {
            bmr = (9.247 * this.weight) + (3.098 * this.height) - (4.33 * this.age) + 447.593;
        }
        amr = bmr * this.getR() + this.getAdjustment();
        return (int)amr;
    }

    public int getProteinNeeded() {
        double result=this.getAmr() * 0.15 * this.getR();
        return (int)result;
    }

    public int getGlucidNeeded() {
        double result=this.getAmr() * 0.6 * this.getR();
        return (int)result;
    }

    public int getLipidNeeded() {
        double result=this.getAmr() * 0.25 * this.getR();
        return (int)result;
    }

    public int getWaterNeeded() {

        return 2000;
    }

    public int getCellulozaNeeded() {

        return 35;
    }
}
