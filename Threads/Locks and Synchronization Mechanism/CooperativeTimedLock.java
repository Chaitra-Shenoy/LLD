import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class TimedWaiting {
  private ReentrantLock lock = new ReentrantLock();

  public void locking() {
    try {
      if (lock.tryLock(2, TimeUnit.SECONDS)) {
        try {
          System.out.println("Lock acquired!");
          Thread.sleep(3000);
          System.out.println("Task done!");
        } finally {
          lock.unlock();
        }
      } else {
        System.out.println("Lock could not be acquired! - " + Thread.currentThread().getName());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class CooperativeTimedLock {
  public static void main(String args[]) throws InterruptedException {
    TimedWaiting tw = new TimedWaiting();
    Thread t1 = new Thread(() -> tw.locking());
    Thread t2 = new Thread(() -> tw.locking());
    Thread t3 = new Thread(() -> tw.locking());

    t1.start();
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();
  }
}
