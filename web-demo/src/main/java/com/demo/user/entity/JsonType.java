/*
 *jiji java
 */
package com.demo.user.entity;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

public class JsonType implements UserType,ParameterizedType {

    @Override
    public int[] sqlTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<?> returnedClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
        throws HibernateException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
        throws HibernateException, SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isMutable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        // TODO Auto-generated method stub

    }

}
