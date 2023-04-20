package com.mycompany.spring_mvc_project_final.dto;

import java.util.List;

public class FoodResponse {
    private long id;
    private String name;
    private String image;
    private String description;
    private String tutorial;
    private List<IngredientRequest> ingredientRequests;
    private List<LabelRequest> labelRequests;

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LabelRequest> getLabelRequests() {
        return labelRequests;
    }

    public void setLabelRequests(List<LabelRequest> labelRequests) {
        this.labelRequests = labelRequests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
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

    public List<IngredientRequest> getIngredientRequests() {
        return ingredientRequests;
    }

    public void setIngredientRequests(List<IngredientRequest> ingredientRequests) {
        this.ingredientRequests = ingredientRequests;
    }
}
