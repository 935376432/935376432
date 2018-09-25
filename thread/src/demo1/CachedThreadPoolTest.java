package demo1;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
  
public class CachedThreadPoolTest {  
  
  public static void main(String[] args) {  
    try {  
      // 建新线程的线程池，如果之前构造的线程可用则重用它们  
      ExecutorService executorService = Executors.newCachedThreadPool();  
      for (int i =1; i <= 4; i++) {  
        executorService.execute(new Task(i));  
      }  
      executorService.shutdown();  
       
    } catch (Exception e) {}  
  }  
}  