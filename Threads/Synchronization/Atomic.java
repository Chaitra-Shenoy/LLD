package Threads.Synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

  public static void main(String args[]) throws InterruptedException {

    AtomicInteger count = new AtomicInteger(0);

    Thread[] threads = new Thread[10];

    // Create 10 threads
    for (int i = 0; i < 10; i++) {

      threads[i] = new Thread(() -> {

        // Each thread increments 1000 times
        for (int j = 0; j < 1000; j++) {
          count.incrementAndGet();
        }
      });

      threads[i].start();
    }

    // Wait for all threads to finish
    for (Thread t : threads) {
      t.join();
    }

    System.out.println("Final count: " + count.get());
  }
}