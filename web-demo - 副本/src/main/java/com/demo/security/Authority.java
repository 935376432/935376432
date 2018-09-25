package com.demo.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 创建一个 Authority ，实现自 org.springframework.security.core.GrantedAuthority 类，
 * getAuthority 方法只返回一个表示权限名称的字符串，如 AUTH_USER 、 AUTH_ADMIN 、 AUTH_DBA 等。
 *
 */

public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public Authority() {  }
    public Authority(String authority) {
        this.setAuthority(authority);
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}