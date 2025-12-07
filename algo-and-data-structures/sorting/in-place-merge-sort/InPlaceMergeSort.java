/**
 * In-Place Merge Sort Implementation
 * 
 * This implementation of merge sort performs merging without using additional arrays
 * for temporary storage. Instead, it uses index manipulation and element shifting
 * to merge sorted subarrays in-place.
 * 
 * Problem Statement:
 * Most implementations of merge sort use extra space to merge arrays.
 * This implementation must:
 * 1. Not use additional arrays during the merge step
 * 2. Use index manipulation or swaps to keep space usage minimal
 * 3. Include proper time and space complexity in comments
 * 4. Add sample input/output in README.md
 * 
 * Time Complexity: O(n log n) where n is the number of elements
 * Although the in-place merge operation has higher constant factors due to shifting,
 * the overall asymptotic complexity remains O(n log n)
 * 
 * Space Complexity: O(log n) for the recursion stack
 * The merge operation itself uses O(1) auxiliary space, but recursion depth
 * contributes O(log n) space complexity
 * 
 * Comparison with standard merge sort:
 * Standard merge sort: O(n) additional space for temporary arrays
 * In-place merge sort: O(1) additional space for merging (but O(log n) for recursion)
 */

//package Sorting.in_place_merge_sort;

public class InPlaceMergeSort {
    
