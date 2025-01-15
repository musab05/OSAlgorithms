import java.util.*;

class Memory {
  int size, allocation;
}

public class Fitalgo {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Memory[] m = new Memory[10];
    int i = 0, j, min, max, diff;
    int[] pro = new int[20];
    int n, memorySize;
    int[][] proallot = new int[20][3];

    System.out.println("Enter total memory size: ");
    memorySize = sc.nextInt();

    System.out.println("Enter number of processes");
    n = sc.nextInt();

    for (i = 0; i < 10; i++) {
      m[i] = new Memory();
      m[i].size = memorySize / 10; // Equal partitioning of memory
    }

    for (i = 0; i < 10; i++) {
      m[i].allocation = 0;
    }

    System.out.println("Enter the size of all the processes");
    for (i = 0; i < n; i++) {
      System.out.println("Enter the size of process " + i);
      pro[i] = sc.nextInt();
    }
    int ch;
    while (true) {
      System.out.println("1-First Fit\n2-Best Fit\n3-Worst Fit\n4-Exit");
      ch = sc.nextInt();
      switch (ch) {
        case 1:
          for (i = 0; i < n; i++) {
            min = 10000;
            for (j = 0; j < 10; j++) {
              diff = m[j].size - pro[i];
              if (diff >= 0 && diff < min && m[j].allocation != 1) {
                min = diff;
                proallot[i][0] = j;
              }
            }
            if (proallot[i][0] >= 0 && proallot[i][0] <= 9) {
              m[proallot[i][0]].allocation = 1;
            } else {
              m[proallot[i][0]].allocation = -999;
            }
          }

          for (i = 0; i < 10; i++) {
            m[i].allocation = 0;
          }

          System.out.println("Job no. First fit");
          for (i = 0; i < n; i++) {
            System.out.printf("%8d%10d\n", i, proallot[i][0]);
          }
          break;
        case 2:
          for (i = 0; i < n; i++) {
            max = -10000;
            for (j = 0; j < 10; j++) {
              diff = m[j].size - pro[i];
              if (diff >= 0 && diff > max && m[j].allocation != 1) {
                max = diff;
                proallot[i][1] = j;
              }
            }
            if (proallot[i][1] >= 0 && proallot[i][1] <= 9) {
              m[proallot[i][1]].allocation = 1;
            } else {
              proallot[i][1] = -999;
            }
          }

          for (i = 0; i < 10; i++) {
            m[i].allocation = 0;
          }

          System.out.println("Job no. Best fit");
          for (i = 0; i < n; i++) {
            System.out.printf("%8d%10d\n", i, proallot[i][1]);
          }
          break;
        case 3:
          for (i = 0; i < n; i++) {
            for (j = 0; j < 10; j++) {
              if (m[j].allocation != 1 && m[j].size >= pro[i]) {
                proallot[i][2] = j;
                break;
              }
            }
            if (proallot[i][2] >= 0 && proallot[i][2] <= 9) {
              m[proallot[i][2]].allocation = 1;
            } else {
              proallot[i][2] = -999;
            }
          }
          System.out.println("Job no. Worst fit");
          for (i = 0; i < n; i++) {
            System.out.printf("%8d%10d\n", i, proallot[i][2]);
          }
          break;
        case 4:
          System.exit(0);
          break;
      }
    }
  }
}
