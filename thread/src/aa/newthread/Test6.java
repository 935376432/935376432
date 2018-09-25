package aa.newthread;

public class Test6 {
	public static void main(String[] args) {
		SaleThread4 th=new SaleThread4(500);  //�ø�����������
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

class SaleThread4  implements Runnable{
	//static int ticket=1000;
	private int ticket;
	public SaleThread4(int ticket){
		this.ticket=ticket;
	}
	
	private synchronized void sale(){
		if(ticket>0){
			try{
					Thread.sleep(20);	
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
			System.out.println("�߳�"+Thread.currentThread().getName() +"��������"+ticket--+"��Ʊ");
		}
		else{
			System.exit(0);
		}
	}
	
	String lockStr="";
	//Object obj=new Object();
	public synchronized void  run() {
		while(true){
			/*synchronized (lockStr) {  //�� ,�����
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
			}*/
			
			sale();
			
			
		}	
	}
}