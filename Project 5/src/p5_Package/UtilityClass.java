package p5_Package;

/**
 * Utility class for three different ways of sorting/managing integers in an
 * array. These three ways are a queue, a stack, or an iterator.
 *
 * @author Joshua Pollock
 */
public class UtilityClass
{

    /**
     * Provides constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;

    /**
     * Provides a constant 0 for the iterator type of utility class
     */
    public static int ITER_TYPE = 101;

    /**
     * Provides a constant 1 for the queue type of utility class
     */
    public static int QUEUE_TYPE = 102;

    /**
     * Provides a constant 2 for the stack type of utility class
     */
    public static int STACK_TYPE = 103;

    /**
     * Stores current capacity of utility class
     */
    private int capacity;

    /**
     * Stores current size of utility class
     */
    private int size;

    /**
     * Stores type of utility class between ITER_TYPE, QUEUE_TYPE, and
     * STACK_TYPE
     */
    private int utilityType;

    private int iteratorIndex;
    private int queueHeadIndex;
    private int queueTailIndex;
    private int stackTopIndex;

    private int[] storage;

    /**
     * Initialization constructor
     * <p>
     * Selectable utilities that persist for the lifetime of the object
     *
     * @param capacitySetting    initial capacity of storage class
     * @param utilityTypeSetting user selectable between ITER_TYPE, QUEUE_TYPE,
     *                           STACK_TYPE
     */
    public UtilityClass(int capacitySetting, int utilityTypeSetting)
    {
        capacity = capacitySetting;
        size = 0;
        utilityType = utilityTypeSetting;
        storage = new int[capacity];
        if (utilityType == ITER_TYPE)
        {
            iteratorIndex = 0;
        }
        else
        {
            if (utilityType == QUEUE_TYPE)
            {
                queueHeadIndex = 0;
                queueTailIndex = 0;
            }
            else
            {
                if (utilityType == STACK_TYPE)
                {
                    stackTopIndex = 0;
                }
            }
        }
    }

    /**
     * Adds (appends) item to end of iterator list
     *
     * @param newValue Value to be added to list
     *
     * @return Boolean success of operation
     */
    public boolean iAdd(int newValue)
    {
        if (utilityType == ITER_TYPE)
        {
            checkForReSize();

            storage[size] = newValue;
            size++;

            return true;
        }
        return false;
    }

    /**
     * Gets value at current location of iterator
     *
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int iGetAtCurrent()
    {
        if (utilityType == ITER_TYPE && !isEmpty())
        {
            return storage[iteratorIndex];
        }
        return FAILED_ACCESS;
    }

    /**
     * Move iterator cursor to next item if not currently at end
     *
     * @return Boolean success of operation
     */
    public boolean iMoveNext()
    {
        if (utilityType == ITER_TYPE)
        {
            if (iteratorIndex < size)
            {

                iteratorIndex++;

                return true;
            }

        }
        return false;
    }

    /**
     * Move iterator cursor to previous item if not currently at beginning
     *
     * @return Boolean success of operation
     */
    public boolean iMovePrevious()
    {
        if (utilityType == ITER_TYPE)
        {
            if (iteratorIndex > 0)
            {

                iteratorIndex--;

                return true;
            }
        }
        return false;
    }

    /**
     * Removes and returns value from list at current iterator position
     * <p>
     * Note: If index is greater than zero, sets iterator index to previous item
     * after removal action
     *
     * @return Value returned if successful, FAILED_ACCESS if not
     */
    public int iRemoveAtCurrent()
    {
        int accessedData;

        if (utilityType == ITER_TYPE && !isEmpty())
        {
            accessedData = storage[iteratorIndex];

            if (iteratorIndex > 0)
            {
                iteratorIndex--;
            }

            size--;

            return accessedData;
        }

        return FAILED_ACCESS;
    }

    /**
     * Reports empty for any of the utilities
     *
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {

        return size == 0;
    }

    /**
     * Sets iterator to beginning of list
     *
     * @return Boolean evidence that iterator is at beginning of list
     */
    public boolean iSetToBeginning()
    {
        if (utilityType == ITER_TYPE)
        {
            iteratorIndex = 0;

            return true;
        }

        return false;
    }

    /**
     * Sets iterator to end of list
     *
     * @return Boolean evidence that iterator is at end of list
     */
    public boolean iSetToEnd()
    {
        if (utilityType == ITER_TYPE)
        {

            iteratorIndex = size - 1;

            return true;

        }

        return false;
    }

