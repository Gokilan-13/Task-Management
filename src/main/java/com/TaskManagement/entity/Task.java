package com.TaskManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private Long assignedEmployeeId;
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
