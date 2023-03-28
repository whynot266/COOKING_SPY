package com.mycompany.spring_mvc_project_final.domainModel;



public class IngredientDto {

    private String name;
    private double amount;
    private int measure;

    public IngredientDto() {
    }

    public IngredientDto( String name, double amount, int measure) {

        this.name = name;
        this.amount = amount;
        this.measure = measure;
    }

    // getters and setters omitted for brevity



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

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", measure=" + measure +
                '}';
    }
}

