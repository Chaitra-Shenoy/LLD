class Counter {
  private int count = 0;
  public void increment() {
    // read, increment, write
    count++; // not thread safe
  }
  public int getCount() {
    return count;
  }
}

public class RaceConditionDemo {
    public static void main(String args[]) throws InterruptedException {
      Counter count = new Counter();

      Runnable task = (() -> {
        for(int i = 0; i < 100; i++) {
          count.increment();
        }
      });

      Thread t1 = new Thread(task);
      Thread t2 = new Thread(task);
      t1.start();
      t2.start();
      t1.join();
      t2.join();

      System.out.println("Counter - "+count.getCount()); // Not guaranteed to be 200 everytime
    }
}