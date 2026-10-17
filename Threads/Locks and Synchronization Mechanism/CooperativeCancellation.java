class FileProcessing {
  public void processFile() {
    try {
      for (int i = 0; i < 100; i++) {

        // Check whether another thread has requested cancellation.
        // isInterrupted() checks the status without clearing it.
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("File Processing Stopped!");
          return;
        }

        System.out.println("File Processing - " + i);

        // Simulate processing a file.
        // If interrupted during sleep, InterruptedException is thrown.
        Thread.sleep(100);
      }
    } catch (InterruptedException e) {

      // sleep() clears the interrupt status when it throws this exception.
      // Restore the status so higher-level code can detect the interruption.
      Thread.currentThread().interrupt();

      System.out.println("Processing interrupted and cancelled!");
    }
  }
}

public class CooperativeCancellation {
  public static void main(String args[]) throws InterruptedException {
    FileProcessing fp = new FileProcessing();

    // Create a separate worker thread to process files concurrently.
    Thread worker = new Thread(() -> {
      fp.processFile();
    });

    // Start file processing on the worker thread.
    worker.start();

    // The main thread waits 300 ms while the worker processes files.
    Thread.sleep(300);

    // Request cancellation of the worker thread.
    // This does not forcibly terminate it.
    worker.interrupt();
  }
}