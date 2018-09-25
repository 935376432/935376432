package test;

public class Test4 {
	public static void main(String[] args) throws InterruptedException {
		SaleThread2 th = new SaleThread2(100); // �ø�����������
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

// �����������,������Ϊ Thread() �Ĺ��캯������
class SaleThread2 implements Runnable {
	// static int ticket=1000;
	private int ticket;
	public boolean flag = true;

	public SaleThread2(int ticket) {
		this.ticket = ticket;
	}

	// ʹ��ͬ������
	private synchronized void sale() {
		if (ticket > 0) {
			try {
				Thread.sleep(20);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("�߳�" + Thread.currentThread().getName() + "��������"
					+ ticket-- + "��Ʊ");
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
				synchronized (this) { // �� ,�����
					if (ticket > 0) {
						try {
							Thread.sleep(10);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						System.out.println("�߳�"
								+ Thread.currentThread().getName() + "��������"
								+ ticket-- + "��Ʊ");
					} else {
						break;
					}
				}
			}

		}
	}
}
