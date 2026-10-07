import java.lang.Thread;

public class OtherWays {
  public static void main(String args[]) {
    Thread t = new Thread(() -> sendSMS());
    t.start();

    Runnable r1 = new Runnable() {
      public void run() {
        try {
          Thread.sleep(2000);
          System.out.println("Email Sent");
        } 
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Thread t2 = new Thread(r1);
    t2.start();

    Runnable r2 = () -> {
      try {
        Thread.sleep(2000);
        System.out.println("Delivery Email Sent");
      } 
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    Thread t3 = new Thread(r2);
    t3.start();

  }

  public static void sendSMS() {
    try {
      Thread.sleep(2000);
      System.out.println("SMS Sent");
    } 
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
