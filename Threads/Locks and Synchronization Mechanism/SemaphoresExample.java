import java.util.concurrent.Semaphore;

class TUFPlusAccount {
  private final Semaphore deviceSlots;

  public TUFPlusAccount(int maxDevices) {
    this.deviceSlots = new Semaphore(maxDevices);
  }

  public boolean login(String user) {
    System.out.println(user + " trying to log in...");

    if (deviceSlots.tryAcquire()) {
      System.out.println(
          user + " successfully logged in.");
      return true;
    }

    System.out.println(
        user + " denied login - too many devices.");
    return false;
  }

  public void logout(String user) {
    System.out.println(user + " logging out.");
    deviceSlots.release();
  }
}

public class SemaphoresExample {
  public static void main(String[] args)
      throws InterruptedException {

    TUFPlusAccount account = new TUFPlusAccount(2);

    Thread user1 = new Thread(
        () -> trySession(account, "User-A"));

    Thread user2 = new Thread(
        () -> trySession(account, "User-B"));

    Thread user3 = new Thread(
        () -> trySession(account, "User-C"));

    user1.start();
    user2.start();

    Thread.sleep(100);
    user3.start();

    user1.join();
    user2.join();
    user3.join();
  }

  private static void trySession(
      TUFPlusAccount account,
      String user) {
    if (!account.login(user)) {
      return;
    }

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      // Release only after a successful login.
      account.logout(user);
    }
  }
}