/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.io.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.IntBuffer;

public class BufferTestDemo {

    /*public static void main(String[] args) {
        IntBuffer buf = IntBuffer.allocate(10) ;    // ׼����10����С�Ļ�����
        System.out.print("1��д������֮ǰ��position��limit��capacity��") ;
        System.out.println("position = " + buf.position() + "��limit = " + buf.limit() + "��capacty = " + buf.capacity()) ;
        int temp[] = {5,7,9} ;              // ����һ��int����
        buf.put(3) ;                        // ����һ������
        buf.put(temp) ;                     // ��ʱ�Ѿ�������ĸ���¼
        System.out.print("2��д������֮���position��limit��capacity��") ;
        System.out.println("position = " + buf.position() + "��limit = " + buf.limit() + "��capacty = " + buf.capacity()) ;

        buf.flip() ;                        // ���軺����  postion = 0 ,limit = ԭ��position
        System.out.print("3��׼���������ʱ��position��limit��capacity��") ;
        System.out.println("position = " + buf.position() + "��limit = " + buf.limit() + "��capacty = " + buf.capacity()) ;
        System.out.print("�������е����ݣ�") ;
        while(buf.hasRemaining()){           //����������л�������
            int x = buf.get() ;
            System.out.print(x + "��") ;
        }
        *//** ��������
            1��д������֮ǰ��position��limit��capacity��position = 0��limit = 10��capacty = 10
            2��д������֮���position��limit��capacity��position = 4��limit = 10��capacty = 10
            3��׼���������ʱ��position��limit��capacity��position = 0��limit = 4��capacty = 10
                        �������е����ݣ�3��5��7��9��
         * @throws UnknownHostException
         *//*
    }*/

    public static void main(String args[]) throws UnknownHostException{
        IntBuffer buf = IntBuffer.allocate(10) ;    // ׼����10����С�Ļ�����
        IntBuffer read = null ;    // �����ӻ�����
        for(int i=0;i<10;i++){
            buf.put(2 * i + 1) ;    // �����������м���10������
        }
        read = buf.asReadOnlyBuffer()  ;// ����ֻ��������



        read.flip() ;    // ���軺����
        System.out.print("���������е����ݣ�") ;
        while(read.hasRemaining()){
            int x = read.get() ;
            System.out.print(x + "��") ;
        }
        //read.put(30) ;    // �޸ģ�����
        /**
         *  ���������е����ݣ�1��3��5��7��9��11��13��15��17��19��Exception in thread "main" java.nio.ReadOnlyBufferException
            at java.nio.HeapIntBufferR.put(Unknown Source)
            at com.test.io.demo.BufferTestDemo.main(BufferTestDemo.java:53)
         */
        //ȡ�ðٶȵĵ�ַ
        InetAddress []  ipList=InetAddress.getAllByName("www.baidu.com");
        for(InetAddress addr:ipList){
            System.out.println(addr.toString());
        }
        InetAddress netAddr2=InetAddress.getByName("DELL-PC");  //����Ҳ���Դ�IP��ַ
        System.out.println(netAddr2.getHostAddress());  //169.254.170.71
        System.out.println(netAddr2.getHostName());
    }

}
