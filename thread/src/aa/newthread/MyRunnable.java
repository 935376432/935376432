package aa.newthread;

public class MyRunnable implements Runnable {
    private int i = 0;
    /**
     * 实现run方法
     */
    @Override
    public void run() { 
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}