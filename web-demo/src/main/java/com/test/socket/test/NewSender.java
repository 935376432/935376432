/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.socket.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NewSender {
    public static void main(String[] args) throws Exception {
        System.out.println("发送端启动了");
        DatagramSocket socket = new DatagramSocket();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 包装键盘输入流
        String str = null;
        while ((str = br.readLine()) != null) {
            byte[] buff = str.getBytes();
            DatagramPacket packet =
                new DatagramPacket(buff, buff.length, InetAddress.getByName("DESKTOP-A7PIVA9"), 9000);
            socket.send(packet);
        }

        br.close();
        socket.close();

    }
}
