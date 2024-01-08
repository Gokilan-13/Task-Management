package com.TaskManagement.service.implementation;

import com.TaskManagement.entity.Task;
import com.TaskManagement.entity.Users;
import com.TaskManagement.exception.BadRequestException;
import com.TaskManagement.exception.NotFoundException;
import com.TaskManagement.exception.UnAuthorizedException;
import com.TaskManagement.repository.TaskRepository;
import com.TaskManagement.service.serviceAbstract.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;



@Service
public class TaskServiceImple implements TaskService {

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public UserServiceImple userServiceImple;

    @Override
    public Page<Task> getAllTask(String loginId,Pageable pageable){
        userServiceImple.isValidAdmin(loginId);
        return taskRepository.findAll(pageable);
    }
    @Override
    public Page<Task> getTasksByStatus(String loginId,String status, Pageable pageable) {
        userServiceImple.isValidAdmin(loginId);
        if(!taskRepository.existsByStatus(status)){
            throw new NotFoundException("No "+status+" status found in the task");
        }
        return taskRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Task> getAllTasksSorted(String loginId,int page, int size, String sortBy,
                                                                       String sortOrder) {
        userServiceImple.isValidAdmin(loginId);
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task addTask(String loginId,Task task ){
            userServiceImple.isValidAdmin(loginId);
            if ((task.getTaskName()==null)||(task.getTaskDesc()==null)||(task.getDueDate()==null)){
                throw new BadRequestException("please give valid details for task!!");
            }
            if(taskRepository.existsByTaskName(task.getTaskName())){
                throw new BadRequestException("Task name ("+task.getTaskName() +") already exists!!");
            }
            LocalDate currentDate = LocalDate.now();
            LocalDate valDueDate=validDueDate(task.getDueDate());
            Task newTask = new Task(task.getTaskName(), task.getTaskDesc(), currentDate, valDueDate);
            taskRepository.save(newTask);
            return task;
    }

    @Override
    @Transactional
    public Task updateTask(String loginId,Task task) {
        userServiceImple.isValidAdmin(loginId);
        if ((task.getTaskId()==null)||(task.getTaskDesc()==null)||(task.getDueDate()==null)||(task.getDueDate()==null)){
            throw new BadRequestException("please give valid details for task!!");
        }
        if (!taskRepository.existsById(task.getTaskId())) {
            throw new NotFoundException("Task id ("+task.getTaskId()+") not found!");
        }
         LocalDate valDueDate=validDueDate(task.getDueDate());
         Task newTask = taskRepository.findById(task.getTaskId()).get();
         newTask.setTaskDesc(task.getTaskDesc());
         newTask.setStatus(task.getStatus());
         task.setDueDate(valDueDate);
         taskRepository.save(newTask);
         return task;
    }

    @Override
    @Transactional
    public void deleteTask(String loginId,Long taskId) {
        userServiceImple.isValidAdmin(loginId);
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundException("Task id ("+taskId+") not found!");
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public void deleteByTaskName(String loginId,String taskName) {
        userServiceImple.isValidAdmin(loginId);
        taskRepository.deleteByTaskName(taskName);
    }

    @Override
    @Transactional
    public void assignTask(String loginId,Long taskId,Long employeeId) {
        userServiceImple.isValidAdmin(loginId);
        if ((!taskRepository.existsById(taskId))||(!userServiceImple.existsEmployee(employeeId))) {
            throw new NotFoundException("Task id ("+taskId+") or employee id("+employeeId+") not found!");
        }
        LocalDate currentDate= LocalDate.now();
        Users users =userServiceImple.findById(employeeId);
        String employeeName=users.getUserName();
        Task task =taskRepository.findById(taskId).get();
        task.setAssignedEmployeeId(employeeId);
        task.setAssignedEmployeeName(employeeName);
        task.setAssignStatus("Assigned");
        task.setStatus("In progress");
        task.setTaskAssignedDate(currentDate);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void changeStatus(String loginId,Long taskId,String status){
        userServiceImple.isValidUser(loginId);
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundException("Task id = " + taskId + " not found!");
        }
        Task task =taskRepository.findById(taskId).get();
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public Page<Task> viewMyTask(String loginId,Pageable pageable) {
        userServiceImple.isValidUser(loginId);
        Users users =userServiceImple.findByEmailId(loginId);
        Long employeeId = users.getUserId();
        if (!userServiceImple.existsEmployee(employeeId)){
            throw new UnAuthorizedException("Admin had no task employee only had task!");
        }
        return taskRepository.findByAssignedEmployeeId(employeeId,pageable);
    }


    public LocalDate validDueDate(LocalDate dueDate){
        LocalDate curDate = LocalDate.now();
        if (dueDate.isBefore(curDate)){
            throw new BadRequestException("Due date should be after task the task created date !!");
        }
        return dueDate;
    }


}
