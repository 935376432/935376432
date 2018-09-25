/*
 *jiji java
 */
package syslog.nio.tcp.test;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class TcpClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            startClient("10.10.66.33", 9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startClient(String serverIp, int serverPort) throws IOException{
        /*
         * ����һ��SocketChannel��ָ��Ϊ������ģʽ
         * A selectable channel for stream-oriented connecting sockets.
         */
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        /*
         * ���ӵ�ָ���ķ����ַ
         */
        socketChannel.connect(new InetSocketAddress(serverIp, serverPort));

        /*
         * ����һ���¼�ѡ����Selector
         */
        Selector selector = Selector.open();

        /*
         * ��������SocketChannelע�ᵽָ����Selector�ϣ���ָ����ע���¼�����ΪOP_CONNECT
         */
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        /*
         * ��ϵͳ�����ն˶�ȡ���ݣ���Ϊ�ͻ�����Ϣ����Դ
         */
        Scanner sc = new Scanner(System.in);
        String cont = null;
        while(true){
            if(socketChannel.isConnected()){
                cont = sc.nextLine();
                socketChannel.write(Charset.forName("UTF-8").encode(cont));
                if(cont == null || cont.equalsIgnoreCase("clientClose")){
                    socketChannel.close();
                    selector.close();
                    sc.close();
                    System.out.println("See you, �ͻ����˳�ϵͳ��");
                    System.exit(0);
                }
            }
            /*
             * ����1sec�ĳ�ʱʱ�䣬����IO�¼�ѡ�����
             */
            int nSelectedKeys = selector.select(500000);
            if(nSelectedKeys > 0){
                for(SelectionKey skey: selector.selectedKeys()){
                    /*
                     * �жϼ�⵽��channel�ǲ��ǿ����ӵģ�����Ӧ��channelע�ᵽѡ�����ϣ�ָ�����ĵ��¼�����ΪOP_READ
                     */
                    if(skey.isConnectable()){
                        SocketChannel connChannel = (SocketChannel) skey.channel();
                        connChannel.configureBlocking(false);
                        connChannel.register(selector, SelectionKey.OP_READ);
                        connChannel.finishConnect();
                    }
                    /*
                     * ����⵽��IO�¼��Ƕ��¼�������������ݵĶ���ص�ҵ���߼�
                     */
                    else if(skey.isReadable()){
                        SocketChannel readChannel = (SocketChannel) skey.channel();
                        StringBuilder sb = new StringBuilder();
                        /*
                         * ����һ��ByteBuffer������������Ϊ1k
                         */
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        int readBytes = 0;
                        int ret = 0;
                        /*
                         * ע�⣬��ByteBuffer�Ĳ�������Ҫ���ĵ���flip��clear�ȡ�
                         */
                        while ((ret = readChannel.read(byteBuffer)) > 0) {
                            readBytes += ret;
                            byteBuffer.flip();
                            sb.append(Charset.forName("UTF-8").decode(byteBuffer).toString());
                            byteBuffer.clear();
                        }

                        if (readBytes == 0) {
                            System.err.println("handle opposite close Exception");
                            readChannel.close();
                        } else {
                            System.out.println(sb.toString());
                        }
                    }
                }
                /*
                 * һ�μ������¼�������Ϻ���Ҫ���Ѿ���¼���¼��������׼����һ�ֵ��¼����
                 */
                selector.selectedKeys().clear();
            }else{
                System.err.println("handle select timeout Exception");
                socketChannel.close();
            }
        }
    }
}

