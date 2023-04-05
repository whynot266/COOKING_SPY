package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.domainModel.FoodDto;
import com.mycompany.spring_mvc_project_final.domainModel.IngredientDto;
import com.mycompany.spring_mvc_project_final.domainModel.LabelDto;
import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.repository.FoodRepository;
import com.mycompany.spring_mvc_project_final.repository.FoodsIngredientsRepository;
import com.mycompany.spring_mvc_project_final.repository.IngredientRepository;
import com.mycompany.spring_mvc_project_final.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateFoodService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    LabelRepository labelRepository;
    @Autowired
    FoodsIngredientsRepository foodsIngredientsRepository;
    public List<String> getAllLabelName(){
        return labelRepository.findAllLabelName();
    }
    public List<String> getListOfIngredients(String input){
        return ingredientRepository.findAllIngredientsNameByInput(input);
    }
    public List<String> getListOfLabels(String input){
        return labelRepository.findAllLabelNameByInput(input);
    }
    public boolean checkIngreExistenceByName(String name){
        if (ingredientRepository.findByNameContaining(name).size()==0){
            return false;
        }

        return true;
    }

    public IngredientEntity findIngreByName(String name){
        return ingredientRepository.findByNameContaining(name).get(0);
    }



    public void createFood(FoodDto foodDto) {
        FoodEntity food = new FoodEntity();
        food.setName(foodDto.getName());
        food.setImage(foodDto.getImage());
        food.setDescription(foodDto.getDescription());
        food.setTutorial(foodDto.getTutorial());
        List<FoodsIngredientsEntity> foodsIngredientsEntityList= new ArrayList<>();
        List<FoodsLabelsEntity> foodsLabelsEntityList= new ArrayList<>();
        for(IngredientDto ingredientDto: foodDto.getIngredientDtoList()){
            IngredientEntity ingredient = ingredientRepository.findByName(ingredientDto.getName());

            FoodsIngredientsEntity foodsIngredientsEntity = new FoodsIngredientsEntity();
            foodsIngredientsEntity.setFood(food);
            if (ingredient!=null){
                foodsIngredientsEntity.setIngredient(ingredient);
            }else {
                foodsIngredientsEntity.setIngredient(ingredientRepository.findById(268).get());
            }
            foodsIngredientsEntity.setAmount(ingredientDto.getAmount());
            foodsIngredientsEntity.setMeasure(ingredientDto.getMeasure());
            foodsIngredientsEntityList.add(foodsIngredientsEntity);
        }
        for(LabelDto labelDto: foodDto.getLabelDtoList()){
            LabelEntity label = labelRepository.findByName(labelDto.getName());
            FoodsLabelsEntity foodsLabelsEntity = new FoodsLabelsEntity();
            foodsLabelsEntity.setFood(food);
            foodsLabelsEntity.setLabel(label);
            foodsLabelsEntityList.add(foodsLabelsEntity);
        }
        food.setFoodsIngredientsEntityList(foodsIngredientsEntityList);
        food.setFoodsLabelsEntityList(foodsLabelsEntityList);

        foodRepository.save(food);

    }


}