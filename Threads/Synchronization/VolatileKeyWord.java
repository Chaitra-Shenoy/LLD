package Threads.Synchronization;

public class VolatileKeyWord {
  volatile static boolean ready = false;
  static int x = 0;
  public static void main(String args[]) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      x = 24;
      ready = true;
    });

    Thread t2 = new Thread(() -> {
      if(ready) {
        System.out.println(x);
      }
    });

    t1.start();
    t2.start();
  }
} 
