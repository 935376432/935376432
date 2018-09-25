package test;

public class Test4 {
	public static void main(String[] args) throws InterruptedException {
		SaleThread2 th = new SaleThread2(100); // 用给别人做参数
		Thread t1 = new Thread(th);
		Thread t2 = new Thread(th);


		t1.start();
		Thread.sleep(10);
		th.flag=false;
		t2.start();
		Thread.sleep(10);
		th.flag=true;

	}
}

// 这个类的类对象,将来做为 Thread() 的构造函数参数
class SaleThread2 implements Runnable {
	// static int ticket=1000;
	private int ticket;
	public boolean flag = true;

	public SaleThread2(int ticket) {
		this.ticket = ticket;
	}

	// 使用同步函数
	private synchronized void sale() {
		if (ticket > 0) {
			try {
				Thread.sleep(20);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("线程" + Thread.currentThread().getName() + "正在卖第"
					+ ticket-- + "张票");
		} else {
			System.exit(0);
		}
	}

	String lockStr = "";

	public void run() {
		if (flag == true) {
			while (true) {
				sale();
			}
		} 
		
		else {
			while (true) {
				synchronized (this) { // 锁 ,锁旗标
					if (ticket > 0) {
						try {
							Thread.sleep(10);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						System.out.println("线程"
								+ Thread.currentThread().getName() + "正在卖第"
								+ ticket-- + "张票");
					} else {
						break;
					}
				}
			}

		}
	}
}
