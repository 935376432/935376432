package aa.newthread;

public class Test3 {
	public static void main(String[] args) {
		new SaleThread().start();
		new SaleThread().start();
		new SaleThread().start();
		new SaleThread().start();
	}
}
	
class SaleThread extends Thread{
	static int ticket=1000;
	public void run() {
		while(ticket>0){
			System.out.println("线程"+getName()+"正在出售第" + ticket-- +"张票");
			/*System.out.println(Thread.NORM_PRIORITY);
			System.out.println(Thread.MAX_PRIORITY);
			System.out.println(Thread.MIN_PRIORITY);*/
		}
	}	
}