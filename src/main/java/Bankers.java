import java.util.*;

public class Bankers {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[][] maximum = new int[10][10];
    int[][] allocation = new int[10][10];
    int[] available = new int[10];
    int[][] need = new int[10][10];
    int[] completed = new int[10];
    int[] safeSequence = new int[10];

    int p, r, i, j, process, count;
    count = 0;
    System.out.print("Enter the number of processes: ");
    p = sc.nextInt();
    for (i = 0; i < p; i++) {
      completed[i] = 0;
    }
    System.out.print("Enter the number of resources: ");
    r = sc.nextInt();
    System.out.println("Enter the maximum matrix: ");
    for (i = 0; i < p; i++) {
      System.out.println("For process: " + (i + 1) + " ");
      for (j = 0; j < r; j++) {
        maximum[i][j] = sc.nextInt();
      }
    }
    System.out.println("Enter the allocation matrix: ");
    for (i = 0; i < p; i++) {
      System.out.println("For process: " + (i + 1) + " ");
      for (j = 0; j < r; j++) {
        allocation[i][j] = sc.nextInt();
      }
    }
    System.out.println("Enter the available resources: ");
    for (i = 0; i < r; i++) {
      available[i] = sc.nextInt();
    }
    for (i = 0; i < p; i++) {
      for (j = 0; j < r; j++) {
        need[i][j] = maximum[i][j] - allocation[i][j];
      }
      do {
        System.out.println("\nMax Matrix:\t\t\tAllocation Matrix:");
        for (i = 0; i < p; i++) {
          for (j = 0; j < r; j++) {
            System.out.print(maximum[i][j] + "\t");
          }
          System.out.print("\t\t\t");
          for (j = 0; j < r; j++) {
            System.out.print(allocation[i][j] + "\t");
          }
          System.out.println();
        }
        process = -1;
        for (i = 0; i < p; i++) {
          if (completed[i] == 0) {
            process = i;
            for (j = 0; j < r; j++) {
              if (need[i][j] > available[j]) {
                process = -1;
                break;
              }
            }
          }
          if (process != -1) {
            break;
          }
        }
        if (process != -1) {
          System.out.println("\nProcess " + (process + 1) + " is executing...");
          safeSequence[count] = process + 1;
          count++;
          for (j = 0; j < r; j++) {
            available[j] += allocation[process][j];
            allocation[process][j] = 0;
            maximum[process][j] = 0;
            completed[process] = 1;
          }
        }
      } while (count != p && process != -1);
      if (count == p) {
        System.out.println("\nThe system is in a safe state.");
        System.out.println("Safe sequence is: ");
        for (i = 0; i < p; i++) {
          System.out.print(safeSequence[i] + " ");
        }
      } else {
        System.out.println("\nThe system is in unsafe state.");
      }
    }
  }
}