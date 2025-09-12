package com.rperotta.autolog.controller;

import com.rperotta.autolog.dto.UserCreationDTO;
import com.rperotta.autolog.dto.UserResponseDTO;
import com.rperotta.autolog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operations related to users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreationDTO userCreationDTO) {
        return ResponseEntity.ok(userService.addUser(userCreationDTO));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a User", description = "Removes a user from the database by its ID")
    //TODO: send a response about the deletion (success/didn't find the user)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
