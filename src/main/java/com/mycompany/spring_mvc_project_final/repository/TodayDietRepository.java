package com.mycompany.spring_mvc_project_final.repository;
import com.mycompany.spring_mvc_project_final.entities.FoodEntity;
import com.mycompany.spring_mvc_project_final.entities.TodayDietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TodayDietRepository extends JpaRepository<TodayDietEntity, Integer> {
    TodayDietEntity findById(long id);
}
