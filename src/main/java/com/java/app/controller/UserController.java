package com.java.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.app.Service.UserService;
import com.java.app.dto.ApiResponse;
import com.java.app.dto.UserDetailsResponse;
import com.java.app.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 🔹 Get all users
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        return ResponseEntity.ok(
            new ApiResponse<>("success", "User list fetched", userService.getAllUsers()));
    }

    // 🔹 Get detailed info of a user
    @GetMapping("/{userId}/details")
    public ResponseEntity<ApiResponse<UserDetailsResponse>> getUserDetails(@PathVariable Long userId) {
        UserDetailsResponse response = userService.getUserDetailsStructured(userId);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "User not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", "User details fetched", response));
    }

    // 🔹 Get user by ID (simple user entity)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "User not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", "User fetched", user));
    }

    // 🔹 Update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "User not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", "User updated", user));
    }

    // 🔹 Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", "User not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", "User deleted successfully", null));
    }
}
