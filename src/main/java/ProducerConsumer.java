import java.util.*;

public class ProducerConsumer {
  static int mutex = 1;
  static int full = 0;
  static int empty = 3;
  static int x = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("\n1. Producer\n2. Consumer\n3. Exit");
    while (true) {
      System.out.println("\nEnter your choice: ");
      int n = sc.nextInt();
      switch (n) {
        case 1:
          if (mutex == 1 && empty != 0) {
            producer();
          } else {
            System.out.println("Buffer is full");
          }
          break;
        case 2:
          if (mutex == 1 && full != 0) {
            consumer();
          } else {
            System.out.println("Buffer is empty");
          }
          break;
        case 3:
          System.exit(0);
          break;
      }
    }
  }

  public static int wait(int s) {
    return --s;
  }

  public static int signal(int s) {
    return ++s;
  }

  public static void producer() {
    mutex = wait(mutex);
    full = signal(full);
    empty = wait(empty);
    x++;
    System.out.println("\nProducer produced the item: " + x);
    mutex = signal(mutex);
  }

  public static void consumer() {
    mutex = wait(mutex);
    full = wait(full);
    empty = signal(empty);
    System.out.println("\nConsumer consumed the item: " + x);
    x--;
    mutex = signal(mutex);
  }
}