package com.algorithm.test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        final ExecutorService executor = new ThreadPoolExecutor(4, 16,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        final CountDownLatch countDownLatch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " = > " + countDownLatch.getCount());
            });
        }

        countDownLatch.await();
        System.out.println("done ...");
        executor.shutdown();
        System.out.println("shutdown ...");
    }
}
