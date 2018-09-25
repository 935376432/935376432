/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package syslog.nio.datagramchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class SyslogTest {
    // UDP协议服务端
    private int port = 514;
    private DatagramChannel channel;
    private Selector selector = null;


    public void service() throws IOException {
        selector = Selector.open();
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(port));
        channel.register(selector, SelectionKey.OP_READ);
        while (selector.select() > 0) {
            /* 得到已经被捕获了的SelectionKey的集合 */
            Iterator<?> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) iterator.next();
                    iterator.remove();

                    if (key.isReadable()) {
                        DatagramChannel sc = (DatagramChannel) key.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        buf.clear();
                        sc.receive(buf);

                        buf.flip();
                        while (buf.hasRemaining()) {
                            System.out.print(new String(buf.array()));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        if (key != null) {
                            key.cancel();
                            key.channel().close();
                        }
                    } catch (ClosedChannelException cex) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    synchronized public void reveice(SelectionKey key) throws IOException {
        if (key == null) {
            return;
        }
        DatagramChannel sc = (DatagramChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        sc.receive(buf);

        buf.flip();
        while (buf.hasRemaining()) {
            System.out.print(new String(buf.array()));
        }
    }


    public static void main(String[] args) throws IOException {
        new DatagramChannelServerDemo().service();
    }
}
