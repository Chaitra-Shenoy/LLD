import java.util.Arrays;

class BankAccountO {
  private String name;
  private int balance;

  public BankAccountO(String name, int balance) {
    this.name = name;
    this.balance = balance;
  }

  public String getName() {
    return name;
  }

  public int getBalance() {
    return balance;
  }

  public synchronized void withdraw(int amount) {
    balance -= amount;
  }

  public synchronized void deposit(int amount) {
    balance += amount;
  }
}

class Transfer {
  private BankAccountO from;
  private BankAccountO to;
  private int amount;

  public Transfer(BankAccountO from, BankAccountO to, int amount) {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }

  public void transferAmount() {
    BankAccountO[] accounts = new BankAccountO[] { from, to };
    Arrays.sort(accounts, (a, b) -> a.getName().compareTo(b.getName()));

    synchronized (accounts[0]) {
      System.out.println("Lock obtained by " + accounts[0].getName());

      synchronized (accounts[1]) {
        System.out.println("Lock obtained by " + accounts[1].getName());

        from.withdraw(amount);
        to.deposit(amount);
      }
    }
  }
}

public class LockOrdering {
  public static void main(String args[]) throws InterruptedException {
    BankAccountO A = new BankAccountO("User A", 1000);
    BankAccountO B = new BankAccountO("User B", 2000);

    Transfer t1 = new Transfer(A, B, 200);
    Transfer t2 = new Transfer(B, A, 100);

    Thread th1 = new Thread(() -> t1.transferAmount());
    Thread th2 = new Thread(() -> t2.transferAmount());

    th1.start();
    th2.start();

    th1.join();
    th2.join();
  }
}
