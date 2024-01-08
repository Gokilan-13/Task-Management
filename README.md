# Task-Management

A simple REST API for managing tasks built with Java Spring Boot.


## Architecture

![Architecture](https://github.com/Gokilan-13/Task-Management/blob/main/ArchitectureOfRestApi.PNG?raw=true)

## Table of Contents
- [Usage](#usage)


## Base URL: {baseUrl}

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
   - Endpoint: `POST /tasks`
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
   - Endpoint: `POST /users`
   - Purpose: Allow admins to add a new user.
   - Request Body:
     ```json
     {
       "userName": "John Doe",
       "emailId": "john.doe@example.com",
       "password": "userpassword",
       "gender": "Male",
       "age": 30,
       "address": "123 Main St",
       "contactNo": 1234567890
     }
     ```
   - Response: User details
   - HTTP Status: 201 Created

4. **Add Admin:**
   - Endpoint: `POST /admins`
   - Purpose: Allow admins to add a new admin user.
   - Request Body (similar to adding a user)
   - Response: None
   - HTTP Status: 201 Created

5. **Change Task Status:**
   - Endpoint: `PUT /tasks/{taskId}/status`
   - Purpose: Allow admins to change the status of a task.
   - Request Body:
     ```json
     {
       "status": "In Progress"
     }
     ```
   - Response: None
   - HTTP Status: 200 OK

6. **View All Tasks:**
   - Endpoint: `GET /tasks`
   - Purpose: Allow admins to view all tasks.
   - Response: List of tasks (paginated)

7. **View My Tasks:**
   - Endpoint: `GET /tasks/my`
   - Purpose: Allow employees to view their assigned tasks.
   - Response: List of tasks (paginated)

8. **Get Tasks with Filtering:**
   - Endpoint: `GET /tasks`
   - Purpose: Allow admins to filter tasks based on status.
   - Request Parameters:
     - `status` (String, optional)
   - Response: List of tasks based on the filter

9. **Delete Task:**
   - Endpoint: `DELETE /tasks/{taskId}`
   - Purpose: Allow admins to delete a task.
   - Response: None
   - HTTP Status: 204 No Content

10. **Delete User:**
    - Endpoint: `DELETE /users/{userId}`
    - Purpose: Allow admins to delete a user.
    - Response: None
    - HTTP Status: 204 No Content

11. **Update Task:**
    - Endpoint: `PUT /tasks/{taskId}`
    - Purpose: Allow admins to update task details.
    - Request Body:
      ```json
      {
        "taskDesc": "Updated Task Description",
        "status": "Completed",
        "dueDate": "2024-01-20"
      }
      ```
    - Response: None
    - HTTP Status: 200 OK

12. **Assign Task:**
    - Endpoint: `PUT /tasks/{taskId}/assign/{employeeId}`
    - Purpose: Allow admins to assign a task to an employee.
    - Response: None
    - HTTP Status: 200 OK
