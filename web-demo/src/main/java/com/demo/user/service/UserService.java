/*
 *jiji java
 */
package com.demo.user.service;

import com.demo.user.entity.User;

public interface UserService {


    /**
     * �����û�
     * @param user �û���Ϣ
     * @return �û���Ϣ
     */
    User createUser(User user);

    /**
     * �޸��û�
     * @param user �û���Ϣ
     * @return �û���Ϣ
     */
    User updateUser(User user);

    /**
     * ɾ���û�
     * @param user �û���Ϣ
     * @return �û���Ϣ
     */
    User deleteUser(User user);


}
