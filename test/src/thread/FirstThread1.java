package thread;



public class FirstThread1 implements Runnable{

	public void run() {
		//System.out.println("thread-1");
		for(int i = 0 ;i < 100 ; i++ ){
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
			
		
	}
	public static void main(String[] args) {
		FirstThread1 f = new FirstThread1();
		Thread t = new Thread(f);
		Thread t1 = new Thread(f);
		t.start();
		t1.start();
	}
	
	
	
}

