import java.lang.Runnable;
import java.lang.Thread;
import java.util.concurrent.*;

class SMSRunnable1 implements Runnable {
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

class EmailRunnable1 implements Runnable {
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

class ETACallable implements Callable<String> {
  private String location;
  public ETACallable(String location) {
    this.location = location;
  }

  @Override
  public String call() throws Exception {
    Thread.sleep(2000);
    return "Location : "+location + ", ETA : 20 minutes";
  }
}
public class CallableInterface {
  public static void main(String args[]) {
    Thread smsThread = new Thread(new SMSRunnable1());
    Thread emailThread = new Thread(new EmailRunnable1());

    FutureTask<String> etaCallable = new FutureTask<>(new ETACallable("BLR"));
    Thread etaThread = new Thread(etaCallable);

    System.out.println("Task 1 Started!");
    smsThread.start();
    System.out.println("Task 2 Started!");
    emailThread.start();

    System.out.println("Task 3 Started!");
    etaThread.start();

    try {
      smsThread.join();
      emailThread.join();

      String eta = etaCallable.get();
      System.out.println(eta);
      System.out.println("Tasks Completed");
    } catch(InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
