package com.mycompany.spring_mvc_project_final.dto;





public class IngredientRequest {


    private String name;
    private double amount;
    private int measure;

    // getters and setters


    public IngredientRequest() {
    }

    public IngredientRequest(String name, double amount, int measure) {
        this.name = name;
        this.amount = amount;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMeasure() {
        return measure;
    }
    public String getMeasureName(){
        if (this.measure==1){
            return "g";
        } else if (this.measure==2) {
            return "thìa cà phê";
        } else if (this.measure==3) {
            return "thìa canh";
        }
        return "";
    }
    public double getMeasureCo() {
        if (this.measure==1){
            return 0.01;
        } else if (this.measure==2) {
            return 0.05;
        } else if (this.measure==3) {
            return 0.15;
        }
        return 0;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", measure=" + measure +
                '}';
    }
}

