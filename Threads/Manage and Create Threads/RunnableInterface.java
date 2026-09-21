import java.lang.Runnable;
import java.lang.Thread;

class ThreadImpl1 implements Runnable {
    @Override
    public void run() {
        System.out.println("In Thread - " + Thread.currentThread().getName());
    }
}

public class RunnableInterface {
    public static void main(String[] args) {
        ThreadImpl1 t1 = new ThreadImpl1();
        ThreadImpl1 t2 = new ThreadImpl1();

        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);

        T1.start();
        T2.start();
    }
}