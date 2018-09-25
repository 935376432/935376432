package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	
	public static void main(String[] args) throws ParseException {
		// 创建日期对象
		Date d = new Date();
		System.out.println("data类型：" + d);
		System.out.println("data类型toString：" + d.toString());
		// 给定模式
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// public final String format(Date date)
		String s = sdf1.format(d);
		System.out.println(s);
		
		System.out.println("data getTime ：" + d.getTime());
		
		//1、java.util.Date类型转换成long类型
		java.util.Date dt = new Date();
		System.out.println(dt.toString());   //java.util.Date的含义
		long lSysTime1 = dt.getTime() / 1000;   //得到秒数，Date类型的getTime()返回毫秒数

		//2、由long类型转换成Date类型
		SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		java.util.Date dt2 = new Date(lSysTime1 * 1000);  
		String sDateTime = sdf.format(dt2);  //得到精确到秒的表示：08/31/2006 21:08:00
		System.out.println(sDateTime);
		 
		//3、"08/31/2006 21:08:00"格式的String转换java.util.Date类型
		String sDt = "08/31/2006 21:08:00";
		SimpleDateFormat sdf3= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dt3 = sdf.parse(sDt);
		//继续转换得到秒数的long型
		long lTime = dt3.getTime() / 1000;

	} 
	
}
