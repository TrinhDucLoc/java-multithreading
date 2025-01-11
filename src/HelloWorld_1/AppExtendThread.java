package HelloWorld_1;

public class AppExtendThread {
    public static void main(String[] args) {
        Thread thread1 = new Runner();
        Thread thread2 = new Runner();
        thread1.start();
        thread2.start();
    }
}

class Runner extends Thread {
    @Override
    public void run() {
        System.out.println(currentThread().getName());
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello world - " + i);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}