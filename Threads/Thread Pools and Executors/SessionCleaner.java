import java.util.concurrent.*;

class SessionCleaner {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
 
        Runnable task = () -> System.out.println("Cleaning up expired sessions...");
 
        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}