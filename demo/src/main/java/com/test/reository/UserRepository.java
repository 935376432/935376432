/*
 *jiji java
 */
package com.test.reository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.test.model.User;


/**
 *  数据库访问实体
 * @author congzhe
 */
public interface UserRepository  extends PagingAndSortingRepository<User,Long>,
JpaSpecificationExecutor<User>,JpaRepository<User,Long>{


}
