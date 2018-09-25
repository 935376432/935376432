/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.io.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class WriterReaderIoTest {

    //创建一个文件,写入几个字符
    //FileWriter 用于写入字符流。要写入原始字节流，请考虑使用 FileOutputStream
    static void fileWriterDemo() throws IOException{
        Writer f=new FileWriter("E:/1.txt"); //可以发现,它能自动创建1.txt

        f.write("这是写入的字符 这是另一行");  //注意,它是有缓冲的
        f.write("\r\n一行新数据");
        f.flush();  //刷缓冲区

        f.close();

        //f.write("sss");  //ava.io.IOException: Stream closed

        //创建了一个流,就产生了两种东西,java产生的对象,还有操作系统本身的某种资源
        System.out.println("----");
    }

    static void readDemo() throws IOException{
        char[] buff=new char[100] ;  //注意数组的长度
        Reader r=new FileReader("E:/1.txt");

        int len=r.read(buff,0,10);  // 返回值是读到的数量  后面的10,指定要读出多少个

        r.close();

        System.out.println("读到的字符数"+len);  //10

        for (int i = 0; i < buff.length; i++) {
            System.out.print(buff[i]);
        }
    }

    //循环读取
    static void readDemo2() throws IOException{
        Reader r = new FileReader("E:/1.txt");
        int ch=0;
        while((ch=r.read())!=-1){
            System.out.print((char)ch);
        }
        r.close();
    }
    //通过缓冲流复制一个文件,从c盘到d盘
    public static void buffFileCopyDemo() throws IOException    {
            FileReader fr=new FileReader("E:\\1.txt");
            FileWriter fw=new FileWriter("E:\\desc.txt");

            BufferedReader br=new BufferedReader(fr);
            BufferedWriter bw=new BufferedWriter(fw);

            String str=null;
            while((str=br.readLine())!=null){
                bw.write(str);
              bw.newLine(); //插入一个换行符
                bw.flush();
            }

            br.close();
            bw.close();
        }
    public static void main(String[] args) throws IOException {
        //fileWriterDemo();
        //readDemo();
        //readDemo2();
        buffFileCopyDemo();
    }

}
