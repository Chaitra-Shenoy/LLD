import java.util.concurrent.*;

public class ThreadExecutorFixedThreadPool {
  private static final ExecutorService executor = Executors.newFixedThreadPool(10);
  
  public static void sendEmail(String recepient) {
    executor.execute(() -> {
      try {
        System.out.println("Email sent to "+recepient+" via "+Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("Email sent to "+recepient);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
  public static void main(String args[]) {
    for(int i = 1; i <= 25; i++) {
      sendEmail("user-"+i+"@gmail.com");
    }
    executor.shutdown();
  }
}
