package com.TaskManagement.service.implementation;

import com.TaskManagement.entity.LoginCredentials;
import com.TaskManagement.entity.Users;
import com.TaskManagement.exception.BadRequestException;
import com.TaskManagement.exception.NotFoundException;
import com.TaskManagement.exception.UnAuthorizedException;
import com.TaskManagement.repository.UserRepository;
import com.TaskManagement.service.serviceAbstract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImple implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public Users addUser(String loginId,Users user){
        isValidAdmin(loginId);
        if((user.getUserName()==null)||(user.getRole()==null)||(user.getAge()==null)||
                (user.getEmailId()==null)||(user.getPassword()==null)||(user.getContactNo()==null)){
            throw new BadRequestException("please give valid details for user!!");
        }
        if(userRepository.existsByEmailId(user.getEmailId())){
            throw new BadRequestException("Entered email id "+user.getEmailId()+" already registered");
        }
        Users newUser =new Users(user.getUserName(),"employee",user.getEmailId(),user.getPassword(),
                           user.getGender(),user.getAge(),user.getAddress(),user.getContactNo());
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    @Transactional
    public void deleteUser(String loginId, Long userId) {
        isValidAdmin(loginId);
        userRepository.deleteById(userId);
    }

    @Override
    public Users addAdmin(String loginId,Users user) {
        isValidAdmin(loginId);
        if((user.getUserName()==null)||(user.getRole()==null)||(user.getAge()==null)||
                (user.getEmailId()==null)||(user.getPassword()==null)||(user.getContactNo()==null)){
            throw new BadRequestException("please give valid details for admin!!");
        }
        if(userRepository.existsByEmailId(user.getEmailId())){
            throw new BadRequestException("Entered email id "+user.getEmailId()+" already registered");
        }
        Users newAdmin = new Users(user.getUserName(),"admin",user.getEmailId(),user.getPassword(),
                             user.getGender(),user.getAge(),user.getAddress(),user.getContactNo());
        userRepository.save(newAdmin);
        return newAdmin;
    }

    @Override
    public Users login(LoginCredentials loginCredentials) {
        List<Users> allUser = userRepository.findAll();
        if(allUser.isEmpty()){
            Users user = new Users("Default Admin","admin","admin@gmail.com","abcd","male",21,"admin adress",9988776655l);
            userRepository.save(user);
        }
        Users user =validLoginCredentials(loginCredentials);
        Users existLoginUser=userRepository.findLoginUser();
        if (existLoginUser != null) {
            existLoginUser.setLogin(false);
        }
        user.setLogin(true);
        userRepository.save(user);
        return user;
    }

    public Users validLoginCredentials(LoginCredentials loginCredentials){
        if(!userRepository.existsByEmailId(loginCredentials.getEmailId())) {
            throw new NotFoundException("No user found in this emailId (" + loginCredentials.getEmailId() +")");
        }
        Users user = userRepository.findByEmailId(loginCredentials.getEmailId());
        if (!user.getPassword().equals(loginCredentials.getPassword())){
            throw new NotFoundException("Invalid password");
        }
        return user;
    }

    public Users findByEmailId(String loginId){
        Users users = userRepository.findByEmailId(loginId);
        return users;
    }

    public boolean existsEmployee(Long userId){
        Users users =userRepository.findById(userId).get();
        boolean isEmployee="employee".equals(users.getRole());
        return (userRepository.existsById(userId))&&(isEmployee);
    }

    public boolean isValidAdmin(String loginId){
        isValidUser(loginId);
        Users user = userRepository.findByEmailId(loginId);
        if(!user.getRole().equals("admin")){
            throw new UnAuthorizedException("Admin can only access ");
        }
        return true;
    }

    public boolean isValidUser(String loginId){
        if(!userRepository.existsByEmailId(loginId)){
            throw new NotFoundException("No user found in this emailId : " + loginId);
        }
        Users user = userRepository.findByEmailId(loginId);
        if(!user.isLogin()){
            throw new UnAuthorizedException("Entered Id didn't login!!");
        }
        return true;
    }

    public Users findById(Long userId){
        return userRepository.findById(userId).get();
    }
}
