package com.mycompany.spring_mvc_project_final.dto;

public class UserRequest {

    private AccountRequest accountRequest;
    private double height;

    private double weight;

    private int age;

    private int gender;

    private int activityLevel;

    private int status;

    public AccountRequest getAccountRequest() {
        return accountRequest;
    }

    public void setAccountRequest(AccountRequest accountRequest) {
        this.accountRequest = accountRequest;
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
}
