package com.mycompany.spring_mvc_project_final.service;
import com.mycompany.spring_mvc_project_final.entities.FoodEntity;
import com.mycompany.spring_mvc_project_final.entities.FoodsLabelsEntity;
import com.mycompany.spring_mvc_project_final.entities.LabelEntity;
import com.mycompany.spring_mvc_project_final.repository.FoodRepository;
import com.mycompany.spring_mvc_project_final.repository.IngredientRepository;
import com.mycompany.spring_mvc_project_final.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class HomepageService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    LabelRepository labelRepository;
    public List<FoodEntity> listAllFoods(){
        return foodRepository.findAll();
    }
    public List<FoodEntity> list12Foods(){
        return foodRepository.find12Foods();
    }
    public List<LabelEntity> getAllLabels(FoodEntity food){
        List<FoodsLabelsEntity> foodsLabelsEntityList= food.getFoodsLabelsEntityList();
        List<LabelEntity> labelEntityList= new ArrayList<>();
        for (FoodsLabelsEntity foodsLabels: foodsLabelsEntityList){
            labelEntityList.add(foodsLabels.getLabel());
        }
        return labelEntityList;
    }
    public List<FoodEntity> getFoodsByInput(String input){
        List<FoodEntity> foodEntityList= new ArrayList<>();
        List<Long> foodIds= foodRepository.findIdByInput(input);
        for (Long i: foodIds){
            foodEntityList.add(foodRepository.findById(i));
        }
        return foodEntityList;
    }
    public List<FoodEntity> getFoodsByLabelName(String name){
        List<FoodEntity> foodEntityList= new ArrayList<>();
        List<Long> foodIds= foodRepository.findIdByLabelName(name);
        for (Long i: foodIds){
            foodEntityList.add(foodRepository.findById(i));
        }
        return foodEntityList;
    }
}
