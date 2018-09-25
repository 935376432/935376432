package demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {

  public static void main(String[] args) {
    try {
      // �����̶��߳������̳߳أ��Թ�����޽���з�ʽ��������Щ�߳�
      ExecutorService executorService = Executors.newFixedThreadPool(5);
      for (int i =1; i <= 10; i++) {
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                testaa();

            }
        });
      }
      executorService.shutdown();

    } catch (Exception e) {}
  }
  public static void testaa() {
      System.out.println("111111");
  }

}
