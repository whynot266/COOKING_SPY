/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.repository;
import com.mycompany.spring_mvc_project_final.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    List<AccountEntity> findAll();
    AccountEntity findByVerificationCode(String code);
    AccountEntity findByEmailLikeAndStatusLike(String email,
            UserStatus status);
    AccountEntity findByEmailLike(String email);
    @Query(value = "select * from account where email like %?1%",nativeQuery = true)
    AccountEntity findByJustEmailLike(String email);
    @Query(value = "select * from account where email like %?1%",nativeQuery = true)
    List<AccountEntity> findListAccountByJustEmailLike(String email);
}
