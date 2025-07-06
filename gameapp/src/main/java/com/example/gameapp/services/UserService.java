package com.example.gameapp.services;

import com.example.gameapp.models.User;
import com.example.gameapp.models.enums.UserRole;
import com.example.gameapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void promoteToReviewer(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        if (user.getRole() == UserRole.USER) {
            user.setRole(UserRole.REVIEWER);
            userRepository.save(user);
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }



}
