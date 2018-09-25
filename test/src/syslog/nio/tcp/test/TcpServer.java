/*
 *jiji java
 */
package syslog.nio.tcp.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;


public class TcpServer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            startServer(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startServer(int port) throws IOException{
        /*
         *����һ������channel��
         *A selectable channel for stream-oriented listening sockets.
         */
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(port));

        /*
         * ����һ��selector
         */
        Selector selector = Selector.open();
        /*
         * ��������serverChannelע�ᵽselectorѡ�����ϣ�ָ�����channelֻ����OP_ACCEPT�¼�
         */
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            /*
             * select()������Ĭ��������ģʽ�ģ�������û��accept����readʱ�䵽��ʱ����һֱ���������������ִ�С�
             */
            int readyChannels = selector.select();
            if (readyChannels <= 0) {
                continue;
            }

            /*
             * ��selector�ϻ�ȡ����IO�¼���������accept��Ҳ�п�����read
             */
            Set<SelectionKey> SelectonKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = SelectonKeySet.iterator();

            /*
             * ѭ������SelectionKeySet�е����е�SelectionKey
             */
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {          //����OP_ACCEPT�¼�
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {  //����OP_READ�¼�
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    StringBuilder sb = new StringBuilder();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    int readBytes = 0;
                    int ret = 0;
                    /*
                     * ע������ݵ�ʱ��ByteBuffer�Ĳ�������Ҫflip,clear����ָ��λ�õĵ���
                     */
                    while ((ret = socketChannel.read(byteBuffer)) > 0) {
                        readBytes += ret;
                        byteBuffer.flip();
                        sb.append(Charset.forName("UTF-8").decode(byteBuffer).toString());
                        byteBuffer.clear();
                    }

                    if (readBytes == 0) {
                        System.err.println("handle opposite close Exception");
                        socketChannel.close();
                    }

                    String message = sb.toString();
                    System.out.println("Message from client: " + message);
                    if ("clientClose".equalsIgnoreCase(message.toString().trim())) {
                        System.out.println("Client is going to shutdown!");
                        socketChannel.close();
                    } else if ("serverClose".equalsIgnoreCase(message.trim())) {
                        System.out.println("Server is going to shutdown!");
                        socketChannel.close();
                        serverChannel.close();
                        selector.close();
                        System.exit(0);
                    } else {
                        String outMessage = "Server response��" + message;
                        socketChannel.write(Charset.forName("UTF-8").encode(outMessage));
                    }
                }
                /*
                 * ��selector�ϵ�ǰ�Ѿ������������Ѿ������˵��¼�����������
                 */
                iterator.remove();
            }
        }
    }
}
