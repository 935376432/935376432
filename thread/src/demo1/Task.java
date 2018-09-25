package demo1;

//��ִ������  
public class Task implements Runnable {  
  // �ж��ź�  
  volatile boolean stop = false;  

  // ������ִ�еĴ���  
  private int runCount = 0;  

  // �����ʶ  
  private int taskId;  

  public Task(int taskId) {  
      this.taskId = taskId;  
      System.out.println("Create Task-" + taskId);  
  }  

  // ִ������  
  public void run() {  

      while (!stop) {  
          try {  
              Thread.sleep(10);  
          } catch (InterruptedException e) {  
              System.out.println("Task interrupted...");  
          }  

          // �߳�����3�κ�,�ж��ź���Ϊtrue  
          if (++runCount == 3)  
              stop = true;  

          // ���һЩ���  
          System.out.println("" + Thread.currentThread().toString()  
                  + "\t\t\t\t execute Task-" + taskId + "'s " + runCount  
                  + "th run. ");  

      }  
  }  
}  
