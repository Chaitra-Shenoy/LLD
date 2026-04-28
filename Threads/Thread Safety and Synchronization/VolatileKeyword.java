class PurchaseSyncV {
  private volatile int count;
  public PurchaseSyncV() {
    this.count = 0;
  }
  public void increment() {
    this.count++;
  }

  public int getCount() {
    return this.count;
  }
}
public class VolatileKeyword {
  public static void main(String args[]) throws InterruptedException {
    PurchaseSyncV p = new PurchaseSyncV();

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
