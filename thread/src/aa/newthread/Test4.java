package aa.newthread;

public class Test4 {
	public static void main(String[] args) {
		SaleThread2 th=new SaleThread2(2000);  //用给别人做参数
		Thread t1=new Thread(th);
		t1.setName("程序员");
		Thread t2=new Thread(th);
		Thread t3=new Thread(th);
		Thread t4=new Thread(th);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();	
	}
}

//这个类的类对象,将来做为 Thread() 的构造函数参数
class SaleThread2 implements Runnable{
	//static int ticket=1000;
	private int ticket;
	public SaleThread2(int ticket){
		this.ticket=ticket;
	}
	public void run() {
		while(true){
			if(ticket>0){
				System.out.println("线程"+Thread.currentThread().getName() +"正在卖第"+ticket--+"张票");
			}
			else{
				break;
			}
		}	
	}
}