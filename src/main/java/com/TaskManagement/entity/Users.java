package com.TaskManagement.entity;

import jakarta.persistence.*;
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
    @Column(nullable = false,length=40)
    private String userName;
    private String role;
    @Column(unique = true,nullable = false,length=40)
    private String emailId;
    @Column(nullable = false,length=10)
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
