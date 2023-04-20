package com.mycompany.spring_mvc_project_final.repository;
import com.mycompany.spring_mvc_project_final.entities.FoodsIngredientsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.EmptyStackException;
import java.util.List;
@Repository
public interface FoodsIngredientsRepository extends CrudRepository<FoodsIngredientsEntity, Integer> {
    @Query(value = "select * from food_ingredient where food_id=?1",nativeQuery = true)
    List<FoodsIngredientsEntity> getListOfIngredients(long id);
    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "delete from food_ingredient where food_id=?1",nativeQuery = true)
    void deleteFoodById(long id);
}
