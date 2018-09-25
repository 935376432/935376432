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

    //����һ���ļ�,д�뼸���ַ�
    //FileWriter ����д���ַ�����Ҫд��ԭʼ�ֽ������뿼��ʹ�� FileOutputStream
    static void fileWriterDemo() throws IOException{
        Writer f=new FileWriter("E:/1.txt"); //���Է���,�����Զ�����1.txt

        f.write("����д����ַ� ������һ��");  //ע��,�����л����
        f.write("\r\nһ��������");
        f.flush();  //ˢ������

        f.close();

        //f.write("sss");  //ava.io.IOException: Stream closed

        //������һ����,�Ͳ��������ֶ���,java�����Ķ���,���в���ϵͳ�����ĳ����Դ
        System.out.println("----");
    }

    static void readDemo() throws IOException{
        char[] buff=new char[100] ;  //ע������ĳ���
        Reader r=new FileReader("E:/1.txt");

        int len=r.read(buff,0,10);  // ����ֵ�Ƕ���������  �����10,ָ��Ҫ�������ٸ�

        r.close();

        System.out.println("�������ַ���"+len);  //10

        for (int i = 0; i < buff.length; i++) {
            System.out.print(buff[i]);
        }
    }

    //ѭ����ȡ
    static void readDemo2() throws IOException{
        Reader r = new FileReader("E:/1.txt");
        int ch=0;
        while((ch=r.read())!=-1){
            System.out.print((char)ch);
        }
        r.close();
    }
    //ͨ������������һ���ļ�,��c�̵�d��
    public static void buffFileCopyDemo() throws IOException    {
            FileReader fr=new FileReader("E:\\1.txt");
            FileWriter fw=new FileWriter("E:\\desc.txt");

            BufferedReader br=new BufferedReader(fr);
            BufferedWriter bw=new BufferedWriter(fw);

            String str=null;
            while((str=br.readLine())!=null){
                bw.write(str);
              bw.newLine(); //����һ�����з�
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
