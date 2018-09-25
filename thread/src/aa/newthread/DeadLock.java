package aa.newthread;

public class DeadLock {
	public static void main(String[] args) {
		new Thread(new DeadLockThread(true)).start();
	//	Thread.sleep(10);
		new Thread(new DeadLockThread(false)).start();
	}

}

class DeadLockThread implements Runnable{
	static Object o1=new Object();
	static Object o2=new Object();
	private boolean flag;
	
	DeadLockThread(boolean flag){
		this.flag=flag;
	}
	public void run() {
		if(flag==true){
			while(true){
				synchronized (o1) {
					System.out.println("���� if�еķ��� ,�õ����� o1");	
					synchronized(o2){
						System.out.println("���� if�еķ��� ,�õ����� o2");
					}
				}
			}
		}
		
		else{
			while(true){
				synchronized (o2) {
					System.out.println("���� else�еķ��� ,�õ����� o2");	
					synchronized (o1) {
						System.out.println("����else�е����,��Ϊ o1");	
					}
				}
			}
		}
	}
	
}
