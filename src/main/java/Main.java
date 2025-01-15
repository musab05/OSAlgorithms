import java.util.*;

public class Main {
    static class MemoryBlock {
        int size;
        boolean allocated;

        MemoryBlock(int size, boolean allocated) {
            this.size = size;
            this.allocated = allocated;
        }
    }

    static class MemoryAllocator {
        List<MemoryBlock> memory;

        MemoryAllocator(int[] blockSize) {
            memory = new ArrayList<>();
            for (int size : blockSize) {
                memory.add(new MemoryBlock(size, false));
            }
        }

        // First Fit Algorithm
        public int firstFit(int processSize) {
            for (int i = 0; i < memory.size(); i++) {
                MemoryBlock block = memory.get(i);
                if (!block.allocated && block.size >= processSize) {
                    block.allocated = true;
                    return i;
                }
            }
            return -1; // No suitable block found
        }

        // Best Fit Algorithm
        public int bestFit(int processSize) {
            int bestFitIndex = -1;
            int minFragmentation = Integer.MAX_VALUE;

            for (int i = 0; i < memory.size(); i++) {
                MemoryBlock block = memory.get(i);
                if (!block.allocated && block.size >= processSize) {
                    int fragmentation = block.size - processSize;
                    if (fragmentation < minFragmentation) {
                        minFragmentation = fragmentation;
                        bestFitIndex = i;
                    }
                }
            }

            if (bestFitIndex != -1) {
                memory.get(bestFitIndex).allocated = true;
            }
            return bestFitIndex;
        }

        // Worst Fit Algorithm
        public int worstFit(int processSize) {
            int worstFitIndex = -1;
            int maxFragmentation = Integer.MIN_VALUE;

            for (int i = 0; i < memory.size(); i++) {
                MemoryBlock block = memory.get(i);
                if (!block.allocated && block.size >= processSize) {
                    int fragmentation = block.size - processSize;
                    if (fragmentation > maxFragmentation) {
                        maxFragmentation = fragmentation;
                        worstFitIndex = i;
                    }
                }
            }

            if (worstFitIndex != -1) {
                memory.get(worstFitIndex).allocated = true;
            }
            return worstFitIndex;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of memory blocks: ");
        int numMemoryBlocks = scanner.nextInt();
        int[] blockSize = new int[numMemoryBlocks];
        for (int i = 0; i < numMemoryBlocks; i++) {
            System.out.print("Enter size of block " + (i + 1) + ": ");
            blockSize[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of process blocks: ");
        int numProcessBlocks = scanner.nextInt();
        int[] processSize = new int[numProcessBlocks];
        for (int i = 0; i < numProcessBlocks; i++) {
            System.out.print("Enter size of process " + (i + 1) + ": ");
            processSize[i] = scanner.nextInt();
        }

        MemoryAllocator allocator = new MemoryAllocator(blockSize);

        System.out.println("First Fit Allocation:");
        for (int size : processSize) {
            System.out.println("Process of size " + size + " allocated at block: " + allocator.firstFit(size));
        }

        System.out.println("\nBest Fit Allocation:");
        for (int size : processSize) {
            System.out.println("Process of size " + size + " allocated at block: " + allocator.bestFit(size));
        }

        System.out.println("\nWorst Fit Allocation:");
        for (int size : processSize) {
            System.out.println("Process of size " + size + " allocated at block: " + allocator.worstFit(size));
        }

        scanner.close();
    }
}
