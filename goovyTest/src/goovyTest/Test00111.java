package goovyTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test00111 {

	public static void main(String[] args) throws ParseException {
		/*String mac = "0:11:25:10:1d:99";
		if(mac.indexOf(":") == 1){
			mac = "0" + mac;
		}
		String mac1 = "00:11:25:10:1d:99";
		System.out.println(mac);
		System.out.println(mac1);*/


	    /*String output = "no";
	    System.out.println((output == null || output == "") ? true : (output.contains("no") ?false:true));*/


	    String test = "aaaa{$3}";

	    System.out.println(test.replace("$", "\\$"));
	    System.out.println(test.replace("\\$", "\\\\$"));
	    //"yyyy-MM-dd HH:mm:ss"
	    //String time = "Mon Mar 27 17:48:33 2017 CST";
	    //String time = "Sun 01 Jul 2018 04:10:53 PM CST";
	    String time = "5/14/13";
	    //SimpleDateFormat sdf = new SimpleDateFormat ("EEE dd MMM yyyy HH:mm:ss a Z", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yy");
        Date date = sdf.parse(time);
        long timgLong = date.getTime();
        System.out.println(timgLong);


	}














}
