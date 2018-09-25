/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.io.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScattingAndGather {
    public static void main(String args[]) {
        gather();
    }

    public static void gather() {
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(10);

        byte[] b1 = { 0, 9 };
        byte[] b2 = { '2', '3' };
        header.put(b1);
        body.put(b2);

        ByteBuffer[] buffs = { header, body };

        try {
            @SuppressWarnings("resource")
            FileOutputStream os = new FileOutputStream("E:/ScattingAndGather.txt");
            FileChannel channel = os.getChannel();
            channel.write(buffs);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
