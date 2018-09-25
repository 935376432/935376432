package aa.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class Task implements Runnable{
    int taskId;
    public Task(int taskId) {
        this.taskId=taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--taskId: "+taskId);

    }
}

class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {

        Thread t = new Thread(r);
        //t.setDaemon(true);
        t.setName("test" + t.getName());
        return t;
    }

}
public class ThreadFactoryTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++) {
        	System.out.println(i);
            exec.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName() + "test");
				}
			});
        }
        exec.shutdown();
    }
}