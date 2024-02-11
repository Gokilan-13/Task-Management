package com.TaskManagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private Long assignedEmployeeId;

    @Column(unique=true,nullable = false,length=20)
    private String taskName;
    private String taskDesc;
    private String assignStatus;
    private String assignedEmployeeName;
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    private LocalDate taskCreatedDate;
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    private LocalDate taskAssignedDate;
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    private LocalDate dueDate;
    private String status;

    public Task(String taskName, String taskDesc, LocalDate taskCreated, LocalDate dueDate) {
        this.assignedEmployeeId=null;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.assignStatus = "Not Assigned";
        this.assignedEmployeeName = "Not Assigned";
        this.taskCreatedDate = taskCreated;
        this.taskAssignedDate = null;
        this.dueDate = dueDate;
        this.status = "Pending";
    }
}
