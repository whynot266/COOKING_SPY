package com.mycompany.spring_mvc_project_final.domainModel;

import java.util.List;

public class FoodDto {
    private String name;
    private String image;
    private String description;
    private String tutorial;
    private List<IngredientDto> ingredientDtoList;
    private List<LabelDto> labelDtoList;

    public FoodDto() {
    }

    // getters and setters omitted for brevity
    public FoodDto(String name, String image, String description, String tutorial) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.tutorial = tutorial;
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

    public List<LabelDto> getLabelDtoList() {
        return labelDtoList;
    }

    public void setLabelDtoList(List<LabelDto> labelDtoList) {
        this.labelDtoList = labelDtoList;
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

    public List<IngredientDto> getIngredientDtoList() {
        return ingredientDtoList;
    }

    public void setIngredientDtoList(List<IngredientDto> ingredients) {
        this.ingredientDtoList = ingredients;
    }
}
