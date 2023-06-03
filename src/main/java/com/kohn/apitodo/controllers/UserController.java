package com.kohn.apitodo.controllers;

import com.kohn.apitodo.dto.UserCreateDto;
import com.kohn.apitodo.dto.UserDto;
import com.kohn.apitodo.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserDto createdUserDto = userService.createUser(userCreateDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
//        // TODO: Call userService to get user and return response
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
//        // TODO: Call userService to update user and return response
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        // TODO: Call userService to delete user and return response
//        return null;
//    }
}
