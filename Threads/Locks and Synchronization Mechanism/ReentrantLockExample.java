import java.util.concurrent.locks.ReentrantLock;

class BookMyShow {
  private int seatsAvailable = 1;
  private ReentrantLock lock = new ReentrantLock();

  public void bookSeat(String user) {
    // Acquires the lock or waits until it becomes available.
    lock.lock();

    // Contains the critical section: checking and updating seat availability.
    try {
      if(seatsAvailable > 0) {
        System.out.println(user + " has booked the seat");
        seatsAvailable--;
      } else {
        System.out.println("No seats available - "+user);
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally // Ensures the lock is released even if an exception occurs
    {
      lock.unlock(); // Releases the lock so another thread can proceed.
    }
  }
}
public class ReentrantLockExample {
  public static void main(String args[]) throws InterruptedException{
    BookMyShow bms = new BookMyShow();
    Thread t1 = new Thread(() -> bms.bookSeat("user1@gmail.com"));
    Thread t2 = new Thread(() -> bms.bookSeat("user2@gmail.com"));
    Thread t3 = new Thread(() -> bms.bookSeat("user3@gmail.com"));
    Thread t4 = new Thread(() -> bms.bookSeat("user4@gmail.com"));
    t1.start();
    t2.start();
    t3.start();
    t4.start();

    t1.join();
    t2.join();
    t3.join();
    t4.join();
  }
}
