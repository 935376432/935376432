/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.io.demo.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class HansServer {
    // ���ڼ������Channel״̬��Selector
    private Selector selector = null;

    public void init() throws IOException {
        selector = Selector.open();
        // ͨ��open��������һ��δ�󶨵�ServerSocketChannelʵ��
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("10.10.66.33", 30000);
        // ����ServerSocketChannel�󶨵�ָ��IP��ַ
        server.socket().bind(isa);
        // ����ServerSocket�Է�������ʽ����
        server.configureBlocking(false);
        // ��serverע�ᵽָ��Selector����
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            // ���δ���selector�ϵ�ÿ����ѡ���SelectionKey
            for (SelectionKey sk : selector.selectedKeys()) {
                // ��selector�ϵ���ѡ��Key����ɾ�����ڴ����SelectionKey
                selector.selectedKeys().remove(sk);
                // ���sk��Ӧ��ͨ�������ͻ��˵���������
                if (sk.isAcceptable()) {
                    // ����accept�����������ӣ������������˶�Ӧ��SocketChannel
                    SocketChannel sc = server.accept();
                    // ���ò��÷�����ģʽ
                    sc.configureBlocking(false);
                    // ����SocketChannelҲע�ᵽselector
                    sc.register(selector, SelectionKey.OP_READ);
                }
                // ���sk��Ӧ��ͨ����������Ҫ��ȡ
                if (sk.isReadable()) {
                    // ��ȡ��SelectionKey��Ӧ��Channel����Channel���пɶ�������
                    SocketChannel sc = (SocketChannel) sk.channel();
                    // ����׼��ִ�ж�ȡ���ݵ�ByteBuffer
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    // ��ʼ��ȡ����
                    try {
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            content += StandardCharsets.UTF_8.decode(buff);
                        }
                        // ��ӡ�Ӹ�sk��Ӧ��Channel���ȡ��������
                        System.out.println("=====" + content);
                    }
                    // �����׽����sk��Ӧ��Channel�������쳣����������Channel
                    // ��Ӧ��Client���������⣬���Դ�Selector��ȡ��sk��ע��
                    catch (IOException ex) {
                        // ��Selector��ɾ��ָ����SelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }
                    // ���content�ĳ��ȴ���0����������Ϣ��Ϊ��
                    if (content.length() > 0) {
                        // ������selector��ע�������SelectKey
                        for (SelectionKey key : selector.keys()) {
                            // ��ȡ��key��Ӧ��Channel
                            Channel targetChannel = key.channel();
                            // �����channel��SocketChannel����
                            if (targetChannel instanceof SocketChannel) {
                                // ������������д���Channel��
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(StandardCharsets.UTF_8.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new HansServer().init();
    }
}
