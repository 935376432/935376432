package aa.newthread;

public class Test4 {
	public static void main(String[] args) {
		SaleThread2 th=new SaleThread2(2000);  //�ø�����������
		Thread t1=new Thread(th);
		t1.setName("����Ա");
		Thread t2=new Thread(th);
		Thread t3=new Thread(th);
		Thread t4=new Thread(th);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();	
	}
}

//�����������,������Ϊ Thread() �Ĺ��캯������
class SaleThread2 implements Runnable{
	//static int ticket=1000;
	private int ticket;
	public SaleThread2(int ticket){
		this.ticket=ticket;
	}
	public void run() {
		while(true){
			if(ticket>0){
				System.out.println("�߳�"+Thread.currentThread().getName() +"��������"+ticket--+"��Ʊ");
			}
			else{
				break;
			}
		}	
	}
}