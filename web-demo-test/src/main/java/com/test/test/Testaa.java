/*
 * Copyright (c) 2015-2017, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.test.test;

import java.io.InputStream;

public class Testaa {

    public static void main(String[] args) throws Exception {
        String aa = "123456";


        //System.out.println(aa.substring(0, aa.length() - 1));
        Process p = Runtime.getRuntime().exec(new String[] {"ping","10.10.66.45"});
        p.waitFor();
        InputStream is = p.getInputStream();
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String str = null;
        while((str = reader.readLine()) != null) {
            sb.append(str);
            sb.append("\n");
        }
        System.out.println(sb.toString());*/



    }

}
