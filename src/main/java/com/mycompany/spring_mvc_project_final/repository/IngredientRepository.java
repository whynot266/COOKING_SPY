package com.mycompany.spring_mvc_project_final.repository;
import com.mycompany.spring_mvc_project_final.entities.IngredientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntity, Integer> {
    @Query(value = "Select name from ingredients where name like %?1%", nativeQuery = true)
    public List<String> findAllIngredientsNameByInput(String input);
    @Query(value = "select * from ingredients where name=?1 limit 1", nativeQuery = true)
    IngredientEntity findByName(String name);
    List<IngredientEntity> findByNameContaining(String name);
}