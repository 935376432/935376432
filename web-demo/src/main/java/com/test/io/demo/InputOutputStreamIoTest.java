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


    //用字节流,写文件
    //void write(byte[] b )  注意,它的返回类型是void
    //void write(byte[] b, int off, int len)
    //void write(int b) 将指定字节写入此文件输入流

    //FileInputStream 的 read 方法
    //int read() 从输入流中读取数据的下一个字节。返回 0 到 255 范围内的 int 字节值,如果到达流的末尾，则返回 -1
    //read(byte[] b)  从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中 ,返回读到总字节数,如果到头返回-1
    //read(byte[] b, int off, int len)
    static void streamDemo() throws Exception{
        OutputStream fos=new FileOutputStream("E:\\fos.txt");
      //fos.write("这是要输出的内容");  //出错,这是字符流的写法

        fos.write("this is 这是要输出的内容".getBytes()); //不用flush 或 close 也能写入,字节流不缓冲

        fos.close();

        InputStream fis=new FileInputStream("E:\\fos.txt");

        int ch=0;
        /*ch=fis.read();
        System.out.println((char)ch);*/

        while((ch=fis.read())!=-1){
            System.out.print((char)ch);  //可以看到,除了前面 的this is ,其余的都是乱码
        }

        fis.close();
    }

    // 例二 用字节数组接收读到的内容
    static void stareamDemo2()throws Exception{
        InputStream fis=new FileInputStream("E:/fos.txt");
        byte [] buff=new byte [1024];
        int len=0;
            while((len=fis.read(buff))!=-1){
                String str=new String(buff,0,len);  //这里有可能构造出来的串中有乱码
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

    //用字节流实现拷贝图片
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
