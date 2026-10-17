import java.util.concurrent.atomic.AtomicInteger;

class PurchaseAtomicCounter {
  private AtomicInteger likes = new AtomicInteger(0);
  public void increment() {
    int prev, next;
    do {
      prev = likes.get();
      next = prev + 1;
    } while(!likes.compareAndSet(prev, next));
  }
  public int getLikes() {
    return likes.get();
  }
}
public class AtomicExample {
  public static void main(String args[]) throws InterruptedException {
    PurchaseAtomicCounter pac = new PurchaseAtomicCounter();

    Runnable task = (() -> {
        for(int i = 0; i < 100; i++) {
          pac.increment();
        }
      });

      Thread t1 = new Thread(task);
      Thread t2 = new Thread(task);
      t1.start();
      t2.start();
      t1.join();
      t2.join();

      System.out.println("Counter - "+pac.getLikes());
  }
}
