package com.mycompany.spring_mvc_project_final.service;
import com.mycompany.spring_mvc_project_final.dto.FoodRequest;
import com.mycompany.spring_mvc_project_final.dto.IngredientRequest;
import com.mycompany.spring_mvc_project_final.dto.LabelRequest;
import com.mycompany.spring_mvc_project_final.dto.TodayDietResponse;
import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Service
public class TodayDietService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    TodayDietRepository todayDietRepository;
    @Autowired
    TodayDietFoodsRepository todayDietFoodsRepository;
    @Autowired
    TodayDietUsersRepository todayDietUsersRepository;
    public TodayDietEntity getTodayDietByUserId(long id){
        return todayDietRepository.findById(id);
    }
    public List<IngredientRequest> sumAllIngredient(List<TodayDietFoodsEntity> todayDietFoodsEntityList){
        List<IngredientRequest> ingredientRequestList= new ArrayList<>();
        for (int i=0; i< todayDietFoodsEntityList.size();i++){
            for (FoodsIngredientsEntity foodsIngredientsEntity: todayDietFoodsEntityList.get(i).getFood().getFoodsIngredientsEntityList()){
                ingredientRequestList.add(new IngredientRequest(foodsIngredientsEntity.getIngredient().getName(),(int)foodsIngredientsEntity.getAmount()*todayDietFoodsEntityList.get(i).getCoefficient(),foodsIngredientsEntity.getMeasureInt()));
            }
        }
        for (int i=0; i<ingredientRequestList.size();i++){
            for (int j=i+1; j<ingredientRequestList.size();j++){
                if(ingredientRequestList.get(j).getName().equals(ingredientRequestList.get(i).getName())){
                    ingredientRequestList.get(i).setAmount((int)ingredientRequestList.get(i).getAmount()+(int)ingredientRequestList.get(j).getAmount()*ingredientRequestList.get(j).getMeasureCo()/ingredientRequestList.get(i).getMeasureCo());
                    ingredientRequestList.remove(ingredientRequestList.get(j));
                }
            }
        }
        return ingredientRequestList;
    }
    public void increaseCoefficientByFoodIdAndUser(long foodId, UserEntity user) {
        TodayDietEntity todayDiet=user.getTodayDiet();
        for (TodayDietFoodsEntity todayDietFoods: todayDiet.getTodayDietFoodsEntityList()){
            if (todayDietFoods.getFood().getId()==foodId && todayDietFoods.getCoefficient()<10){
                double newCoeff= todayDietFoods.getCoefficient()+0.1;
                todayDietFoodsRepository.deleteTodayDietFoodById(foodId,user.getTodayDiet().getId());
                todayDietFoodsRepository.insertTodayDietFood(foodId,user.getTodayDiet().getId(),newCoeff);
            }
        }
    }
    public void increaseCoefficientByFoodIdAndUserBy1(long foodId, UserEntity user) {
        TodayDietEntity todayDiet=user.getTodayDiet();
        for (TodayDietFoodsEntity todayDietFoods: todayDiet.getTodayDietFoodsEntityList()){
            if (todayDietFoods.getFood().getId()==foodId && todayDietFoods.getCoefficient()<9.1){
                double newCoeff= todayDietFoods.getCoefficient()+1;
                todayDietFoodsRepository.deleteTodayDietFoodById(foodId,user.getTodayDiet().getId());
                todayDietFoodsRepository.insertTodayDietFood(foodId,user.getTodayDiet().getId(),newCoeff);
            }
        }
    }
    public void dereaseCoefficientByFoodIdAndUser(long foodId, UserEntity user) {
        TodayDietEntity todayDiet=user.getTodayDiet();
        for (TodayDietFoodsEntity todayDietFoods: todayDiet.getTodayDietFoodsEntityList()){
            if (todayDietFoods.getFood().getId()==foodId && todayDietFoods.getCoefficient()>0.1){
                double newCoeff= todayDietFoods.getCoefficient()-0.1;
                todayDietFoodsRepository.deleteTodayDietFoodById(foodId,user.getTodayDiet().getId());
                todayDietFoodsRepository.insertTodayDietFood(foodId,user.getTodayDiet().getId(),newCoeff);
            }
        }
    }
    public void deleteFoodFromTodayDietByAdmin(long id){
        todayDietFoodsRepository.deleteTodayDietFoodByAdmin(id);
    }
    public void deleteFoodFromTodayDietById(long id, UserEntity user){
        todayDietFoodsRepository.deleteTodayDietFoodById(id, user.getTodayDiet().getId());
    }
    public void addFoodToTodayDiet(long foodId, UserEntity user) {
        todayDietFoodsRepository.insertTodayDietFood(foodId, user.getTodayDiet().getId(),1);
    }
    public void updateUser(UserEntity user){
        userRepository.save(user);
    }
    public void addUserToDiet(UserEntity user, TodayDietEntity todayDiet){
        todayDietUsersRepository.insertTodayDietUser(user.getId(), todayDiet.getId());
    }
    public void deleteTodayDiet(TodayDietEntity todayDiet){
        todayDietRepository.delete(todayDiet);
    }
    public void deleteUserFromTodayDietById(long id, UserEntity user){
        todayDietUsersRepository.deleteTodayDietUserById(id, user.getTodayDiet().getId());
    }
    public boolean checkFoodExistence(long id, UserEntity user){
        for (TodayDietFoodsEntity todayDietFoods : user.getTodayDiet().getTodayDietFoodsEntityList()) {
            if (todayDietFoods.getFood().getId() == id) {
                return true;

            }
        }
        return false;
    }
}
