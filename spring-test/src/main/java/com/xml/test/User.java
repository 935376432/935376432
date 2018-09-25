/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.xml.test;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * xmlӳ�������--�û�ʵ��
 * @author congzhe
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML�ļ��еĸ���ʶ
@XmlRootElement(name = "User")
// ����JAXB ���������Ժ��ֶε�����
@XmlType(propOrder = {
        "userId",
        "userName",
        "password",
        "birthday",
        "money",
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // �û�Id
    private int userId;
    // �û���
    private String userName;
    // �û�����
    private String password;
    // �û�����
    private Date birthday;
    // �û�Ǯ��
    private double money;

    public User() {
        super();
    }

    public User(int userId, String userName, String password, Date birthday,
            double money) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.money = money;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User [birthday=" + birthday + ", money=" + money
                + ", password=" + password + ", userId=" + userId
                + ", userName=" + userName + "]";
    }

}
