package test;

import java.awt.List;
import java.util.ArrayList;

public class ThreadTestLess {
	public static void main(String[] args) {
		SaleThread22 s = new SaleThread22(100);
		Thread t1 = new Thread(s);
		Thread t2 = new Thread(s);
		Thread t3 = new Thread(s);
		Thread t4 = new Thread(s);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class SaleThread22 implements Runnable {
	// static int ticket=1000;
	private int ticket;

	public SaleThread22(int ticket) {
		this.ticket = ticket;
	}

	public void run() {
		String str = "";
		ArrayList ss = new ArrayList();
		while (true) {
			synchronized (ss) {
				if (ticket > 0) {
					try {
						Thread.sleep(10);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在卖第" + ticket-- + "张票");
				} else {
					break;
				}
			}

		}
	}

}
