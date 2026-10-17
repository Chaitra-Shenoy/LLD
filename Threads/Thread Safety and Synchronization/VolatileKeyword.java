// Flow:
// 1. Thread 1 keeps running while running == true.
// 2. Thread 2 sets running = false.
// 3. Because running is volatile, Thread 1 sees the updated value and exits.

// Key point: volatile guarantees visibility, not atomicity.
class Worker {
    private volatile boolean running = true;

    public void work() {
        while (running) {
            // Keep working
        }
        System.out.println("Thread 1 stopped");
    }

    public void stop() {
        running = false;
    }
}

public class VolatileKeyword {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();

        Thread t1 = new Thread(() -> worker.work()); // Thread 1
        Thread t2 = new Thread(() -> worker.stop()); // Thread 2

        t1.start();

        Thread.sleep(1000);
        t2.start();

        t1.join();
        t2.join();
    }
}