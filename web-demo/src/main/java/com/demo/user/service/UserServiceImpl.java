/*
 *jiji java
 */
package com.demo.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User deleteUser(User user) {
        userRepository.delete(user);
        return user;
    }



}
