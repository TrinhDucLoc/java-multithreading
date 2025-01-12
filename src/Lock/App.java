package Lock;

import java.util.ArrayList;
import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {
        new Worker().main();
    }
}


class Worker {
    private final Random random = new Random();
    // lock 1, 2
    final Object lock1 = new Object();
    final Object lock2 = new Object();

    // arrayList
    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ArrayList<Integer> arrayList2 = new ArrayList<>();

    // stageOne, stageTwo synchronized lock
    public void stageOne() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(1);
            arrayList1.add(random.nextInt(100));
        }
    }

    public void stageTwo() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(2);
            arrayList2.add(random.nextInt(100));
        }
    }

    public void process() throws InterruptedException {
        stageOne();
        stageTwo();
    }

    public void main() throws InterruptedException {
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println("Time range: " + (end - start));
        System.out.println("List1: " + arrayList1.size() + "; List2: " + arrayList2.size());
    }
}