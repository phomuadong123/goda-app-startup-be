package com.goda.ai.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goda.ai.model.User;
import com.goda.ai.repository.UserRepository;
import com.goda.ai.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If user is found, return user information
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @PutMapping("/change-avatar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String changeAvatar(
        @RequestParam("avatar") MultipartFile[] avatar, 
        @RequestParam("userId") Long userId
    ){
        userService.changeAvatar(avatar, userId);
        return "Avatar change successfully!";
    }

    @GetMapping("/get-avatar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAvatar(
        @RequestParam Long userId
    ){
        String avatar = userService.getAvatar(userId); 
        return ResponseEntity.ok(avatar);
    }

}
