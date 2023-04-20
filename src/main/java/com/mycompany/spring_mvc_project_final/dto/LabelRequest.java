package com.mycompany.spring_mvc_project_final.dto;

public class LabelRequest {
    private String name;

    // getters and setters
    public LabelRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LabelRequest(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }
}
