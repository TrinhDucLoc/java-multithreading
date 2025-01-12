package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        /*
        Thread pool contains collection of worker thread.
        Once a thread completes a task in thread pool, it is recycled to complete pending tasks.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Assign 5 tasks
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Processor(i));
        }

        // stop accepting new task after complete all task
        executorService.shutdown();

        System.out.println("All task was submit");

        // Max wait time for the tasks to complete
        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("All task completed");
    }
}


class Processor implements Runnable {
    private final int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task " + id + " is running by " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task " + id + " is completed by " + Thread.currentThread().getName());
    }
}