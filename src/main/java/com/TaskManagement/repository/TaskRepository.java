package com.TaskManagement.repository;

import com.TaskManagement.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
   public void deleteByTaskName(String taskName);

   public boolean existsByStatus(String Status);

   public boolean existsByTaskName(String taskName);

   Page<Task> findByStatus(String status, Pageable pageable);


   Page<Task> findByAssignedEmployeeId(Long employeeId,Pageable pageable);


}
