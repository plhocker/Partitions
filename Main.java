import java.util.Arrays;

public class Main {
    public static void printArray(int[] p, int n) {
        for (int i = 0; i < n; i++)
            System.out.print(p[i]+" ");
        System.out.println();
    }

    public static void printAllUniqueParts(int n, int[] requiredNumbers, int[] forbiddenNumbers, int maximumLength) {

        int[] p = new int[n]; // An array to store a partition
        int k = 0;  // Index of last element in a partition
        p[k] = n;  // Initialize first partition as number itself

        // This loop first prints current partition then generates next
        // partition. The loop stops when the current partition has all 1s
        while (true) {
            // print current partition
            // printArray(p, k+1);
            if (isLegal(Arrays.copyOfRange(p, 0, k + 1), requiredNumbers, forbiddenNumbers, 50, maximumLength)) {
                printArray(p, k+1);
            }

            // Generate next partition

            // Find the rightmost non-one value in p[]. Also, update the
            // rem_val so that we know how much value can be accommodated
            int rem_val = 0;
            while (k >= 0 && p[k] == 1)
            {
                rem_val += p[k];
                k--;
            }

            // if k < 0, all the values are 1 so there are no more partitions
            if (k < 0) {
                return;
            }

            // Decrease the p[k] found above and adjust the rem_val
            p[k]--;
            rem_val++;


            // If rem_val is more, then the sorted order is violated.  Divide
            // rem_val in different values of size p[k] and copy these values at
            // different positions after p[k]
            while (rem_val > p[k])
            {
                p[k+1] = p[k];
                rem_val = rem_val - p[k];
                k++;
            }

            // Copy rem_val to next position and increment position
            p[k+1] = rem_val;
            k++;
        }
    }

    public static boolean isLegal(int[] testList, int[] requirementList, int[] forbiddenList, int maximum, int maximumLength) {
        return containsAll(testList, requirementList) && inBounds(testList, maximum) && !containsDuplicate(testList) && containsNone(testList, forbiddenList) && testList.length <= maximumLength;
    }

    public static boolean containsAll(int[] testList, int[] requirementList) {
        boolean contains = true;
        for (int value : requirementList) {
            if (!contains(testList, value)) {
                contains = false;
            }
        }
        return contains;
    }

    public static boolean containsNone(int[] testList, int[] forbiddenList) {
        boolean containsNone = true;
        for (int value : forbiddenList) {
            if (contains(testList, value)) {
                containsNone = false;
            }
        }
        return containsNone;
    }

    public static boolean contains(int[] array, int v) {

        boolean result = false;

        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }

        return result;
    }

    public static boolean inBounds(int[] testList, int maximum) {
        for (int value : testList) {
            if (value > maximum) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsDuplicate(int[] testList) {
        for (int i = 0; i < testList.length; i++) {
            for (int j = i + 1; j < testList.length; j++) {
                if (testList[i] == testList[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * This part can be used to compute and print all partitions of a certain number without repetition
     * In required you can choose numbers which must be included
     * In prohibited you can choose numbers which must be excluded
     * maximumLength defines the maximum number of terms in a partition
     */
    public static void main (String[] args) {
        int[] required = {14,18};
        int[] prohibited = {12,11,13,2,5,8,7,6,23,24,22,21,20,4,3,43,42,34,33,32,28,27,26,25,19,15};
        int maximumLength = 7;
        printAllUniqueParts(75, required, prohibited, maximumLength);
    }
}
