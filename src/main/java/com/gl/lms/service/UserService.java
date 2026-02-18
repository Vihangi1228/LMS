package com.gl.lms.service;

import com.gl.lms.entity.Users;
import com.gl.lms.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

    // Find a user by username
    public Users findByUsername(String username) {
        Optional<Users> user = userRepository.findByName(username);
        return user.orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    // Update a user by ID
    public Users update(Integer id, Users updatedUser) {
        Optional<Users> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            Users u = existingUser.get();
            u.setName(updatedUser.getName());
            u.setEmail(updatedUser.getEmail());
            u.setRole(updatedUser.getRole());
            // add other fields as needed
            return userRepository.save(u);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
