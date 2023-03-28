package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.FoodsIngredientsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;

@Repository
public interface FoodsLabelsRepository extends CrudRepository<FoodsIngredientsEntity, Integer> {
    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "Delete from food_label where food_id=?1",nativeQuery = true)
    void deleteFoodById(long id);
}

