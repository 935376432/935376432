/*
 *jiji java
 */
package com.test.ukey;

import java.security.MessageDigest;

public class MainTest {


    public static String byte2HexString(byte[] bs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bs.length; i++) {
            String hex = Integer.toHexString(bs[i] & 0xff);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] userPINDigest = md5.digest("weide5".getBytes());
        String actualPIN = byte2HexString(userPINDigest).substring(0, 16);
        System.out.println(actualPIN);
    }


}
