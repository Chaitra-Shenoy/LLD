class PurchaseSync1 {
  private int count;
  public PurchaseSync1() {
    this.count = 0;
  }
  public void increment() {
    synchronized (this) {
      count++;
    }

    System.out.println("Out of synchronized block - "+Thread.currentThread().getName());
  }

  public int getCount() {
    return this.count;
  }
}
public class SynchronizedBlock {
  public static void main(String args[]) throws InterruptedException {
    PurchaseSync1 p = new PurchaseSync1();

    Runnable task = () -> {
      for(int i = 0; i < 10; i++)
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
