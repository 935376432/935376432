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
			System.out.println("�߳�"+getName()+"���ڳ��۵�" + ticket-- +"��Ʊ");
			/*System.out.println(Thread.NORM_PRIORITY);
			System.out.println(Thread.MAX_PRIORITY);
			System.out.println(Thread.MIN_PRIORITY);*/
		}
	}	
}