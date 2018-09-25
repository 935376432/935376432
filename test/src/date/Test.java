package date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

public class Test {

    //public static long midTime;

    public static Map<String,Object> map = new HashMap<>();

	public static void main(String[] args) {

	    String dateStr = LocalDate.now().toString();
	    System.out.println(dateStr);

	    Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        System.out.println("Date = " + date);
        System.out.println("LocalDateTime = " + localDateTime);

        //midTime = System.currentTimeMillis()/1000;
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                /*midTime--;
                long hh = midTime / 60 / 60 % 60;
                long mm = midTime / 60 % 60;
                long ss = midTime % 60;
                System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");*/
                System.out.println(new Date());
                aa ();
                this.cancel();
            }
        }, 4, 10000);


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String str = StringUtils.join(list.toArray(), ",");
        System.out.println(str);

	}

	public static void aa () {
	    System.out.println(map);
	    System.out.println(System.currentTimeMillis()%1000);
	}


}
