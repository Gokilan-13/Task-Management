package com.TaskManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String role;
    private String emailId;
    private String password;
    private String gender;
    private Integer age;
    private boolean isLogin;
    private String address;
    private Long contactNo;

    public Users(String userName, String role, String emailId, String password, String gender, Integer age, String address, Long contactNo) {
        this.userName = userName;
        this.role = role;
        this.emailId = emailId;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.isLogin=false;
        this.address = address;
        this.contactNo = contactNo;
    }
}
