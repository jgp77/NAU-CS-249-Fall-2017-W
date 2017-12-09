package p4_Package;


/*
This does not work ):

The findNextOpenLocation or the checkForFitInField
methods are giving an error (i think its those, but i am unsure). I do not
know why, but my code is stacking each box on top of each other, this
prevents it from finding a packing solution. I tried writing it on pen and
paper, but I could not find the flaw in my code.


I think this may be because I used 0,0 as the bottom left corner instead of
using (0,containerBoxHeight). Looking at other student's code, they used the
latter instead.
 */


/**
 * Manages a container box into which smaller boxes are placed using recursive
 * backtracking
 *
 * @author Joshua Pollock
 */
public class ContainerBoxClass
{
    private static final char DEFAULT_FIELD_CHAR = 45;
    private static final int FILL_BOX = 101;
    private static final int CLEAR_BOX = 102;
    private static final int MAX_NUM_BOXES = 26;
    private static final int NO_BOXES_AVAILABLE = -1;

    private char[][] containerBoxField;
    private BoxClass[] boxList;
    private int containerBoxWidth;
    private int containerBoxHeight;
    private int numBoxesAvailable;
    private boolean displayFlag;

    private int startIndex = 0;


    /**
     * Initialization constructor
     *
     * @param initBoxWidth  Initial width of container box
     * @param initBoxHeight Initial height of container box
     */
    public ContainerBoxClass(int initBoxWidth, int initBoxHeight)
    {
        containerBoxHeight = initBoxHeight;
        containerBoxWidth = initBoxWidth;
        containerBoxField = new char[containerBoxWidth][containerBoxHeight];
        numBoxesAvailable = 0;
        boxList = new BoxClass[MAX_NUM_BOXES];


        int xIndex, yIndex;
        for (yIndex = 0; yIndex < containerBoxHeight; yIndex++)
        {
            for (xIndex = 0; xIndex < containerBoxWidth; xIndex++)
            {
                containerBoxField[xIndex][yIndex] = DEFAULT_FIELD_CHAR;
            }
        }

    }

    /**
     * Implements recursive backtracking to pack boxes into container box
     *
     * @return Boolean success or failure
     */
    /*
    find an open location if possible-
        loop while I can find a box-

            check for box fit (does it fit)-

            put the box in container-

            mark the box as used -

           call a new method - recurse-

            check for recursion success-

                return true if successful -

            mark box as not used-

            clear box area in container-

            rotate box-

            check for fix-

            put box in container-

            mark box as used-

            call new method - recurse-

                check for recursion success-

                return true-

            take box out of container

            mark box as unused

            unrotate the box

            Update start index

        end box search loop

    report back if no open locations and no boxes available
    */
    public boolean fillContainerBox()
    {
        int boxLocation = findNextUnusedBoxIndex(startIndex);
        ;
        boolean fitsInField, nextBoxFill;

        PointClass openLocation = findNextOpenLocation();

        if (openLocation != null)
        // Find a open location
        {

            while (boxLocation != NO_BOXES_AVAILABLE)
            // While I can find a box
            {
                fitsInField = checkForFitInField(openLocation,
                        boxList[boxLocation]);
                // Check for box fit
                if (fitsInField)
                // If the box fits
                {
                    fillBoxLocation(openLocation, boxList[boxLocation],
                            FILL_BOX);
                    // Put the box in the container

                    boxList[boxLocation].setUsedState();
                    // mark box as used


                    displayField();

                    // Display the container


                    nextBoxFill = fillContainerBox();
                    // call method, and recurse
                    if (nextBoxFill)
                    // if recurse returns true
                    {
                        return true;
                        // also return true
                    }

                    else
                    // Else if recursive does not work
                    {
                        boxList[boxLocation].unsetUsedState();
                        // set box as unused

                        fillBoxLocation(openLocation, boxList[boxLocation],
                                CLEAR_BOX);
                        // Clear out the container

                        boxList[boxLocation].rotate();
                        // rotate the box

                        fitsInField = checkForFitInField(openLocation,
                                boxList[boxLocation]);
                        // Check for fit

                        if (fitsInField)
                        // if it does fit
                        {
                            fillBoxLocation(openLocation,
                                    boxList[boxLocation], FILL_BOX);
                            // put box in the container

                            displayField();

                            // Display the container

                            boxList[boxLocation].setUsedState();
                            // Set box as used

                            nextBoxFill = fillContainerBox();
                            // call method, and recurse

                            if (nextBoxFill)
                            // if recurse returns successful
                            {
                                return true;
                                // return true
                            }
                            else
                            // if not
                            {
                                boxList[boxLocation].unsetUsedState();
                                // set box as unused

                                fillBoxLocation(openLocation,
                                        boxList[boxLocation], CLEAR_BOX);
                                // take box out of container

                                boxList[boxLocation].rotate();
                                // rotate the box

                                startIndex++;

                                // iterate start index

                                displayField();

                                return false;

                            }
                        }

                    }
                }

            }


        }
        return false;
    }


