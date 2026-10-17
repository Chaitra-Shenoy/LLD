class BankAccount {
  private String name;
  private int balance;

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

  public synchronized void withdraw(int amount) {
    balance -= amount;
  }

  public synchronized void deposit(int amount) {
    balance += amount;
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

  public void transferAmount() {
    synchronized (from) {
      System.out.println("Lock obtained by " + from.getName());

      synchronized (to) {
        System.out.println("Lock obtained by " + to.getName());

        from.withdraw(amount);
        to.deposit(amount);
      }
    }
  }
}

public class DeadlockExample {
  public static void main(String args[]) throws InterruptedException {
    BankAccount A = new BankAccount("User A", 1000);
    BankAccount B = new BankAccount("User B", 2000);

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
