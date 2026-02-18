package com.gl.lms.controller;

import com.gl.lms.entity.Role;
import com.gl.lms.entity.Users;
import com.gl.lms.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Only ADMIN or the user themselves can update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,
                                        @RequestBody Users updatedUser,
                                        Authentication authentication) {

        Users currentUser = userService.findByUsername(authentication.getName());

        if (!currentUser.getRole().equals(Role.ROLE_ADMIN)
                && !currentUser.getId().equals(id)) {
            throw new AccessDeniedException("Unauthorized");
        }

        return ResponseEntity.ok(userService.update(id, updatedUser));
    }
}
