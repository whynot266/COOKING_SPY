package com.mycompany.spring_mvc_project_final.repository;
import com.mycompany.spring_mvc_project_final.entities.TodayDietEntity;
import com.mycompany.spring_mvc_project_final.entities.TodayDietFoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.EmptyStackException;
@Repository
public interface TodayDietFoodsRepository extends JpaRepository<TodayDietFoodsEntity, Integer> {
    TodayDietFoodsEntity findById(long id);
    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "delete from todayDiet_food where food_id=?1 and todayDiet_id=?2",nativeQuery = true)
    void deleteTodayDietFoodById(long foodId, long todayDietId);
    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "delete from todayDiet_food where food_id=?1",nativeQuery = true)
    void deleteTodayDietFoodByAdmin(long foodId);
    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "delete from todayDiet_food where todayDiet_id=?1",nativeQuery = true)
    void deleteTodayDietFoodByTodayDietId(long id);
    @Modifying
    @Transactional
    @Query(value = "insert into todayDiet_food(todayDiet_id, food_id, coefficient) values(?2,?1,?3)",nativeQuery = true)
    void insertTodayDietFood(long foodId, long todayDietId, double coefficient);
}
