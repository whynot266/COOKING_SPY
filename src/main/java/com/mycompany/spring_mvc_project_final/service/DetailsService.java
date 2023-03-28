package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.domainModel.IngredientDto;
import com.mycompany.spring_mvc_project_final.domainModel.LabelDto;
import com.mycompany.spring_mvc_project_final.dto.FoodRequest;
import com.mycompany.spring_mvc_project_final.dto.IngredientRequest;
import com.mycompany.spring_mvc_project_final.dto.LabelRequest;
import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetailsService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    LabelRepository labelRepository;
    @Autowired
    private FoodsIngredientsRepository foodsIngredientsRepository;
    @Autowired
    private FoodsLabelsRepository foodsLabelsRepository;

    public FoodEntity findById(long id){
        return foodRepository.findById(id);
    }
    public List<LabelEntity> getAllLabels(FoodEntity food){
        List<FoodsLabelsEntity> foodsLabelsEntityList= food.getFoodsLabelsEntityList();
        List<LabelEntity> labelEntityList= new ArrayList<>();
        for (FoodsLabelsEntity foodsLabels: foodsLabelsEntityList){
            labelEntityList.add(foodsLabels.getLabel());
        }
        return labelEntityList;
    }

    public void deleteFoodById(long id){
        foodRepository.delete(foodRepository.findById(id));
    }
    public void foodMapperResponse(FoodEntity foodEntity, FoodRequest foodRequest){
        List<IngredientRequest> ingredientRequests= new ArrayList<>();
        List<LabelRequest> labelRequests= new ArrayList<>();
        for (FoodsIngredientsEntity foodIngredient: foodEntity.getFoodsIngredientsEntityList()){
            IngredientRequest ingredientRequest= new IngredientRequest(foodIngredient.getIngredient().getName(),foodIngredient.getAmount(),foodIngredient.getMeasureInt());
            ingredientRequests.add(ingredientRequest);
        }
        for (FoodsLabelsEntity foodsLabels: foodEntity.getFoodsLabelsEntityList()){
            LabelRequest labelRequest= new LabelRequest(foodsLabels.getLabel().getName());
            labelRequests.add(labelRequest);
        }
        foodRequest.setIngredientRequests(ingredientRequests);
        foodRequest.setLabelRequests(labelRequests);
        foodRequest.setDescription(foodEntity.getDescription());
        foodRequest.setImage(foodEntity.getImage());
        foodRequest.setTutorial(foodEntity.getTutorial());
        foodRequest.setName(foodEntity.getName());

    }
    public void updateFood(FoodRequest foodRequest){
        FoodEntity food = new FoodEntity();
        food.setId(foodRequest.getId());
        byte[] bytes1 = foodRequest.getName().getBytes(StandardCharsets.ISO_8859_1);
        String foodName = new String(bytes1, StandardCharsets.UTF_8);
        food.setName(foodName);
        food.setImage(foodRequest.getImage());
        byte[] bytes2 = foodRequest.getDescription().getBytes(StandardCharsets.ISO_8859_1);
        String foodDes = new String(bytes2, StandardCharsets.UTF_8);
        food.setDescription(foodDes);
        byte[] bytes3 = foodRequest.getTutorial().getBytes(StandardCharsets.ISO_8859_1);
        String foodTut = new String(bytes3, StandardCharsets.UTF_8);
        food.setTutorial(foodTut);
        List<FoodsIngredientsEntity> foodsIngredientsEntityList= new ArrayList<>();
        List<FoodsLabelsEntity> foodsLabelsEntityList= new ArrayList<>();
        for(IngredientRequest ingredientRequest: foodRequest.getIngredientRequests()){
            byte[] bytes = ingredientRequest.getName().getBytes(StandardCharsets.ISO_8859_1);
            String encodeName = new String(bytes, StandardCharsets.UTF_8);
            IngredientEntity ingredient = ingredientRepository.findByName(encodeName);
            FoodsIngredientsEntity foodsIngredientsEntity = new FoodsIngredientsEntity();
            foodsIngredientsEntity.setFood(food);
            foodsIngredientsEntity.setIngredient(ingredient);
            foodsIngredientsEntity.setAmount(ingredientRequest.getAmount());
            foodsIngredientsEntity.setMeasure(ingredientRequest.getMeasure());
            foodsIngredientsEntityList.add(foodsIngredientsEntity);
        }
        for(LabelRequest labelRequest: foodRequest.getLabelRequests()){
            byte[] bytes = labelRequest.getName().getBytes(StandardCharsets.ISO_8859_1);
            String encodeName = new String(bytes, StandardCharsets.UTF_8);
            LabelEntity label = labelRepository.findByName(encodeName);
            FoodsLabelsEntity foodsLabelsEntity = new FoodsLabelsEntity();
            foodsLabelsEntity.setFood(food);
            foodsLabelsEntity.setLabel(label);
            foodsLabelsEntityList.add(foodsLabelsEntity);
        }
        food.setFoodsIngredientsEntityList(foodsIngredientsEntityList);
        food.setFoodsLabelsEntityList(foodsLabelsEntityList);
        foodsIngredientsRepository.deleteFoodById(food.getId());
        foodsLabelsRepository.deleteFoodById(food.getId());
        foodRepository.save(food);


    }




}
