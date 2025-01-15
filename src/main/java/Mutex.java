import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mutex {
  static Thread[] tid = new Thread[5];
  static Lock lock = new ReentrantLock();
  static AtomicInteger counter = new AtomicInteger(0);

  public static void main(String[] args) {
    int i = 0;
    while (i < 5) {
      tid[i] = new Thread(Mutex::tryThis);
      tid[i].start();
      i++;
    }

    try {
      for (Thread t : tid) {
        t.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void tryThis() {
    long i = 0;
    try {
      lock.lock();
      int currentCounter = counter.incrementAndGet();
      System.out.println("\nJob " + currentCounter + " has started");

      for (i = 0; i < (0xFFFFFFFFL); i++)
        ;

      System.out.println("\nJob " + currentCounter + " has finished");
    } finally {
      lock.unlock();
    }
  }
}
