/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.io.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class IoAndNioTest {

    /**
     * �ô�ͳ��io
     */
    public static void method2(){
        InputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream("E:/fos.txt"));

            byte [] buf = new byte[in.available()];
            in.read(buf);
            String str=new String(buf);  //�����п��ܹ�������Ĵ���������
            System.out.print(str);
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * ��nio
     */
    public static void method1(){
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("E:/fos.txt","rw");
            //���� new FileInputStream("E:/fos.txt").getChannel();
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while(bytesRead != -1)
            {
                //flip()��Buffer������ģʽ��дģʽ�Ͷ�ģʽ����дģʽ�µ���flip()֮��Buffer��дģʽ��ɶ�ģʽ��
                //��ôlimit�����ó���position��ǰ��ֵ(����ǰд�˶�������)��postion�ᱻ��Ϊ0���Ա�ʾ�������ӻ����ͷ��ʼ����mark��Ϊ-1��
                //Ҳ����˵����flip()֮�󣬶�/дָ��positionָ��������ͷ�����������������ֻ�ܶ���֮ǰд������ݳ���(���������������������С)��
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }
                // write��������ֻд���˲������ݣ�buffer�ﻹ��ʣ�ࡣ
                //ѹ��һ�£��Ѻ�һ�ε�����Ų��ǰ�档ָ��ҲŲ����Ч���ݵĺ�һλ��
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //method2();
        method1();
    }
}