    /**
     * Adds a new box to the list of boxes to be placed into the container box
     *
     * @param boxWidth  width of box to be added
     * @param boxHeight height of box to be added
     *
     * @return Boolean success, or failure if box array is full
     */
    public boolean addBoxToList(int boxWidth, int boxHeight)
    {
        if (numBoxesAvailable < MAX_NUM_BOXES)
        {
            BoxClass newBox = new BoxClass(boxWidth, boxHeight);
            boxList[numBoxesAvailable] = newBox;
            numBoxesAvailable++;
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Sets display flag to display container box during operations
     *
     * @param setState Boolean state to set or unset display flag
     */
    public void setDisplayFlag(boolean setState)
    {
        displayFlag = setState;
    }

    /**
     * Displays entire container box with any boxes currently within container
     */

    /*
    Needs to print out the container field. And example output is
        ===================
        ||---------------||
        ||---------------||
        ||---------------||
        ||---------------||
        ||---------------||
        ||---------------||
        ||AAAAAAA--------||
        ||AAAAAAA--------||
        ||AAAAAAA--------||
        ||AAAAAAA--------||
        ||AAAAAAA--------||
        ||AAAAAAA--------||
        ===================

        It should start in the upper left (0,containerBoxHeight) and print
        its way down. The for loop should be backwards due to this

    */
    public void displayField()
    {
        if (displayFlag)
        {
            System.out.print("===================");
            System.out.print("\n");
            int xIndex, yIndex;
            for (yIndex = containerBoxHeight - 1; yIndex > 0; yIndex--)
            {
                System.out.print("||");
                for (xIndex = 0; xIndex < containerBoxWidth; xIndex++)
                {
                    System.out.print(containerBoxField[xIndex][yIndex]);
                }
                System.out.print("||\n");
            }
            System.out.println("===================");
        }
    }

    /**
     * Finds next open location
     * <p>
     * Note: Starts search from left lower corner, moves right, then up
     *
     * @return X, Y coordinate of open location as PointClass object, null if no
     * open location found
     */

    /*
    This should search through the current container field and try to find
    the character '-'. Once found it should return the current values. With
    this class the for loops should be nested, and start iterating from
    (0,0)/the bottom left corner

     */
    private PointClass findNextOpenLocation()
    {
        int xIndex, yIndex;
        PointClass emptyPosition;

        for (yIndex = 0; yIndex < containerBoxHeight; yIndex++)
        {
            for (xIndex = 0; xIndex < containerBoxWidth; xIndex++)
            {
                if (containerBoxField[xIndex][yIndex] == DEFAULT_FIELD_CHAR)
                {
                    emptyPosition = new PointClass(xIndex, yIndex);
                    return emptyPosition;
                }
            }
        }
        return null;
    }

    /**
     * Searches for unused box from the given index of the list of boxes to be
     * packed
     *
     * @param startAtIndex Index used to begin search of box list
     *
     * @return integer index if a box is found, constant NO_BOXES_AVAILABLE if
     * no boxes available
     */
    private int findNextUnusedBoxIndex(int startAtIndex)
    {
        int boxListIndex;

        for (boxListIndex = startAtIndex; boxListIndex < numBoxesAvailable;
             boxListIndex++)
        {
            if (!boxList[boxListIndex].isUsed())
            {
                return boxListIndex;
            }
        }
        return NO_BOXES_AVAILABLE;

    }

    /**
     * Checks to see if a box can be placed within the container starting at the
     * test location provided
     * <p>
     * Note: Tests from lower left corner across then up to check for available
     * area
     *
     * @param testLocation lower left corner location to start testing for
     *                     available space within the container box
     * @param testBox      box to be checked for fit
     *
     * @return Boolean result of test
     */
    private boolean checkForFitInField(PointClass testLocation, BoxClass
            testBox)
    {
        int xPos, yPos,
                boxWidth = testBox.getWidth(), boxHeight = testBox.getHeight();


        for (yPos = testLocation.getYPos(); yPos < containerBoxHeight; yPos++)
        {
            for (xPos = testLocation.getXPos(); xPos < containerBoxWidth;
                 xPos++)
            {
                if (containerBoxField[xPos][yPos] == DEFAULT_FIELD_CHAR)
                {
                    if (containerBoxWidth - xPos > boxWidth
                            || containerBoxHeight - yPos > boxHeight)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }

                }
            }
        }
        return false;

    }

    /**
     * Fills container box at given location with box letter; if clear flag is
     * set, sets container box with default character
     * <p>
     * Note: Paints box from lower left corner across then up
     *
     * @param boxLocation PointClass object location of fill starting point
     * @param fillBox     BoxClass object containing width, height, and ID
     *                    letter to be used for filling container box
     * @param clearFlag   provides input on whether to use the box letter
     *                    (FILL_BOX) Or to use the default character to clear
     *                    box area (CLEAR_BOX)
     */
    private void fillBoxLocation(PointClass boxLocation, BoxClass fillBox,
                                 int clearFlag)
    {

        int xPos, yPos,
                boxWidth = fillBox.getWidth(), boxHeight = fillBox.getHeight();

        if (clearFlag == FILL_BOX)
        {
            for (yPos = boxLocation.getYPos(); yPos <= boxHeight; yPos++)
            {
                for (xPos = boxLocation.getXPos(); xPos < boxWidth;
                     xPos++)
                {
                    containerBoxField[xPos][yPos] = fillBox.getID();
                }
            }

        }
        else if (clearFlag == CLEAR_BOX)
        {
            for (yPos = boxLocation.getYPos(); yPos <= boxHeight; yPos++)
            {
                for (xPos = boxLocation.getXPos(); xPos < boxWidth;
                     xPos++)
                {
                    containerBoxField[xPos][yPos] = DEFAULT_FIELD_CHAR;
                }
            }
        }
    }
}


