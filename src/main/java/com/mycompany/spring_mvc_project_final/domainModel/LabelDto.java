package com.mycompany.spring_mvc_project_final.domainModel;



public class LabelDto {

    private String name;


    public LabelDto() {
    }

    public LabelDto(String name, double amount, int measure) {

        this.name = name;

    }

    // getters and setters omitted for brevity


    public LabelDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Label{" +
                ", name='" + name + '\'' +

                '}';
    }
}
