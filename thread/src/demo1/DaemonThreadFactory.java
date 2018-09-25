package demo1;

import java.util.concurrent.ThreadFactory;  

public class DaemonThreadFactory implements ThreadFactory {  
   
  //创建一个守护线程  
  public Thread newThread(Runnable r) {  
    Thread t = new Thread(r);  
    t.setDaemon(true);  
    return t;  
  }  
}  