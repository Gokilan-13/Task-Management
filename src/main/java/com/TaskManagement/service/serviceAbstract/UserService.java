package com.TaskManagement.service.serviceAbstract;

import com.TaskManagement.entity.Users;

public interface UserService {


    public Users addUser(String loginId,Users newUser);

    public Users addAdmin(String loginId,Users newUser);

    public void deleteUser(String loginId,Long userId);

    public Users login(String loginCredentials);
}
