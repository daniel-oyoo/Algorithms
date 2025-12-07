# In-Place Merge Sort

## Available Implementations

### C Implementation
# In-Place Merge Sort

This directory contains an in-place implementation of the **Merge Sort** algorithm. The primary goal of an in-place merge sort is to **sort an array efficiently while minimizing additional memory usage**, especially during the merge step, unlike a traditional merge sort which typically uses a temporary auxiliary array.

---

## Algorithm Description

Merge sort is a highly efficient, comparison-based sorting algorithm based on the **divide and conquer** paradigm.

1.  **Divide**: The unsorted list is recursively divided into `n` sublists, each containing one element (a list of one element is considered sorted).
2.  **Conquer**: Sublists are repeatedly merged to produce new sorted sublists until there is only one sorted list remaining.

The unique aspect of this "in-place" version lies in the **merge step**. While standard merge sort allocates a temporary array to facilitate the merging of two sorted sub-arrays, this implementation performs the merge directly within the original array. This is achieved by carefully **shifting elements** to make room for smaller elements from the second sub-array, effectively inserting them into their correct positions within the first sub-array's space. This avoids the `O(N)` space overhead of a temporary array per merge.

---

## Time and Space Complexity

* ### Time Complexity: O(n log n)
    * The recursive division of the array still results in `log n` levels, similar to a standard merge sort.
    * Although the `inPlaceMerge` function avoids an auxiliary array, the element shifting operations within it can, in the worst case, involve `O(n)` shifts for each merge operation (where `n` is the size of the current sub-array being merged). Despite these shifts, the overall number of comparisons and movements scales to maintain an `O(n log n)` time complexity. However, the constant factor can be higher than an out-of-place merge sort due to the cost of these shifts.

* ### Space Complexity: O(log n)
    * This space complexity is primarily due to the **recursion stack** used during the divide phase of the merge sort. Each recursive call consumes a small amount of memory on the call stack. For `n` elements, there are `log n` levels of recursion.
    * Crucially, the `inPlaceMerge` function itself uses **O(1) auxiliary space** for a few temporary variables. It does not allocate new arrays whose size is proportional to the input size during the merge process, differentiating it from traditional merge sort implementations.

---

## Sample Input/Output

Here are examples demonstrating the sort function's behavior with various inputs:



### Java Implementation (Added for Issue #19)
**File**: `InPlaceMergeSort.java`

**Description**: Java implementation of in-place merge sort that avoids using additional arrays during merging.

**Algorithm**: 
- Uses divide-and-conquer strategy
- Merges in-place using element shifting instead of temporary arrays
- Maintains O(1) auxiliary space for merging operations

**Complexity Analysis**:
- Time Complexity: O(n log n)
- Space Complexity: O(log n) for recursion stack
- Auxiliary Space: O(1) for merging

**Sample Input/Output**:

Input:
```java
int[] arr = {12, 11, 13, 5, 6, 7};
InPlaceMergeSort.sort(arr);
=== In-Place Merge Sort Test Cases ===

Test Case 1: Unsorted Array
Input:  [12, 11, 13, 5, 6, 7]
Output: [5, 6, 7, 11, 12, 13]

Test Case 2: Already Sorted Array
Input:  [1, 2, 3, 4, 5, 6]
Output: [1, 2, 3, 4, 5, 6]

Test Case 3: Reverse Sorted Array
Input:  [9, 8, 7, 6, 5, 4]
Output: [4, 5, 6, 7, 8, 9]

Test Case 4: Array with Duplicate Elements
Input:  [4, 2, 4, 1, 2, 3, 4]
Output: [1, 2, 2, 3, 4, 4, 4]

Test Case 5: Single Element Array
Input:  [42]
Output: [42]

Test Case 6: Empty Array
Input:  []
Output: []

Test Case 7: Larger Array (first 10 elements shown)
Input (first 10):  [64, 34, 25, 12, 22, 11, 90, 88, 75, 50...]
Output (first 10): [11, 12, 22, 25, 33, 34, 44, 50, 55, 64...]

=== Complexity Analysis ===
Time Complexity: O(n log n)
Space Complexity: O(log n) for recursion stack
Auxiliary Space for Merging: O(1)