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
     * 用传统的io
     */
    public static void method2(){
        InputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream("E:/fos.txt"));

            byte [] buf = new byte[in.available()];
            in.read(buf);
            String str=new String(buf);  //这里有可能构造出来的串中有乱码
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
     * 用nio
     */
    public static void method1(){
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("E:/fos.txt","rw");
            //或者 new FileInputStream("E:/fos.txt").getChannel();
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while(bytesRead != -1)
            {
                //flip()：Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
                //那么limit就设置成了position当前的值(即当前写了多少数据)，postion会被置为0，以表示读操作从缓存的头开始读，mark置为-1。
                //也就是说调用flip()之后，读/写指针position指到缓冲区头部，并且设置了最多只能读出之前写入的数据长度(而不是整个缓存的容量大小)。
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }
                // write方法可能只写出了部分数据，buffer里还有剩余。
                //压缩一下，把后一段的数据挪到前面。指针也挪到有效数据的后一位。
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
