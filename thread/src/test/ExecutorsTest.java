package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {  
    public static void main(String[] args) {  
        // 创建一个可重用固定线程数的线程池  
        //ExecutorService pool = Executors.newFixedThreadPool(5);  
        ExecutorService pool = Executors.newCachedThreadPool();//动态
        //ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        // 创建线程  
        Thread t1 = new MyThread();  
        Thread t2 = new MyThread();  
        Thread t3 = new MyThread();  
        Thread t4 = new MyThread();  
        Thread t5 = new MyThread();  
        // 将线程放入池中进行执行  
        pool.execute(t1);  
        pool.execute(t2);  
        pool.execute(t3);  
        pool.execute(t4);  
        pool.execute(t5);  
        //pool.schedule(t5, 1000, TimeUnit.MILLISECONDS);
        // 关闭线程池  
        pool.shutdown();  
    }  
}  
  
class MyThread extends Thread {  
    @Override  
    public void run() {  
    	for(int i = 0;i < 10 ;i++){
    		System.out.println(Thread.currentThread().getName() + "正在执行。。。" + i);  
    	}
        
    }  
}  