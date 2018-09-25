package com.demo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userRepository.findAll();
        for (int i = 0, len = userList.size(); i < len; i++) {
            User user = userList.get(i);
            if (user.getLoginName().equals(username)) {
                return null;
            }
        }
        throw new UsernameNotFoundException("用户不存在！");
    }

}