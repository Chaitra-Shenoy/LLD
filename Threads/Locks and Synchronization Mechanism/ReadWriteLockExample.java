import java.util.concurrent.locks.ReentrantReadWriteLock;

class StockMarket {
  private int stockPrice = 138;
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public void setPrice(int price) {
    lock.writeLock().lock();
    try {
      this.stockPrice = price;
    } finally {
      lock.writeLock().unlock();
    }
  }

  public int readStockPrice() {
    lock.readLock().lock();
    try {
      return stockPrice;
    } finally {
      lock.readLock().unlock();
    }
  }
}

public class ReadWriteLockExample {
  public static void main(String args[]) throws InterruptedException {
    StockMarket sm = new StockMarket();

    Thread t1 = new Thread(() -> {
      sm.setPrice(140);
    });
    Thread t2 = new Thread(() -> {
      System.out.println(sm.readStockPrice());
    });

    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}
