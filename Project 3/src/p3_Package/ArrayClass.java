package p3_Package;

/**
 * Description: Class wrapper for a Java array, with additional management
 * operations
 * <p>
 * Note: Maintains a capacity value for maximum number of items that can be
 * stored,
 * and a size value for the number of valid or viable data items in the array
 * <p>
 * Note: Contains a quick sorting method, a merge sorting method, and a private
 * element swapping method
 *
 * @author Joshua Pollock
 */

public class ArrayClass
{
    public final static int FAILED_ACCESS = -999999;

    private static final int DEFAULT_CAPACITY = 10;

    private int[] localArray;
    private int arraySize, arrayCapacity;

    /**
     * Default constructor, initializes array to default capacity (10)
     */
    public ArrayClass()
    {
        localArray = new int[DEFAULT_CAPACITY];

        arrayCapacity = DEFAULT_CAPACITY;

        arraySize = 0;
    }

    /**
     * Copy constructor, initializes array to size and capacity of copied array,
     * then copies only the elements up to the given size
     *
     * @param copied ArrayClass object to be copied
     */
    public ArrayClass(ArrayClass copied)
    {
        int index;

        this.arrayCapacity = copied.arrayCapacity;

        this.arraySize = copied.arraySize;

        this.localArray = new int[this.arrayCapacity];

        for (index = 0; index < arraySize; index++)
        {
            this.localArray[index] = copied.localArray[index];
        }
    }

    /**
     * Initializing constructor, initializes array to specified capacity
     *
     * @param capacity maximum capacity specification for the array
     */
    public ArrayClass(int capacity)
    {
        localArray = new int[capacity];

        arrayCapacity = capacity;

        arraySize = 0;
    }

    /**
     * Initializing constructor, initializes array to specified capacity,
     * size to specified value, then fills all elements with specified size
     * value
     *
     * @param capacity  maximum capacity specification for the array
     * @param size      sets the number of items to be filled in array,
     *                  and sets the size of the ArrayClass object
     * @param fillValue value to be placed in all elements of initialized array
     *                  up to the size
     */
    public ArrayClass(int capacity, int size, int fillValue)
    {
        int index;

        localArray = new int[capacity];

        arrayCapacity = capacity;

        arraySize = size;

        for (index = 0; index < arraySize; index++)
        {
            localArray[index] = fillValue;
        }
    }

    /**
     * Loads a specified number of unique random numbers in object
     * <p>
     * Note: This method overwrites all data in the array up to the number of
     * randoms requested
     * <p>
     * Note: If requested number of randoms is greater than the array capacity,
     * the array is resized
     * <p>
     * Note: Size is set to number of random numbers requested
     * <p>
     * Exceptional Condition: If more values are requested than are possible
     * given the range of numbers, method returns false, otherwise, it returns
     * true
     *
     * @param numRands  number of random values requested
     * @param lowLimit  lowest value to be generated
     * @param highLimit highest value to be generated
     *
     * @return Boolean true if method successful; false otherwise
     */

    public boolean loadUniqueRandoms(int numRands, int lowLimit, int highLimit)
    {
        int workingIndex;

        if (numRands > (highLimit - lowLimit))
        {
            return false;
        }

        else if (lowLimit > highLimit)
        {
            return false;
        }

        else if (numRands > arrayCapacity)
        {
            resize(numRands);

        }

        for (workingIndex = 0; workingIndex < numRands; workingIndex++)
        {
            int randomNum = lowLimit + (int) (Math.random() * highLimit);

            localArray[workingIndex] = randomNum;
        }
        arraySize = numRands;
        return true;
    }

