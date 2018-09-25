package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	
	public static void main(String[] args) throws ParseException {
		// �������ڶ���
		Date d = new Date();
		System.out.println("data���ͣ�" + d);
		System.out.println("data����toString��" + d.toString());
		// ����ģʽ
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// public final String format(Date date)
		String s = sdf1.format(d);
		System.out.println(s);
		
		System.out.println("data getTime ��" + d.getTime());
		
		//1��java.util.Date����ת����long����
		java.util.Date dt = new Date();
		System.out.println(dt.toString());   //java.util.Date�ĺ���
		long lSysTime1 = dt.getTime() / 1000;   //�õ�������Date���͵�getTime()���غ�����

		//2����long����ת����Date����
		SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		//ǰ���lSysTime���������ȳ�1000�õ�����������תΪjava.util.Date����
		java.util.Date dt2 = new Date(lSysTime1 * 1000);  
		String sDateTime = sdf.format(dt2);  //�õ���ȷ����ı�ʾ��08/31/2006 21:08:00
		System.out.println(sDateTime);
		 
		//3��"08/31/2006 21:08:00"��ʽ��Stringת��java.util.Date����
		String sDt = "08/31/2006 21:08:00";
		SimpleDateFormat sdf3= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dt3 = sdf.parse(sDt);
		//����ת���õ�������long��
		long lTime = dt3.getTime() / 1000;

	} 
	
}
