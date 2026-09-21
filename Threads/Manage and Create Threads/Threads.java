import java.lang.Thread;

class ThreadImpl extends Thread {
    public void run() {
        System.out.println("In Thread - "+Thread.currentThread().getName());
    }
}

public class Threads {
    public static void main(String[] args) {
        ThreadImpl t1 = new ThreadImpl();
        ThreadImpl t2 = new ThreadImpl();

        t1.start();
        t2.start();
    }
}