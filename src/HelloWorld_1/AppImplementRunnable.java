package HelloWorld_1;

/*
 * Two ways of starting threads
 * 1) Extends Thread class
 * 2) Implement Runnable and pass it to constructor of thread class
 *
 * This code is example for starting threads using method (2) - Implements Runnable and pass it to thread class
 *
 */

public class AppImplementRunnable {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnerImpl());
        thread1.start();
        Thread thread2 = new Thread(new RunnerImpl());
        thread2.start();
    }
}


class RunnerImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
        }
    }
}