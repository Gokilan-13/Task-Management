package com.TaskManagement.service.serviceAbstract;

import com.TaskManagement.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    public Page<Task> getAllTask(String loginId, Pageable pageable);

    public Page<Task> getTasksByStatus(String loginId,String status, Pageable pageable);

    public Page<Task> getAllTasksSorted(String loginId,int page, int size, String sortBy, String sortOrder);

    public Task addTask(String loginId,Task task);

    public Task updateTask(String loginId,Task task);

    public void deleteTask(String loginId,Long taskId);

    public void deleteByTaskName(String loginId,String taskName);

    public void assignTask(String loginId,Long taskId,Long employeeId);

    public void changeStatus(String loginId,Long taskId,String status);

    public Page<Task> viewMyTask(String loginId,Pageable pageable);

}
