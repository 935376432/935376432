package aa.newthread;

public class YieldTest extends Thread {

    public YieldTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // ��iΪ30ʱ�����߳̾ͻ��CPUʱ���õ��������������Լ����߳�ִ�У�Ҳ����˭������˭ִ�У�
            if (i == 30) {
                yield();
            	/*try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
            }
        }
    }

    public static void main(String[] args) {
        YieldTest yt1 = new YieldTest("����");
        YieldTest yt2 = new YieldTest("����");
        yt2.setPriority(MAX_PRIORITY);
        yt1.setPriority(MIN_PRIORITY);
        yt1.start();
        yt2.start();
    }
    //��һ����������ģ��̣߳���ִ�е�30ʱ��CPUʱ���õ�����ʱ�������̣߳�����CPUʱ�䲢ִ�С�
    //�ڶ�����������ģ��̣߳���ִ�е�30ʱ��CPUʱ���õ�����ʱ���ģ��̣߳�����CPUʱ�䲢ִ�С�
}
