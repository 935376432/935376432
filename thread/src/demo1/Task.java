package demo1;

//可执行任务  
public class Task implements Runnable {  
  // 中断信号  
  volatile boolean stop = false;  

  // 该任务执行的次数  
  private int runCount = 0;  

  // 任务标识  
  private int taskId;  

  public Task(int taskId) {  
      this.taskId = taskId;  
      System.out.println("Create Task-" + taskId);  
  }  

  // 执行任务  
  public void run() {  

      while (!stop) {  
          try {  
              Thread.sleep(10);  
          } catch (InterruptedException e) {  
              System.out.println("Task interrupted...");  
          }  

          // 线程运行3次后,中断信号置为true  
          if (++runCount == 3)  
              stop = true;  

          // 输出一些语句  
          System.out.println("" + Thread.currentThread().toString()  
                  + "\t\t\t\t execute Task-" + taskId + "'s " + runCount  
                  + "th run. ");  

      }  
  }  
}  
