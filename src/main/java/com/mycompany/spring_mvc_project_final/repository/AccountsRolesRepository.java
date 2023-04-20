/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.repository;
import com.mycompany.spring_mvc_project_final.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.entities.AccountsRolesEntity;
import com.mycompany.spring_mvc_project_final.entities.RoleEntity;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
@Repository
public interface AccountsRolesRepository extends CrudRepository<AccountEntity, Long> {
    @Query(value = "select r.role_name from account_role as a inner join role as r on a.role_id=r.id where a.account_id=?1", nativeQuery = true)
    Set<String> findAllByAccountId(long id);
}
