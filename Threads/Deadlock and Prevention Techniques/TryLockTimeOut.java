import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
  private String name;
  private int balance;
  private ReentrantLock lock = new ReentrantLock();

  public BankAccount(String name, int balance) {
    this.name = name;
    this.balance = balance;
  }

  public String getName() {
    return name;
  }

  public int getBalance() {
    return balance;
  }

  public void withdraw(int amount) {
    balance -= amount;
  }

  public void deposit(int amount) {
    balance += amount;
  }

  public ReentrantLock getLock() {
    return lock;
  }
}

class Transfer {
  private BankAccount from;
  private BankAccount to;
  private int amount;

  public Transfer(BankAccount from, BankAccount to, int amount) {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }

  public void transferAmount() throws InterruptedException {
    ReentrantLock lockFrom = from.getLock();
    ReentrantLock lockTo = to.getLock();

    boolean acquiredFrom = lockFrom.tryLock(2, TimeUnit.SECONDS);

    if (acquiredFrom) {
      try {
        System.out.println("Lock obtained by " + from.getName());

        boolean acquiredTo = lockTo.tryLock(2, TimeUnit.SECONDS);

        if (acquiredTo) {
          try {
            System.out.println("Lock obtained by " + to.getName());

            from.withdraw(amount);
            to.deposit(amount);
          } finally {
            lockTo.unlock();
          }
        } else {
          System.out.println(to.getName() + " could not obtain the lock!");
        }
      } finally {
        lockFrom.unlock();
      }
    } else {
      System.out.println(from.getName() + " could not obtain the lock!");
    }
  }
}

public class TryLockTimeOut {
  public static void main(String args[]) throws InterruptedException {
    BankAccount A = new BankAccount("User A", 1000);
    BankAccount B = new BankAccount("User B", 2000);

    Transfer t1 = new Transfer(A, B, 200);
    Transfer t2 = new Transfer(B, A, 100);

    Thread th1 = new Thread(() -> {
      try {
        t1.transferAmount();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread th2 = new Thread(() -> {
      try {
        t2.transferAmount();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    th1.start();
    th2.start();

    th1.join();
    th2.join();
  }
}
