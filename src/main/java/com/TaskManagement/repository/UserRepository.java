package com.TaskManagement.repository;

import com.TaskManagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
      public boolean existsByEmailId(String emailId);

      public Users findByEmailId(String emailId);

      public List<Users> findByRole(String role);

      @Query("select u from Users u where u.isLogin = true")
      public Users findLoginUser();
}