    /**
     * Tests for value found in object array; returns true if value within
     * array, false otherwise
     *
     * @param testVal value to be tested
     *
     * @return Boolean true if value is found in array, false otherwise
     */
    public boolean isInArray(int testVal)
    {
        int index;

        for (index = 0; index < arraySize; index++)
        {
            if (localArray[index] == testVal)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Tests for size of array equal to capacity,
     * no more values can be added
     *
     * @return Boolean result of test for full
     */
    public boolean isFull()
    {
        return (arraySize == arrayCapacity);
    }

    /**
     * Tests for size of array equal to zero,
     * no valid values stored in array
     *
     * @return Boolean result of test for empty
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }

    /**
     * Appends item to end of array, if array is not full,
     * e.g., no more values can be added
     *
     * @param newValue value to be appended to array
     *
     * @return Boolean success if appended, or failure if array was full
     */
    public boolean appendItem(int newValue)
    {
        if (!isFull())
        {
            localArray[arraySize] = newValue;

            arraySize++;

            return true;
        }

        return false;
    }

    /**
     * Description: Inserts item to array at specified index if array is not
     * full,
     * e.g., no more values can be added
     * <p>
     * Note: Value is inserted at given index, all data from that index to the
     * end of the array
     * is shifted up by one
     * <p>
     * Note: Value can be inserted after the last valid element
     * but not at any index past that point
     *
     * @param insertIndex index of element into which value is to be inserted
     * @param newValue    value to be inserted into array
     *
     * @return Boolean success if inserted, or failure if array was full
     */
    public boolean insertItemAt(int insertIndex, int newValue)
    {
        int workingIndex;

        if (!isFull() && insertIndex >= 0 && insertIndex <= arraySize)
        {
            workingIndex = arraySize;

            while (workingIndex > insertIndex)
            {
                localArray[workingIndex] = localArray[workingIndex - 1];

                workingIndex--;
            }

            localArray[workingIndex] = newValue;

            arraySize++;

            return true;
        }

        return false;
    }

    /**
     * Accesses item in array at specified index if index within array size
     * bounds
     *
     * @param accessIndex index of requested element value
     *
     * @return accessed value if successful, FAILED_ACCESS (-999999) if not
     */
    public int accessItemAt(int accessIndex)
    {
        if (accessIndex >= 0 && accessIndex < arraySize)
        {
            return localArray[accessIndex];
        }

        return FAILED_ACCESS;
    }

    /**
     * Description: Removes item from array at specified index if index within
     * array size bounds
     * <p>
     * Note: Each data item from the element immediately above the remove index
     * to the end of the array
     * is moved down by one element
     *
     * @param removeIndex index of element value to be removed
     *
     * @return removed value if successful, FAILED_ACCESS (-999999) if not
     */
    public int removeItemAt(int removeIndex)
    {
        int workingIndex, returnedItem;

        if (removeIndex >= 0 && removeIndex < arraySize)
        {
            returnedItem = localArray[removeIndex];

            workingIndex = removeIndex;

            arraySize--;

            while (workingIndex < arraySize)
            {
                localArray[workingIndex] = localArray[workingIndex + 1];

                workingIndex++;
            }

            return returnedItem;
        }

        return FAILED_ACCESS;
    }

    /**
     * Description: Resets array capacity, copies current size and current size
     * number of elements
     * <p>
     * Exception: Method will not resize capacity below current array size,
     * returns false if this is attempted, true otherwise
     *
     * @param newCapacity new capacity to be set; must be larger than current
     *                    capacity
     *
     * @return Boolean condition of resize success or failure
     */
    public boolean resize(int newCapacity)
    {
        int[] newArray;
        int workingIndex;

        if (newCapacity > arrayCapacity)
        {
            newArray = new int[newCapacity];

            for (workingIndex = 0;
                 workingIndex < arraySize; workingIndex++)
            {
                newArray[workingIndex] = localArray[workingIndex];
            }

            arrayCapacity = newCapacity;

            localArray = newArray;

            return true;
        }

        return false;
    }

    /**
     * Data sorted using merge sort algorithm
     * <p>
     * Note: Call runMergeSortHelper with lower and upper indices of array to be
     * sorted
     */
    public void runMergeSort()
    {
        mergeSortHelper(0, arraySize - 1);
    }

    /**
     * Helper method for the merge sort algorithm
     * <p>
     * Note: Call mergeSortHelper recursively with {@code lowerIndex} and middle
     * indices
     * <p>
     * Note: Call mergeSortHelper recursively with middle + 1 and {@code
     * higherIndex} indices
     * <p>
     * Note: Call mergeSort with {@code lowerIndex}, {@code higherIndex},
     * and middle indices of the array to be sorted
     *
     * @param lowerIndex  lower index of the merge sort
     * @param higherIndex top index of the merge sort
     */
    private void mergeSortHelper(int lowerIndex, int higherIndex)
    {
        if (lowerIndex < higherIndex)
        {
            int middleIndex = lowerIndex + (higherIndex - lowerIndex) / 2;

            mergeSortHelper(lowerIndex, middleIndex);

            mergeSortHelper(middleIndex + 1, higherIndex);

            mergeSort(lowerIndex, middleIndex, higherIndex);


        }
    }

    /**
     * Sorting method for the merge sort algorithm
     *
     * @param lowerIndex  lower index of the merge sort
     * @param middleIndex middle index of the array
     * @param higherIndex top index of the merge sort
     */
    private void mergeSort(int lowerIndex, int middleIndex, int higherIndex)
    {
        int workingIndex, midIndex = middleIndex + 1, secondIndex = lowerIndex;

        int[] mergeArray = new int[arraySize];

        for (workingIndex = lowerIndex; workingIndex <= higherIndex;
             workingIndex++)
        {
            mergeArray[workingIndex] = localArray[workingIndex];
        }

        workingIndex = lowerIndex;

        while (middleIndex >= workingIndex && higherIndex >= midIndex)
        {

            if (mergeArray[midIndex] >= mergeArray[workingIndex])
            {
                localArray[secondIndex] = mergeArray[workingIndex];
                workingIndex++;
            }

            else
            {
                localArray[secondIndex] = mergeArray[midIndex];
                midIndex++;
            }

            secondIndex++;
        }

        while (middleIndex >= workingIndex)
        {
            localArray[secondIndex] = mergeArray[workingIndex];

            secondIndex++;

            workingIndex++;
        }
    }

    /**
     * Data sorted using quick sort algorithm
     * <p>
     * Note: Call runQuickSortHelper with lower and upper indices of array to be
     * sorted
     */
    public void runQuickSort()
    {
        quickSortHelper(0, arraySize - 1);
    }

    /**
     * Helper and sorting method for the quick sort algorithm
     * <p>
     * Note: Recursively calls quickSortHelper with {@code low} and calculated
     * upper index
     * <p>
     * Note: Recursively calls quickSortHelper with calculated lower index and
     * {@code high}
     *
     * @param high the upper index passed through
     * @param low  the lower index passed through
     */
    private void quickSortHelper(int low, int high)
    {
        if (arraySize == 0)
        {
            return;
        }

        else if (low >= high)
        {
            return;
        }

        int middle = low + ((high - low) / 2),
                pivotPoint = localArray[middle],
                lowerIndex = low, upperIndex = high;

        while (upperIndex >= lowerIndex)
        {

            while (pivotPoint > localArray[lowerIndex])
            {
                lowerIndex++;
            }

            while (pivotPoint < localArray[upperIndex])
            {
                upperIndex--;
            }

            if (lowerIndex <= upperIndex)
            {
                swapElements(lowerIndex, upperIndex);

                lowerIndex++;

                upperIndex--;
            }
        }

        if (low < upperIndex)
        {
            quickSortHelper(low, upperIndex);
        }

        if (high > lowerIndex)
        {
            quickSortHelper(lowerIndex, high);
        }
    }

    /**
     * Description: Gets current size of array
     * <p>
     * Note: size of array indicates number of valid or viable values in the
     * array
     *
     * @return size of array
     */
    public int getCurrentSize()
    {
        return arraySize;
    }

    /**
     * Description: Gets current capacity of array
     * <p>
     * Note: capacity of array indicates number of values the array can hold
     *
     * @return capacity of array
     */
    public int getCurrentCapacity()
    {
        return arrayCapacity;
    }

    /**
     * Clears array of all valid values by setting array size to zero,
     * values remain in array but are not accessible
     */
    public void clear()
    {
        arraySize = 0;
    }

    /**
     * Takes in two indices and swaps their position in the array
     * <p>
     * Note: Since this is a private method, no checks should be needed. This
     * method WILL cause errors if used improperly
     *
     * @param oneIndex   One index position to be swapped
     * @param otherIndex Another index position to be swapped
     */
    private void swapElements(int oneIndex, int otherIndex)
    {
        int tmp = localArray[oneIndex];

        localArray[oneIndex] = localArray[otherIndex];

        localArray[otherIndex] = tmp;
    }
}