package com.mycompany.spring_mvc_project_final.repository;
import com.mycompany.spring_mvc_project_final.entities.TodayDietEntity;
import com.mycompany.spring_mvc_project_final.entities.TodayDietFoodsEntity;
import com.mycompany.spring_mvc_project_final.entities.TodayDietUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.EmptyStackException;
@Repository
public interface TodayDietUsersRepository extends JpaRepository<TodayDietUsersEntity, Integer> {
    TodayDietUsersEntity findById(long id);
    @Modifying
    @Transactional(rollbackFor = EmptyStackException.class)
    @Query(value = "delete from todayDiet_user where user_id=?1 and todayDiet_id=?2",nativeQuery = true)
    void deleteTodayDietUserById(long userId, long todayDietId);
    @Modifying
    @Transactional
    @Query(value = "insert into todayDiet_user(todayDiet_id, user_id) values(?2,?1)",nativeQuery = true)
    void insertTodayDietUser(long userId, long todayDietId);
}
