package aa.executor;

/**
 * ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���ʾ���������£�
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
public class FixedThreadPoolTest {
	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("SendSyslog" + t.getName());
                return t;
            }
        });
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				@Override
                public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + ":" + index);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}


class MyThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable arg0) {
		Thread t = new Thread(arg0);
        //t.setDaemon(true);
        t.setName("test" + t.getName());
        return t;
	}
}