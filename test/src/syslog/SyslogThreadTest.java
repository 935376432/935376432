/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package syslog;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyslogThreadTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ip = null;
        int count = 0;
        int i = 0;
        while (scanner.hasNext()) {
            if ( i == 0) {
                ip= scanner.next().toString();
                i++;
            }
            if (i == 1) {
                count = Integer.parseInt(scanner.next().toString());
                break;
            }
        }
        System.out.println(ip);
        System.out.println(count);
        final String server = ip;
        start(server,count);
        scanner.close();
    }

    private static void start(final String server, int count) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(server);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= count; i++) {
            final int devCount  = i ;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(server);
                        System.out.println(devCount);
                        DatagramSocket scoket = new DatagramSocket();
                        int size = 0;
                        while (size < 100) {
                            //String logsys = "<2> " + sdf.format(new Date()) + " fw" + devCount + " FW 1 1 fw1 10.1.1.50 NARI DCD 1 1 90";
                            String logsys = "<2> "+ sdf.format(new Date()) +" ids01 IDS 0 PPLive在线流媒体播放 192.168.10.244 138 192.168.10.255 138";
                            System.out.println(logsys);
                            byte[] by = logsys.getBytes("UTF-8");
                            DatagramPacket packet =
                                new DatagramPacket(by, logsys.length(), InetAddress.getByName(server), 514);
                            scoket.send(packet);
                            Thread.sleep(1000);
                            size++;
                            System.out.println(Thread.currentThread().getName() + ":" + size);
                        }
                        scoket.close();
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.toString());
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
