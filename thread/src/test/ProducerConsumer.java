package test;

public class ProducerConsumer {  
	  
    public static void main(String[] args) {  
        Stack stack = new Stack();  
        Thread producer1 = new Thread(new Producer(stack), "producer1");  
        Thread consumer1 = new Thread(new Consumer(stack), "consumer1");  
        producer1.start();  
        consumer1.start();  
    }  
}  
  
class Pizza {  
    public int id;  
  
    Pizza(int id) {  
        this.id = id;  
    }  
  
    public String toString() {  
        return ("Pizza:" + id);  
    }  
}  
  
class Stack {  
    int index = 0;  
    Pizza[] arrPizza = new Pizza[10];  
  
    public synchronized void push(Pizza pizza) {  
        while (index == arrPizza.length) {// Ҫ��Stack�����ѭ���жϣ���Ϊ�߳�ÿ���Ƿ���Stack��ͬһ������  
            try {  
                this.wait();// ʹ�������������߳̽���ȴ�״̬  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        this.notify();// ���ѷ��ʵ�ǰ���������һ���߳�  
        arrPizza[index] = pizza;  
        index++;  
        System.out.println(Thread.currentThread().getName() + "������" + pizza);// �����Ϣ����synchronized�����ķ��������Ȼ���ױ������̴߳�ϣ��������  
  
    }  
  
    public synchronized Pizza pull() {  
        while (index == 0) {  
            try {  
                this.wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        this.notify();  
        index--;  
        Pizza pizza = arrPizza[index];  
        System.out.println(Thread.currentThread().getName() + "������" + pizza);// �����Ϣ����synchronized�����ķ��������Ȼ���ױ������̴߳�ϣ��������  
        return pizza;  
    }  
}  
  
class Producer implements Runnable {  
    Stack stack;  
  
    Producer(Stack stack) {  
        this.stack = stack;  
    }  
  
    public void run() {  
        for (int i = 0; i < stack.arrPizza.length; i++) {  
            Pizza pizza = new Pizza(i);  
            stack.push(pizza);  
            // Thread.yield();//�ó�cpu�������߳�  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
}  
  
class Consumer implements Runnable {  
    Stack stack;  
  
    Consumer(Stack stack) {  
        this.stack = stack;  
    }  
  
    public void run() {  
        for (int i = 0; i < stack.arrPizza.length; i++) {  
            stack.pull();  
            // Thread.yield();  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  