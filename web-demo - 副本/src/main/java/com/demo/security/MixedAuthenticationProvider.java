/*
 *jiji java
 */
package com.demo.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;

/**
 * �����֤��
 */

public class MixedAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    /**
     * ���ʺź����������֤
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
        UsernamePasswordAuthenticationToken auth) throws AuthenticationException {
        // TODO Auto-generated method stub
        //�����������ṩ
        if (auth.getCredentials() == null) {
            System.out.println("��¼�����������");
            throw new BadCredentialsException("��½�����������");
        }

        String userName = userDetails.getUsername();
        String userPwd = userDetails.getPassword();

        User user = userRepository.findOneByLoginName(userName);
        if (user == null) {
            throw new BadCredentialsException("��½�����������");
        }
        if (!userPwd.equals(user.getPassword())) {
            throw new BadCredentialsException("��½�����������");
        }

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        User user = userRepository.findOneByLoginName(username);
        MixAuthUser mUser = new MixAuthUser();
        if (user != null) {
            mUser.setUsername(user.getLoginName());
            mUser.setPassword(user.getPassword());
            ArrayList<Authority> authors = new ArrayList<>();
            authors.add(new Authority("AUTH_USER"));
            mUser.setAuthorities(authors);
        } else {
            mUser.setUsername(username);
        }
        return mUser;
    }

}
