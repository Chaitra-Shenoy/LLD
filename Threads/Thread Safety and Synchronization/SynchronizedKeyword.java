class SyncMethod {
  private int count = 0;
  public synchronized void increment() {
    count++;
  }
  public int getCount() {
    return count;
  }
}

class SyncBlock {
  private int count = 0;
  public void increment() {
    synchronized(this) {
      count++;
    }
  }
  public int getCount() {
    return count;
  }
}

public class SynchronizedKeyword {
  public static void main(String args[]) throws InterruptedException {
    // SyncMethod s1 = new SyncMethod();
    SyncBlock s1 = new SyncBlock();

    Runnable task = (() -> {
        for(int i = 0; i < 100; i++) {
          s1.increment();
        }
      });

      Thread t1 = new Thread(task);
      Thread t2 = new Thread(task);
      t1.start();
      t2.start();
      t1.join();
      t2.join();

      System.out.println("Counter - "+s1.getCount());
  }
}
