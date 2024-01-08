package com.TaskManagement.controller;

import com.TaskManagement.entity.LoginCredentials;
import com.TaskManagement.entity.Users;
import com.TaskManagement.entity.Task;
import com.TaskManagement.service.implementation.UserServiceImple;
import com.TaskManagement.service.implementation.TaskServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    public TaskServiceImple taskServiceImple;

    @Autowired
    public UserServiceImple userServiceImple;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials loginCredentials){

       Users employee = userServiceImple.login(loginCredentials);
       return new ResponseEntity<>(employee,HttpStatus.FOUND);
    }

    @PostMapping("/admin/addTask/{loginId}")
    public ResponseEntity<?> addTask(@PathVariable String loginId,@RequestBody Task task){
       Task addedTask = taskServiceImple.addTask(loginId,task);
       return new ResponseEntity<>(addedTask,HttpStatus.CREATED);
    }
    @PostMapping("/admin/addUser/{loginId}")
    public ResponseEntity<?> addUser(@PathVariable String loginId,
                                     @RequestBody Users newUser){

        Users addedUser = userServiceImple.addUser(loginId,newUser);
        return new ResponseEntity<>(addedUser,HttpStatus.CREATED);
    }

    @PostMapping("/admin/addAdmin/{loginId}")
    public ResponseEntity<?> addAdmin(@PathVariable String loginId,
                                      @RequestBody Users newAdmin){

        Users addedAdmin = userServiceImple.addAdmin(loginId,newAdmin);
        return new ResponseEntity<>(addedAdmin,HttpStatus.CREATED);
    }

    @PostMapping("/changeTaskStatus/{loginId}")
    public ResponseEntity<?> changeTaskStatus(@PathVariable String loginId,
                                              @RequestParam Long taskId,
                                              @RequestParam String status){
        taskServiceImple.changeStatus(loginId,taskId,status);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/admin/viewAllTask/{loginId}")
    public Page<Task>viewAllTask(@PathVariable String loginId,Pageable pageable){
        return taskServiceImple.getAllTask(loginId,pageable);
    }

    @GetMapping("/admin/viewAllTaskSorted/{loginId}")
    public Page<Task> viewAllTaskSorted(@PathVariable String loginId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sortBy,
                                        @RequestParam(defaultValue = "asc") String sortOrder) {
        return taskServiceImple.getAllTasksSorted(loginId,page, size, sortBy, sortOrder);
    }

    @GetMapping("/employee/viewMyTask/{loginId}")
    public Page<Task> viewMyTask(@PathVariable String loginId,Pageable pageable){
        return taskServiceImple.viewMyTask(loginId,pageable);
    }

    @GetMapping("/admin/getTaskFilter/{loginId}")
    public Page<Task> getTasks(@PathVariable String loginId,
                               @RequestParam String status,
                               Pageable pageable) {
        if (status != null) {
            return taskServiceImple.getTasksByStatus(loginId,status, pageable);
        } else {
            return taskServiceImple.getAllTask(loginId,pageable);
        }
    }

    @DeleteMapping("/admin/deleteTask/{loginId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String loginId,
                                        @PathVariable Long taskId){
        taskServiceImple.deleteTask(loginId,taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/deleteByTaskName/{loginId}/{taskName}")
    public ResponseEntity<?> deleteTask(@PathVariable String loginId,
                                        @PathVariable String taskName){
        taskServiceImple.deleteByTaskName(loginId,taskName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/deleteUser/{loginId}/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String loginId,
                                        @PathVariable Long userId){
        userServiceImple.deleteUser(loginId,userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/admin/updateTask/{loginId}")
    public ResponseEntity<?> updateTask(@PathVariable String loginId,
                                        @RequestBody Task task){
        taskServiceImple.updateTask(loginId,task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/assignTask/{loginId}")
    public ResponseEntity<?> assignTask(@PathVariable String loginId,
                                        @RequestParam Long taskId,
                                        @RequestParam Long employeeId){
        taskServiceImple.assignTask(loginId,taskId,employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
