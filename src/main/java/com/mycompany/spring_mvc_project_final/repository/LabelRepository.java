package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.LabelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends CrudRepository<LabelEntity, Integer> {
    @Query(value = "Select name from labels where name like %?1%", nativeQuery = true)
    public List<String> findAllLabelNameByInput(String input);
    @Query(value = "select * from labels where name=?1 limit 1", nativeQuery = true)
    LabelEntity findByName(String name);
    @Query(value = "Select name from labels", nativeQuery = true)
    public List<String> findAllLabelName();
    List<LabelEntity> findByNameContaining(String name);
}
