/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.xml.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlTest {

    public static void main(String[] args) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(User.class);    // ��ȡ�����Ķ���
        Marshaller marshaller = context.createMarshaller(); // ���������Ļ�ȡmarshaller����

    }

}
