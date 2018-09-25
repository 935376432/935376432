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
        IntBuffer buf = IntBuffer.allocate(10) ;    // 准备出10个大小的缓冲区
        System.out.print("1、写入数据之前的position、limit和capacity：") ;
        System.out.println("position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf.capacity()) ;
        int temp[] = {5,7,9} ;              // 定义一个int数组
        buf.put(3) ;                        // 设置一个数据
        buf.put(temp) ;                     // 此时已经存放了四个记录
        System.out.print("2、写入数据之后的position、limit和capacity：") ;
        System.out.println("position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf.capacity()) ;

        buf.flip() ;                        // 重设缓冲区  postion = 0 ,limit = 原本position
        System.out.print("3、准备输出数据时的position、limit和capacity：") ;
        System.out.println("position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf.capacity()) ;
        System.out.print("缓冲区中的内容：") ;
        while(buf.hasRemaining()){           //如果缓冲区中还有内容
            int x = buf.get() ;
            System.out.print(x + "、") ;
        }
        *//** 输出结果：
            1、写入数据之前的position、limit和capacity：position = 0，limit = 10，capacty = 10
            2、写入数据之后的position、limit和capacity：position = 4，limit = 10，capacty = 10
            3、准备输出数据时的position、limit和capacity：position = 0，limit = 4，capacty = 10
                        缓冲区中的内容：3、5、7、9、
         * @throws UnknownHostException
         *//*
    }*/

    public static void main(String args[]) throws UnknownHostException{
        IntBuffer buf = IntBuffer.allocate(10) ;    // 准备出10个大小的缓冲区
        IntBuffer read = null ;    // 定义子缓冲区
        for(int i=0;i<10;i++){
            buf.put(2 * i + 1) ;    // 在主缓冲区中加入10个奇数
        }
        read = buf.asReadOnlyBuffer()  ;// 创建只读缓冲区



        read.flip() ;    // 重设缓冲区
        System.out.print("主缓冲区中的内容：") ;
        while(read.hasRemaining()){
            int x = read.get() ;
            System.out.print(x + "、") ;
        }
        //read.put(30) ;    // 修改，错误
        /**
         *  主缓冲区中的内容：1、3、5、7、9、11、13、15、17、19、Exception in thread "main" java.nio.ReadOnlyBufferException
            at java.nio.HeapIntBufferR.put(Unknown Source)
            at com.test.io.demo.BufferTestDemo.main(BufferTestDemo.java:53)
         */
        //取得百度的地址
        InetAddress []  ipList=InetAddress.getAllByName("www.baidu.com");
        for(InetAddress addr:ipList){
            System.out.println(addr.toString());
        }
        InetAddress netAddr2=InetAddress.getByName("DELL-PC");  //这里也可以传IP地址
        System.out.println(netAddr2.getHostAddress());  //169.254.170.71
        System.out.println(netAddr2.getHostName());
    }

}
