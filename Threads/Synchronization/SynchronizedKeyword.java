package Threads.Synchronization;

class Counter {

  private int count = 0;

  // 1. Synchronized instance method
  // Lock = this
  public synchronized void increment() {
    count++;
    System.out.println("increment(): " + count);
  }

  // 2. Synchronized block using this
  // Lock = this
  public void increment2() {
    synchronized (this) {
      count++;
      System.out.println("increment2(): " + count);
    }
  }

  // 3. Synchronized block using a specific lock object
  private final Object lock = new Object();

  public void increment3() {
    synchronized (lock) {
      count++;
      System.out.println("increment3(): " + count);
    }
  }

  // 4. Static synchronized method
  // Lock = Counter.class
  private static int staticCount = 0;

  public static synchronized void increment4() {
    staticCount++;
    System.out.println("increment4(): " + staticCount);
  }
}

public class SynchronizedKeyword {

  public static void main(String args[]) {

    // ----------------------------------
    // 1. synchronized instance method
    // ----------------------------------

    Counter c = new Counter();

    for (int i = 0; i < 10; i++) {
      Thread t = new Thread(() -> {
        c.increment();
      });

      t.start();
    }

    // ----------------------------------
    // 2. synchronized(this) block
    // ----------------------------------

    Counter c1 = new Counter();

    for (int i = 0; i < 10; i++) {
      Thread t = new Thread(() -> {
        c1.increment2();
      });

      t.start();
    }

    // ----------------------------------
    // 3. synchronized(specific object)
    // ----------------------------------

    Counter c2 = new Counter();

    for (int i = 0; i < 10; i++) {
      Thread t = new Thread(() -> {
        c2.increment3();
      });

      t.start();
    }

    // ----------------------------------
    // 4. static synchronized method
    // ----------------------------------

    for (int i = 0; i < 10; i++) {
      Thread t = new Thread(() -> {
        Counter.increment4();
      });

      t.start();
    }
  }
}