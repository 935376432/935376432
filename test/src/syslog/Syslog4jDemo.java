package syslog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Syslog4jDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub

        // Set a Specific Host, then Log to It

        /*SyslogIF syslog = Syslog.getInstance("udp");

        //syslog.getConfig().setHost("10.10.66.33");

        syslog.getConfig().setPort(514);
        */
        int i = 0;
        while (i < 15) {
            i++;
            /*String str = "Today is a good day!";
            byte[] b = str.getBytes("UTF-8");
            String n = new String(b,"UTF-8");
            syslog.info(n);*/
            //syslog.log(0, URLDecoder.decode(str,"UTF-8"));
            try {
                DatagramSocket ds = new DatagramSocket();
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket("test udp aaaaa".getBytes(), "test udp aaaaa".length(), InetAddress.getByName("10.10.66.33"), 514);
                ds.send(dp);
                System.out.println("test");
                ds.close();
                Thread.sleep(1000);
                send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static void send(){
        DatagramChannel channel = null;
        try{
            channel = DatagramChannel.open();
            String info = "I'm the Sender!";
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.clear();
            buf.put(info.getBytes());
            buf.flip();

            int bytesSent = channel.send(buf, new InetSocketAddress("10.10.66.33",514));
            System.out.println(bytesSent);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(channel!=null){
                    channel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}