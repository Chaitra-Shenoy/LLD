import java.util.concurrent.atomic.AtomicInteger;

class PurchaseAtomicCounter {

    // A thread-safe integer backed by hardware-level CAS
    private final AtomicInteger likes = new AtomicInteger(0);

    // Atomically add 1 to the like counter
    public void incrementLikes() {
        int prev, next;
        do {

            // Step 1  – read the current value.
            //           (May be outdated if another thread wins the race.)
            prev = likes.get();

            // Step 2  – compute the desired next value.
            next = prev + 1;

            // Step 3  – attempt to swap: 
            /*          “If current value is still ‘prev’, set it to ‘next’.”
             *          Returns true on success, false if another thread
             *          already changed the value (retry needed).
             */
        } while (!likes.compareAndSet(prev, next));
    }

    // Read-only accessor
    public int getCount() {
        return likes.get();
    }
}


public class AtomicVariables {
  public static void main(String args[]) throws InterruptedException {
    PurchaseAtomicCounter pa = new PurchaseAtomicCounter();

    Runnable task = () -> {
      for(int i = 0; i < 1000; i++)
        pa.incrementLikes();
    };

    Thread t1 = new Thread(task);
    Thread t2 = new Thread(task);
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    System.out.println("Likes = "+pa.getCount());
  }  
}
