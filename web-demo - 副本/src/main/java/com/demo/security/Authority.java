package com.demo.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * ����һ�� Authority ��ʵ���� org.springframework.security.core.GrantedAuthority �࣬
 * getAuthority ����ֻ����һ����ʾȨ�����Ƶ��ַ������� AUTH_USER �� AUTH_ADMIN �� AUTH_DBA �ȡ�
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