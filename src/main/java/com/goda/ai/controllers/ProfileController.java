package com.goda.ai.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goda.ai.model.User;
import com.goda.ai.payload.request.UpdateProfileRequest;
import com.goda.ai.repository.UserRepository;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable Long id,
            @Validated @RequestBody UpdateProfileRequest request) {

        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Update user profile with new data
        User user = optionalUser.get();
        user.setUsername(request.getName());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());

        // Save the updated user profile
        userRepository.save(user);

        return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
    }

}
