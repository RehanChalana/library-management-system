package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.model.User;
import com.rehan.librarymanagementsystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public List<User> findAll() {
         return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
