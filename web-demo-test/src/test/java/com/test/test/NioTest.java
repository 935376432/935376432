/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

public class NioTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    //�ͻ���
    @Test
    public void client() throws Exception {
        //1. ��ȡsocketChannel
        SocketChannel sChannel = SocketChannel.open();
        //2. ��������
        sChannel.connect(new InetSocketAddress("10.10.66.33", 9898));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3. ����ͨ��Ϊ������
        sChannel.configureBlocking(false);

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();

            buf.put((new Date() + "��" + msg).getBytes());
            buf.flip();
            //4. ��ͨ��д����
            sChannel.write(buf);
            buf.clear();
        }
    }

    @Test
    //�����
    public void server() throws Exception {
        //1. ��ȡ�����ͨ��
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.bind(new InetSocketAddress(9898));
        //2. ����Ϊ������ģʽ
        ssChannel.configureBlocking(false);

        //3. ��һ��������
        Selector selector = Selector.open();
        //4. �������ע������¼�
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            //5. ��ȡ�����������еļ����¼�ֵ
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            //6. �����ֵ
            while (it.hasNext()) {
                //7. ȡ��SelectionKey
                SelectionKey key = it.next();

                //8. ����keyֵ�ж϶�Ӧ���¼�
                if (key.isAcceptable()) {
                    //9. ���봦��
                    SocketChannel socketChannel = ssChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //10. �ɶ��¼�����
                    SocketChannel channel = (SocketChannel) key.channel();
                    readMsg(channel);
                }
                //11. �Ƴ���ǰkey
                it.remove();
            }
        }
    }

    private void readMsg(SocketChannel channel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int len = 0;
        while ((len = channel.read(buf)) > 0) {
            buf.flip();
            byte[] bytes = new byte[1024];
            buf.get(bytes, 0, len);
            System.out.println(new String(bytes, 0, len));
        }
    }

}