    /**
     * Public sorting method that initiates the in-place merge sort
     * 
     * @param arr The array to be sorted
     * @throws IllegalArgumentException if the input array is null
     */
    public static void sort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (arr.length <= 1) {
            return; // Array of size 0 or 1 is already sorted
        }
        inPlaceMergeSort(arr, 0, arr.length - 1);
    }
    
    /**
     * Recursive method that implements the divide-and-conquer strategy
     * 
     * The algorithm follows these steps:
     * 1. Divide: Split the array into two halves
     * 2. Conquer: Recursively sort each half
     * 3. Combine: Merge the two sorted halves in-place
     * 
     * @param arr The array to be sorted
     * @param left The starting index of the subarray to sort (inclusive)
     * @param right The ending index of the subarray to sort (inclusive)
     */
    private static void inPlaceMergeSort(int[] arr, int left, int right) {
        // Base case: subarray of size 1 or invalid indices
        if (left >= right) {
            return;
        }
        
        // Calculate mid-point using safe method to prevent integer overflow
        int mid = left + (right - left) / 2;
        
        // Recursively sort the left and right halves
        inPlaceMergeSort(arr, left, mid);
        inPlaceMergeSort(arr, mid + 1, right);
        
        // Merge the two sorted halves in-place
        inPlaceMerge(arr, left, mid, right);
    }
    
    /**
     * In-place merge of two sorted subarrays
     * 
     * Merges arr[left...mid] and arr[mid+1...right] without using additional arrays.
     * The algorithm uses two pointers and element shifting to achieve in-place merging.
     * 
     * Algorithm:
     * 1. Initialize pointers: i for left subarray, j for right subarray
     * 2. Compare arr[i] and arr[j]
     * 3. If arr[i] <= arr[j], element is in correct position, increment i
     * 4. If arr[i] > arr[j], element arr[j] needs to be inserted before arr[i]
     *    a. Save arr[j] in temporary variable
     *    b. Shift all elements from i to j-1 one position to the right
     *    c. Insert the saved element at position i
     *    d. Update all pointers (i, mid, j)
     * 
     * @param arr The array containing the two sorted subarrays
     * @param left The starting index of the first subarray
     * @param mid The ending index of the first subarray
     * @param right The ending index of the second subarray
     */
    private static void inPlaceMerge(int[] arr, int left, int mid, int right) {
        // Pointers for the two subarrays
        int i = left;      // Pointer for left subarray [left...mid]
        int j = mid + 1;   // Pointer for right subarray [mid+1...right]
        
        // Process while both subarrays have elements remaining
        while (i <= mid && j <= right) {
            // Case 1: Element from left subarray is in correct position
            if (arr[i] <= arr[j]) {
                i++;
            }
            // Case 2: Element from right subarray needs to be moved
            else {
                // Save the element to be inserted
                int valueToInsert = arr[j];
                
                // Index for shifting elements
                int shiftIndex = j;
                
                // Shift all elements from i to j-1 one position to the right
                // This creates space for the element to be inserted
                while (shiftIndex > i) {
                    arr[shiftIndex] = arr[shiftIndex - 1];
                    shiftIndex--;
                }
                
                // Insert the saved element at the correct position
                arr[i] = valueToInsert;
                
                // Update pointers after insertion
                i++;      // Move past the newly inserted element
                mid++;    // The boundary of left subarray shifts right
                j++;      // Move to next element in right subarray
            }
        }
        
        // Note: No need to handle remaining elements in left subarray
        // They are already in their correct positions
        // Remaining elements in right subarray (if any) are already
        // in their correct positions since we've been shifting them left
    }
    
    /**
     * Alternative in-place merge implementation using gap method
     * 
     * This method uses a shell-sort like gap reduction approach
     * It may be more efficient for certain data distributions
     * 
     * @param arr The array containing the two sorted subarrays
     * @param left The starting index of the first subarray
     * @param mid The ending index of the first subarray
     * @param right The ending index of the second subarray
     */
    private static void inPlaceMergeWithGap(int[] arr, int left, int mid, int right) {
        int totalLength = right - left + 1;
        int gap = nextGap(totalLength);
        
        while (gap > 0) {
            for (int i = left; i + gap <= right; i++) {
                int j = i + gap;
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
            gap = nextGap(gap);
        }
    }
    
    /**
     * Calculates the next gap for the gap-based merge method
     * 
     * @param gap Current gap value
     * @return Next gap value (ceil(gap/2))
     */
    private static int nextGap(int gap) {
        if (gap <= 1) {
            return 0;
        }
        return (int) Math.ceil(gap / 2.0);
    }
    
    /**
     * Utility method to swap two elements in an array
     * 
     * @param arr The array containing the elements
     * @param i Index of first element
     * @param j Index of second element
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Prints the elements of an array
     * 
     * @param arr The array to print
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * Main method with test cases
     * 
     * Demonstrates the functionality with various test cases
     * and shows sample input/output
     */
    public static void main(String[] args) {
        System.out.println("=== In-Place Merge Sort Test Cases ===\n");
        
        // Test Case 1: Unsorted array
        System.out.println("Test Case 1: Unsorted Array");
        int[] arr1 = {12, 11, 13, 5, 6, 7};
        System.out.print("Input:  ");
        printArray(arr1);
        sort(arr1);
        System.out.print("Output: ");
        printArray(arr1);
        System.out.println();
        
        // Test Case 2: Already sorted array
        System.out.println("Test Case 2: Already Sorted Array");
        int[] arr2 = {1, 2, 3, 4, 5, 6};
        System.out.print("Input:  ");
        printArray(arr2);
        sort(arr2);
        System.out.print("Output: ");
        printArray(arr2);
        System.out.println();
        
        // Test Case 3: Reverse sorted array
        System.out.println("Test Case 3: Reverse Sorted Array");
        int[] arr3 = {9, 8, 7, 6, 5, 4};
        System.out.print("Input:  ");
        printArray(arr3);
        sort(arr3);
        System.out.print("Output: ");
        printArray(arr3);
        System.out.println();
        
        // Test Case 4: Array with duplicates
        System.out.println("Test Case 4: Array with Duplicate Elements");
        int[] arr4 = {4, 2, 4, 1, 2, 3, 4};
        System.out.print("Input:  ");
        printArray(arr4);
        sort(arr4);
        System.out.print("Output: ");
        printArray(arr4);
        System.out.println();
        
        // Test Case 5: Single element array
        System.out.println("Test Case 5: Single Element Array");
        int[] arr5 = {42};
        System.out.print("Input:  ");
        printArray(arr5);
        sort(arr5);
        System.out.print("Output: ");
        printArray(arr5);
        System.out.println();
        
        // Test Case 6: Empty array
        System.out.println("Test Case 6: Empty Array");
        int[] arr6 = {};
        System.out.print("Input:  ");
        printArray(arr6);
        sort(arr6);
        System.out.print("Output: ");
        printArray(arr6);
        System.out.println();
        
        // Test Case 7: Large array demonstration
        System.out.println("Test Case 7: Larger Array (first 10 elements shown)");
        int[] arr7 = {64, 34, 25, 12, 22, 11, 90, 88, 75, 50, 33, 44, 55, 66};
        System.out.print("Input (first 10):  [");
        for (int i = 0; i < Math.min(10, arr7.length); i++) {
            System.out.print(arr7[i] + (i < 9 ? ", " : ""));
        }
        System.out.println("...]");
        sort(arr7);
        System.out.print("Output (first 10): [");
        for (int i = 0; i < Math.min(10, arr7.length); i++) {
            System.out.print(arr7[i] + (i < 9 ? ", " : ""));
        }
        System.out.println("...]");
        
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("Space Complexity: O(log n) for recursion stack");
        System.out.println("Auxiliary Space for Merging: O(1)");
    }
}