    /**
     * Provides peek at top of stack if stack mode engaged
     *
     * @return value if successful, FAILED_ACCESS if not
     */
    public int sPeek()
    {
        if (utilityType == STACK_TYPE && size > 0)
        {
            return storage[stackTopIndex];
        }
        return FAILED_ACCESS;
    }

    /**
     * Removes and returns data from top of stack if stack mode engaged
     *
     * @return value if successful, FAILED_ACCESS if not
     */
    public int sPop()
    {
        int accessedData;

        if (utilityType == STACK_TYPE && !isEmpty())
        {
            accessedData = storage[stackTopIndex];
            size--;
            stackTopIndex--;

            return accessedData;

        }

        return FAILED_ACCESS;
    }

    /**
     * Pushes value onto stack if stack mode engaged
     *
     * @param newValue Value to be pushed onto stack
     *
     * @return Boolean evidence of success
     */
    public boolean sPush(int newValue)
    {
        if (utilityType == STACK_TYPE)
        {
            checkForReSize();
            if (isEmpty() && stackTopIndex == 0)
            /*  Handler for adding the first value/base case
                    Can't go through normal setters, as it would mess up the 
                    index(0) vs size(1)
             */
            {
                size = 1;
                storage[stackTopIndex] = newValue;

                return true;
            }
            else
            {
                stackTopIndex++;
                storage[stackTopIndex] = newValue;
                size++;

                return true;
            }
        }

        return false;
    }

    /**
     * Provides peek at front of queue if queue mode engaged
     *
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int qPeek()
    {
        if (utilityType == QUEUE_TYPE && !isEmpty())
        {
            return storage[queueHeadIndex];
        }

        return FAILED_ACCESS;
    }

    /**
     * Removes and returns value from front of queue if queue mode is engaged
     *
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int qDequeue()
    {
        int accessedData;

        if (utilityType == QUEUE_TYPE && !isEmpty())
        {

            accessedData = storage[queueHeadIndex];
            updateQueueHeadIndex();
            size--;

            return accessedData;

        }

        return FAILED_ACCESS;
    }

    /**
     * Appends value to end of queue if queue mode is engaged
     *
     * @param newValue Value to be enqueued
     *
     * @return Boolean success of operation
     */
    public boolean qEnqueue(int newValue)
    {

        if (utilityType == QUEUE_TYPE)
        {
            checkForReSize();
            storage[queueTailIndex] = newValue;
            size++;

            if (queueTailIndex + 1 == capacity && queueHeadIndex == 0)
            {

                checkForReSize();

            }
            else
            {

                updateQueueTailIndex();

            }

            return true;
        }

        return false;
    }

    /**
     * Updates queue head index to wrap around array as needed
     */
    private void updateQueueHeadIndex()
    {

        queueHeadIndex++;

        if (queueHeadIndex == capacity)
        {

            queueHeadIndex = 0;

        }
    }

    /**
     * Updates queue tail index to wrap around array as needed
     */
    public void updateQueueTailIndex()
    {
        if (utilityType == QUEUE_TYPE)
        {
            queueTailIndex++;

            if (queueTailIndex == capacity)
            {

                queueTailIndex = 0;

            }

        }
    }

    /**
     * Checks for resize and resizes to twice the current capacity if needed
     *
     * @return success of operation
     */
    private boolean checkForReSize()
    {
        if (capacity == size)
        {
            capacity = size * 2;

            int[] tempArray = new int[capacity];
            int index = 0;

            if (utilityType == QUEUE_TYPE)
            {
                int headIterator, tailIterator;

                for (headIterator = queueHeadIndex; headIterator < size;
                     headIterator++)
                {

                    tempArray[index] = storage[headIterator];

                    index++;

                }
                for (tailIterator = queueTailIndex;
                     tailIterator < queueHeadIndex; tailIterator++)
                {

                    tempArray[index] = storage[tailIterator];

                    index++;

                }

                queueHeadIndex = 0;
                queueTailIndex = size;
                storage = tempArray;

                return true;
            }
            else
            {
                if (utilityType == STACK_TYPE || utilityType == ITER_TYPE)
                {
                    for (index = 0; index < size; index++)
                    {

                        tempArray[index] = storage[index];

                    }

                    storage = tempArray;

                    return true;
                }
            }
        }

        return false;

    }
}
