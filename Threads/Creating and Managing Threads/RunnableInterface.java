import java.lang.Runnable;
import java.lang.Thread;

class SMSRunnable implements Runnable {
  public void run() {
    try {
      Thread.sleep(2000);
      System.out.println("SMS Sent!");
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class EmailRunnable implements Runnable {
  public void run() {
    try {
      Thread.sleep(2000);
      System.out.println("Email Sent!");
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class RunnableInterface {
  public static void main(String args[]) {
    Thread smsThread = new Thread(new SMSRunnable());
    Thread emailThread = new Thread(new EmailRunnable());

    System.out.println("Task 1 Started!");
    smsThread.start();
    System.out.println("Task 2 Started!");
    emailThread.start();

    try {
      smsThread.join();
      emailThread.join();
      System.out.println("Tasks Completed");
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
}
