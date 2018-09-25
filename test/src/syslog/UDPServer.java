package syslog;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) throws Throwable {
        // TODO Auto-generated method stub
        DatagramSocket datagramSocket = new DatagramSocket(514);
        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[512], 512);
            try {
                datagramSocket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                byte[] b = msg.getBytes("UTF-8");
                String n = new String(b,"UTF-8");
                System.out.println(packet.getAddress() + "/" + packet.getPort() + ":" + n);
                packet.setData("I am server!!!".getBytes());
                datagramSocket.send(packet);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}