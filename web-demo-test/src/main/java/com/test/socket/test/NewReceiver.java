/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.socket.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class NewReceiver{
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        DatagramSocket socket = new DatagramSocket(9000);
        System.out.println("接收端启动了");

        while (true) {
            byte[] buff = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);

            String senderIp = packet.getAddress().getHostAddress();
            int sendPort = packet.getPort();
            String msg = new String(packet.getData(), 0, packet.getLength());

            System.out.println(senderIp + ":" + sendPort + ":" + msg);
        }
    }
}
