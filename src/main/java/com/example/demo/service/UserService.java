package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exception.DuplicateEmailException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already in use");
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with the ID:" + id));

        // Prevent changing the email to an existing one
        if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(userDetails.getEmail()) != null) {
                throw new DuplicateEmailException("Email already in use.");
            }
        }

        if (userDetails.getName() != null) {
            user.setName(userDetails.getName());
        }
        if (userDetails.getAge() != null) {
            user.setAge(userDetails.getAge());
        }

        if (userDetails.getGender() != null) {
            user.setGender(userDetails.getGender());
        }

        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }

        return userRepository.save(user);
    }
}
