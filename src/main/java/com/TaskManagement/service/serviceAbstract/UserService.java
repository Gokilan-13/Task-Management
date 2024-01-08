package com.TaskManagement.service.serviceAbstract;

import com.TaskManagement.entity.LoginCredentials;
import com.TaskManagement.entity.Users;

public interface UserService {


    public Users addUser(String loginId,Users newUser);

    public Users addAdmin(String loginId,Users newUser);


    public Users login(LoginCredentials loginCredentials);

    public void deleteUser(String loginId,Long userId);
}
