import java.util.concurrent.*;

public class ThreadExecutorFuture {
  public static void main(String args[]) throws InterruptedException, ExecutionException {
    ExecutorService execute = Executors.newFixedThreadPool(2);
    
    Future<Integer> value = execute.submit(() -> {
      int x = 42;
      Thread.sleep(1000);
      return x;
    });

    System.out.println("Doing work");

    Integer valueReturned = value.get(); //Blocking
    System.out.println(valueReturned);
    execute.shutdown();
  }
}
