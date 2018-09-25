package aa.newthread;

public class Test001ticket {
	public static void main(String[] args) {
		SaleThread3 th=new SaleThread3(500);  //�ø�����������
		Thread t1=new Thread(th);
		Thread t2=new Thread(th);
		Thread t3=new Thread(th);
		Thread t4=new Thread(th);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();	
	}
}
class SaleThread3  implements Runnable{
	private int ticket;
	public SaleThread3(int ticket){
		this.ticket=ticket;
	}
	public void run() {
		while(true){
			if(ticket>0){
				try{
						Thread.sleep(10);	
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				System.out.println("�߳�"+Thread.currentThread().getName() +"��������"+ticket--+"��Ʊ");
			}
			else{
				break;
			}
		}	
	}

}