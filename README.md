# Task-Management

A simple REST API for managing tasks built with Java Spring Boot.


## Architecture


![Architecture](https://github.com/Gokilan-13/Task-Management/blob/main/ArchitectureOfRestApi.PNG?raw=true)


## Table of Contents
- [Usage](#usage)


## Base URL: {localhost:8080/}

### User Authentication:

1. **User Login:**
   - Endpoint: `POST /login`
   - Purpose: Allow users to log in with their email and password.
   - Request Body:
     ```json
     {
       "emailId": "user@example.com",
       "password": "userpassword"
     }
     ```
   - Response: User details
   - HTTP Status: 200 OK (assuming successful login)

### Admin Privileges:

2. **Add Task:**
   - Endpoint: `POST /admin/addtask/{loginId}`
   - Purpose: Allow admins to add a new task.
   - Request Body:
     ```json
     {
       "taskName": "New Task",
       "taskDesc": "Task Description",
       "dueDate": "2024-01-15"
     }
     ```
   - Response: Task details
   - HTTP Status: 201 Created

3. **Add User:**
   - Endpoint: `POST /admin/adduser/{loginId}`
   - Purpose: Allow admins to add a new user.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
   - Request Body:
     ```json
     {
       "userName": "John Doe",
       "emailId": "john.doe@example.com",
       "password": "userpassword",
       "gender": "Male",
       "age": 30,
       "address": "123 Main St",
       "contactNo": 9234567890
     }
     ```
   - Response: User details
   - HTTP Status: 201 Created

4. **Add Admin:**
   - Endpoint: `POST /admin/addadmin/{loginId}`
   - Purpose: Allow admins to add a new admin user.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
   - Request Body:
     ```json
     {
       "userName": "John Doe",
       "emailId": "john.doe@example.com",
       "password": "userpassword",
       "gender": "Male",
       "age": 30,
       "address": "123 Main St",
       "contactNo": 9234567890
     }
     ```
   - Response: None
   - HTTP Status: 201 Created

5. **Change Task Status:**
   - Endpoint: `PUT /changeTaskStatus/{loginId}`
   - Purpose: Allow admins to change the status of a task.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
   - **Request Parameters:**
       - `taskId` (Long) - The taskId to filter tasks.
       - `status` (String) - status to change task status.
   - Response: None
   - HTTP Status: 200 OK

6. **View All Tasks:**
   - Endpoint: `GET /admin/viewAllTask/{loginId}`
   - Purpose: Allow admins to view all tasks.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
   - Response: List of tasks (paginated)

7. **View My Tasks:**
   - Endpoint: `GET /employee/viewMyTask/{loginId}`
   - Purpose: Allow employees to view their assigned tasks.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
   - Response: List of tasks (paginated)

8. **Get Tasks with Filtering:**
   - Endpoint: `GET /admin/getTaskFilter/{loginId}`
   - Purpose: Allow admins to filter tasks based on status.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
   - Request Parameters:
     - `status` (String) - status to filter tasks.
   - Response: List of tasks based on the filter

9. **Delete Task:**
   - Endpoint: `DELETE /admin/deleteTask/{loginId}/{taskId}`
   - Purpose: Allow admins to delete a task.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
       - `{taskId}` (Long) - task id to identify the corresponding task.
   - Response: None
   - HTTP Status: 204 No Content

10. **Delete Task:**
   - Endpoint: `DELETE /admin/deleteTask/{loginId}/{taskName}`
   - Purpose: Allow admins to delete a task.
   - **Path Variable:**
       - `{loginId}` (String) - The unique identifier or email id of the user.
       - `{taskName}` (String) - task name to identify the corresponding task.
   - Response: None
   - HTTP Status: 204 No Content

11. **Delete User:**
    - Endpoint: `DELETE /admin/deleteUser/{loginId}/{userId}`
    - Purpose: Allow admins to delete a user.
    - **Path Variable:**
        - `{loginId}` (String) - The unique identifier or email id of the user.
        - `{userId}` (Long) - user id to identify the corresponding user.
    - Response: None
    - HTTP Status: 204 No Content

12. **Update Task:**
    - Endpoint: `PUT /admin/updateTask/{loginId}`
    - Purpose: Allow admins to update task details.
    - **Path Variable:**
        - `{loginId}` (String) - The unique identifier or email id of the user.
    - Request Body:
      ```json
      {
        "taskDesc": "Updated Task Description",
        "status": "Completed",
        "dueDate": "2024-01-20",
        "taskId": 1
      }
      ```
    - Response: None
    - HTTP Status: 200 OK

13. **Assign Task:**
    - Endpoint: `PUT /admin/assignTask/{loginId}`
    - Purpose: Allow admins to assign a task to an employee.
    - **Path Variable:**
        - `{loginId}` (String) - The unique identifier or email id of the user.
    - Response: None
    - HTTP Status: 200 OK
