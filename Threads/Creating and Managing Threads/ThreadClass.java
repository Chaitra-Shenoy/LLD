import java.lang.Thread;

class SMSThread extends Thread {
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

class EmailThread extends Thread {
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

public class ThreadClass {
  public static void main(String args[]) {
    SMSThread smsThread = new SMSThread();
    EmailThread emailThread = new EmailThread();

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