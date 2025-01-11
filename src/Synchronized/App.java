package Synchronized;

/*
Synchronized: Let's only one thread inside the function at a time
Avoid: Race Condition
 */

public class App {
    public int count = 0;

    public synchronized void increase() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.doWork();
    }

    public void doWork() throws InterruptedException {
        // 1. create thread
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    increase();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    increase();
                }
            }
        });

        // 2. start thread
        thread1.start();
        thread2.start();

        // 3. join thread
        try {
            thread1.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }
}