/*
 *jiji java
 */
package com.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.user.entity.User;


/**
 *  ���ݿ����ʵ��
 * @author congzhe
 */
public interface UserRepository
    extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>, JpaRepository<User, Long> {

    User findOneByLoginName(String loginName);

}
