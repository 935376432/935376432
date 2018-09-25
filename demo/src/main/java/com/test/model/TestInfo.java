package com.test.model;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.springframework.ldap.odm.annotations.Id;

/**
 * 测试实体
 */

/*@Entity
@Table(name = "tbl_test")
@JsonInclude(Include.NON_EMPTY)*/
public class TestInfo implements Serializable {

    /** 序列化ID */
    private static final long serialVersionUID = -3114125379349498429L;

    /** id 自增主键 */
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String name;


    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "extra")
    @Type(type = "com.test.bean.JsonbType")
    private Map<String, Object> extra = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }





}