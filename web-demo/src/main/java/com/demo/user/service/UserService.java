/*
 *jiji java
 */
package com.demo.user.service;

import com.demo.user.entity.User;

public interface UserService {


    /**
     * 保存用户
     * @param user 用户信息
     * @return 用户信息
     */
    User createUser(User user);

    /**
     * 修改用户
     * @param user 用户信息
     * @return 用户信息
     */
    User updateUser(User user);

    /**
     * 删除用户
     * @param user 用户信息
     * @return 用户信息
     */
    User deleteUser(User user);


}
