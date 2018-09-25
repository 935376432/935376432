package aa.newthread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static void main(String[] args) {
		SaleThread5 th=new SaleThread5(500);  //用给别人做参数
		Thread t1=new Thread(th);
		
		t1.start();
	}
}

class SaleThread5  implements Runnable{
	//static int ticket=1000;
	Lock lock = new ReentrantLock();
	private int ticket;
	public SaleThread5(int ticket){
		this.ticket=ticket;
	}
	
	public synchronized void  run() {
		while(true){
			if(ticket>0){
				try{
					lock.lock();
					System.out.println("线程"+Thread.currentThread().getName() +"正在卖第"+ticket--+"张票");
				}
				catch(Exception ex){
					ex.printStackTrace();
				} finally {
					lock.unlock();
				}
				
			}
			else{
				break;
			}
			
		}	
	}
}