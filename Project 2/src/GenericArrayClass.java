/**
 * <p>CS-249 Project 2 - GenericArrayClass</p>
 * <p>Description: Description: Class wrapper for a Java array of generic
 * data (classes), with additional management operations</p>
 * <p>Note: Only works with class that extends Comparable, as shown in class
 * declaration</p>
 * <p>Note: Maintains a capacity value for maximum number of items that can
 * be stored, and a size value for the number of valid or viable data items
 * in the array</p>
 *
 * @author Joshua Pollock
 */
public class GenericArrayClass<GenericData
        extends java.lang.Comparable<GenericData>>
        extends java.lang.Object
{
    private static int DEFAULT_CAPACITY = 10;
    private int arrayCapacity;
    private int arraySize;
    private Object[] localArray;

    /**
     * Default constructor, initializes array to default capacity (10)
     */
    public GenericArrayClass()
    {
        arrayCapacity = DEFAULT_CAPACITY;
        arraySize = 0;
        localArray = new Object[arrayCapacity];
    }

    /**
     * Initializing constructor, initializes array to specified capacity
     *
     * @param capacity maximum capacity specification for the array
     */
    public GenericArrayClass(int capacity)
    {
        arrayCapacity = capacity;
        arraySize = 0;
        localArray = new Object[capacity];
    }

    /**
     * Copy constructor, initializes array to size and capacity of
     * copied array, then copies only the elements up to the given size
     *
     * @param copied GenericArrayClass object to be copied
     */
    public GenericArrayClass(GenericArrayClass<GenericData> copied)
    {
        arrayCapacity = copied.getCurrentCapacity();
        arraySize = copied.getCurrentSize();
        localArray = new Object[arrayCapacity];
        for (int i = 0; i < arraySize; i++)
        {
            localArray[i] = copied.accessItemAt(i);
        }
    }

    /**
     * Tests for size of array equal to capacity, no more values can be added
     *
     * @return Boolean result of testing the array for full
     */
    public boolean isFull()
    {
        if (arraySize == arrayCapacity)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Tests for size of array equal to zero, no valid values stored in array
     *
     * @return Boolean result of test for empty
     */
    public boolean isEmpty()
    {
        if (arraySize == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Appends item to end of array, if array is not full, e.g., no more
     * values can be added
     *
     * @param newValue value to be appended to array
     *
     * @return Boolean success if value was appended, or failure if array was
     * full
     */
    public boolean appendItem(GenericData newValue)
    {
        if (isFull())
        {
            return false;
        }
        else
        {
            localArray[arraySize] = newValue;
            arraySize++;
            return true;
        }
    }

    /**
     * <p>Description: Inserts item to array at specified index if array is
     * not full, e.g., no more values can be added</p>
     * <p>Note: Value is inserted at given index, all data from that index
     * to the end of the array is shifted up by one</p>
     *
     * @param insertIndex index of element into which value is to be inserted
     * @param newValue    value to be inserted into array
     *
     * @return Boolean success if inserted, or failure if array was full
     */
    public boolean insertItemAt(int insertIndex, GenericData newValue)
    {
        if (isFull())
        {
            return false;
        }
        else if (insertIndex >= arraySize)
        {
            return false;
        }
        else if (insertIndex < 0)
        {
            return false;
        }
        else if (insertIndex == (arraySize - 1))
        {
            appendItem(newValue);
            return true;
        }

        else
        {
            for (int i = arraySize - 1; i > insertIndex; i--)
            {
                localArray[i + 1] = localArray[i];
            }
            localArray[insertIndex] = newValue;
            arraySize++;
            return true;
        }
    }

    /**
     * Accesses item in array at specified index if index within array size
     * bounds
     *
     * @param accessIndex index of requested element value
     *
     * @return accessed value if successful, null if not
     */
    @SuppressWarnings("unchecked")
    public GenericData accessItemAt(int accessIndex)
    {
        GenericData accessedData;
        if (accessIndex > arraySize)
        {
            return null;
        }
        else if (isEmpty())
        {
            return null;
        }
        else
        {
            accessedData = (GenericData) localArray[accessIndex];
            return accessedData;
        }
    }

    /**
     * <p>Description: Removes item from array at specified index if index
     * within array size bounds</p>
     * <p>Note: Each data item from the element immediately above the remove
     * index to the end of the array is moved down by one element</p>
     *
     * @param removeIndex index of element value to be removed
     *
     * @return removed value if successful, null if not
     */
    @SuppressWarnings("unchecked")
    public GenericData removeItemAt(int removeIndex)
    {
        GenericData accessedData;
        if (removeIndex > arraySize)
        {
            return null;
        }
        else if (isEmpty())
        {
            return null;
        }
        else if (removeIndex < 0)
        {
            return null;
        }
        else
        {

            accessedData = (GenericData) localArray[removeIndex];
            for (int i = removeIndex; i < arraySize; i++)
            {
                localArray[i] = localArray[i + 1];
            }
            arraySize--;
            return accessedData;
        }
    }

    /**
     * <p>Description: Resets array capacity, copies current size and current
     * size number of elements</p>
     * <p>Exception: Method will not resize capacity below current array size,
     * returns false if this is attempted, true otherwise</p>
     *
     * @param newCapacity new capacity to be set; must be larger than current
     *                    capacity
     *
     * @return Boolean condition of resize success or failure
     */
    public boolean resize(int newCapacity)
    {
        int index;
        Object newArray[];
        if (newCapacity > arrayCapacity)
        {
            arrayCapacity = newCapacity;
            newArray = new Object[arrayCapacity];
            for (index = 0; index < arraySize; index++)
            {
                newArray[index] = localArray[index];
            }
            localArray = newArray;
            return true;
        }

        return false;
    }

    /**
     * <p>Description: Sorts elements using the bubble sort algorithm</p>
     * <p>Note: The data is sorted using the compareTo method of the
     * given data set; the compareTo method decides the key and the order
     * resulting</p>
     */
    @SuppressWarnings("unchecked")
    public void runBubbleSort()
    {
        int outerIndex, innerIndex, comparedDataResult;
        GenericData innerIndexObject, outerIndexObject;

        for (outerIndex = 1; outerIndex < arraySize; outerIndex++)
        {
            for (innerIndex = 0; innerIndex < arraySize; innerIndex++)
            {
                innerIndexObject = (GenericData) localArray[innerIndex];
                outerIndexObject = (GenericData) localArray[outerIndex];
                comparedDataResult = outerIndexObject.compareTo
                        (innerIndexObject);

                if (comparedDataResult < 0)
                {
                    swapElements(outerIndex, innerIndex);
                }
            }
        }
    }

    /**
     * <p>Description: Sorts elements using the selection sort algorithm</p>
     * <p>Note: The data is sorted using the compareTo method of the given
     * data set; the compareTo method decides the key and the order
     * resulting</p>
     */
    @SuppressWarnings("unchecked")
    public void runSelectionSort()
    {
        int outerIndex, innerIndex, lowestIndex, comparedDataResult;
        GenericData innerIndexObject, lowestIndexObject;

        for (outerIndex = 0; outerIndex < arraySize - 1; outerIndex++)
        {
            lowestIndex = outerIndex;
            for (innerIndex = lowestIndex + 1; innerIndex < arraySize;
                 innerIndex++)
            {
                lowestIndexObject = (GenericData) localArray[lowestIndex];
                innerIndexObject = (GenericData) localArray[innerIndex];
                comparedDataResult = innerIndexObject.compareTo
                        (lowestIndexObject);
                if (comparedDataResult < 0)
                {
                    lowestIndex = innerIndex;
                }
            }
            swapElements(outerIndex, lowestIndex);
        }
    }

    /**
     * <p>Description: Sorts elements using the insertion sort algorithm</p>
     * <p>Note: The data is sorted using the compareTo method of the given
     * data set; the compareTo method decides the key and the order
     * resulting</p>
     */
    @SuppressWarnings("unchecked")
    public void runInsertionSort()
    {
        int outerIndex, innerIndex, lowestIndex, comparedDataResult;
        GenericData innerIndexObject, outerIndexObject;

        for (outerIndex = 1; outerIndex < arraySize; outerIndex++)
        {
            for (innerIndex = outerIndex; innerIndex > 0; innerIndex--)
            {
                innerIndexObject = (GenericData) localArray[innerIndex];
                outerIndexObject = (GenericData) localArray[innerIndex - 1];

                comparedDataResult = innerIndexObject.compareTo
                        (outerIndexObject);

                if (comparedDataResult > 0)
                {
                    swapElements(innerIndex - 1, innerIndex);
                }
            }
        }
    }

    /**
     * <p>Description: Gets current size of array</p>
     * <p>Note: size of array indicates number of valid or viable values
     * in the array</p>
     *
     * @return size of array
     */
    public int getCurrentSize()
    {
        return arraySize;
    }

    /**
     * <p>Description: Gets current capacity of array</p>
     * <p>Note: capacity of array indicates number of values the array can
     * hold</p>
     *
     * @return capacity of array
     */
    public int getCurrentCapacity()
    {
        return arrayCapacity;
    }

    /**
     * Swaps one element in the local array at a given index with another
     * element in the array at the other given element
     *
     * @param oneIndex   index of one of two elements to be swapped
     * @param otherIndex index of second of two elements to be swapped
     */
    @SuppressWarnings("unchecked")
    private void swapElements(int oneIndex, int otherIndex)
    {
        GenericData accessedData;
        accessedData = (GenericData) localArray[oneIndex];
        localArray[oneIndex] = localArray[otherIndex];
        localArray[otherIndex] = accessedData;
    }

    /**
     * Clears array of all valid values by setting array size to zero,
     * values remain in array but are not accessible
     */
    public void clear()
    {
        arraySize = 0;
    }

}
