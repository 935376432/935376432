package demo1;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
  
public class CachedThreadPoolTest {  
  
  public static void main(String[] args) {  
    try {  
      // �����̵߳��̳߳أ����֮ǰ������߳̿�������������  
      ExecutorService executorService = Executors.newCachedThreadPool();  
      for (int i =1; i <= 4; i++) {  
        executorService.execute(new Task(i));  
      }  
      executorService.shutdown();  
       
    } catch (Exception e) {}  
  }  
}  