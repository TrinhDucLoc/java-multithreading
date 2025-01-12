package CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started ...");
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService exec = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            exec.submit(new Processor(latch));
        }

        // if latch != 0 process not complete
        latch.await();

        System.out.println("Completed ...");
    }
}


class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Latch value at process init : " + latch.getCount());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        latch.countDown();
        System.out.println("Latch value at process completed : " + latch.getCount());
    }
}