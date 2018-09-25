/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.io.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class InputOutputStreamIoTest {


    //���ֽ���,д�ļ�
    //void write(byte[] b )  ע��,���ķ���������void
    //void write(byte[] b, int off, int len)
    //void write(int b) ��ָ���ֽ�д����ļ�������

    //FileInputStream �� read ����
    //int read() ���������ж�ȡ���ݵ���һ���ֽڡ����� 0 �� 255 ��Χ�ڵ� int �ֽ�ֵ,�����������ĩβ���򷵻� -1
    //read(byte[] b)  ���������ж�ȡһ���������ֽڣ�������洢�ڻ��������� b �� ,���ض������ֽ���,�����ͷ����-1
    //read(byte[] b, int off, int len)
    static void streamDemo() throws Exception{
        OutputStream fos=new FileOutputStream("E:\\fos.txt");
      //fos.write("����Ҫ���������");  //����,�����ַ�����д��

        fos.write("this is ����Ҫ���������".getBytes()); //����flush �� close Ҳ��д��,�ֽ���������

        fos.close();

        InputStream fis=new FileInputStream("E:\\fos.txt");

        int ch=0;
        /*ch=fis.read();
        System.out.println((char)ch);*/

        while((ch=fis.read())!=-1){
            System.out.print((char)ch);  //���Կ���,����ǰ�� ��this is ,����Ķ�������
        }

        fis.close();
    }

    // ���� ���ֽ�������ն���������
    static void stareamDemo2()throws Exception{
        InputStream fis=new FileInputStream("E:/fos.txt");
        byte [] buff=new byte [1024];
        int len=0;
            while((len=fis.read(buff))!=-1){
                String str=new String(buff,0,len);  //�����п��ܹ�������Ĵ���������
                System.out.print(str);
        }

        fis.close();
    }

    static void streamDemo3() throws Exception{
        InputStream fis = new FileInputStream("E:/fos.txt");
        byte [] buff = new byte [fis.available()];
        fis.read(buff);
        String str = new String(buff);
        System.out.println(str);
        fis.close();
    }

    //���ֽ���ʵ�ֿ���ͼƬ
    public static void pictureCopyDemo() throws IOException{
        FileInputStream fis = new FileInputStream("E:\\src.bmp");
        FileOutputStream fos = new FileOutputStream("E:\\desc.bmp");
        byte[] buff=new byte[fis.available()];

        fis.read(buff);
        fos.write(buff);

        fis.close();
        fos.close();
    }

    public static void method1(){
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("E:/fos.txt","rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }

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

    public static void main(String[] args) throws Exception {
        //streamDemo();
        //stareamDemo2();
        //streamDemo3();
        method1();
    }
}
