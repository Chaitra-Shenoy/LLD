class PurchaseSync {
  private int count;
  public PurchaseSync() {
    this.count = 0;
  }
  public synchronized void increment() {
    this.count++;
  }

  public int getCount() {
    return this.count;
  }
}
public class SynchronizedMethod {
  public static void main(String args[]) throws InterruptedException {
    PurchaseSync p = new PurchaseSync();

    Runnable task = () -> {
      for(int i = 0; i < 1000; i++)
        p.increment();
    };

    Thread t1 = new Thread(task);
    Thread t2 = new Thread(task);
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    System.out.println("Count = "+p.getCount());
  }
}
