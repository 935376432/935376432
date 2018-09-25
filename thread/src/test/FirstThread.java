package test;

public class FirstThread implements Runnable{

	@Override
	public void run() {
		//System.out.println("thread-1");
		for(int i = 0 ;i < 100 ; i++ ){
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
		
	}
	
	public static void main(String[] args) {
		FirstThread f = new FirstThread();
		Thread t = new Thread(f);
		Thread t1 = new Thread(f);
		Thread t2 = new Thread(f);
		Thread t3 = new Thread(f);
		Thread t4 = new Thread(f);
		t.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
}


