package aa.newthread;

public class RunnableTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Runnable myRunnable = new MyRunnable(); // ����һ��Runnableʵ����Ķ���
                Thread thread1 = new Thread(myRunnable); // ��myRunnable��ΪThread target�����µ��߳�
                Thread thread2 = new Thread(myRunnable);
                thread2.setName("����");
                thread1.start(); // ����start()����ʹ���߳̽������״̬
                thread2.start();
            }
        }
    }
}