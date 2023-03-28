package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
    FoodEntity findById(long id);
    @Query(value = "select f.id from food_ingredient as fi left join foods as f on fi.food_id=f.id left join ingredients as i on fi.ingredient_id=i.id where f.name like %?1% or i.name like %?1% group by f.id", nativeQuery = true)
    List<Long> findIdByInput(String input);
    @Query(value = "select f.id from food_label as fl left join foods as f on fl.food_id=f.id left join labels as l on fl.label_id=l.id where l.name like %?1% group by f.id", nativeQuery = true)
    List<Long> findIdByLabelName(String labelName);

}
