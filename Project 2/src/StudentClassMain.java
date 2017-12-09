import java.io.*;

/** Provides workbench for testing StudentClass and GenericArrayClass
 * 
 * @author MichaelL
 *
 */
public class StudentClassMain
   {
    private final static int END_OF_FILE_MARKER = -1;

    // used for acquiring data via several methods
    private static FileReader fileIn;
    
      /** main method for driving multiple tests on Generic Array Class
       * and StudentClass
       * 
       * @param args command-line string input arguments
       */
      public static void main(String[] args)
         {
          GenericArrayClass<StudentClass> gSC;
          StudentClass studentData;
          String[] testData = new String[ 10 ];
          String tempStr;
          int arrayCapacity = 60;
          int arraySize, index;
          String fileName = "inData.txt";
          
          // title
          System.out.println( "\nGenericArrayClass Data Testing Program\n");
          
          // set up test data
          testData[ 0 ] = "Casolari, Lyle/554535/M/3.260673916";
          testData[ 1 ] = "Serate, Alexis/183761/F/2.417047022";
          testData[ 2 ] = "Goldberg, Zachary/557631/M/3.781838392";
          testData[ 3 ] = "Niday, Breanna/898497/F/3.964983416";
          testData[ 4 ] = "Andrieu, Andrew/295673/M/3.800520395";
          testData[ 5 ] = "Ahmed, Alexandra/214029/F/2.503090728";
          testData[ 6 ] = "Hunt, Stephanie/597706/F/3.7864219";
          testData[ 7 ] = "Volkmar, Mason/641651/M/3.325557771";
          testData[ 8 ] = "Goffinet, Jahshua/722457/M/2.258524754";
          testData[ 9 ] = "Buck, Phoebe/729716/F/3.701569389";
          
          // access data from file
             // test initialization constructor, appendItem, resize 
          System.out.println( "Data Retrieval from file - Begin");         
          gSC = getData( fileName, arrayCapacity );
          System.out.println( "Data Retrieval from file - End");          
          
          if( gSC != null )
             {
             // remove all but 10 items
             while( gSC.getCurrentSize() > 10 )  
                {
                 studentData = gSC.removeItemAt( 0 );
                 
                 // Test for data accessed and removed
                 //   System.out.println( "Student data: " 
                 //                    + studentData.toString() + " removed.");                           
                }
             
             StudentClass.setSortDirKey( StudentClass.SORT_FORWARD );
             StudentClass.setSortKey( StudentClass.SORT_BY_NAME );
          
             try
                {
                 // Test accessItemAt
                 System.out.println( "\nTesting accessItem:");
             
                 System.out.println( "\tStudent data: " 
                            + gSC.accessItemAt( 2 ).toString() + " tested.");
                 tempStr = gSC.accessItemAt( 2 ).toString(); 
                 assert tempStr.equals( testData[ 2 ] ) == true;
             
                 System.out.println( "\tStudent data: " 
                            + gSC.accessItemAt( 4 ).toString() + " tested.");
                 tempStr = gSC.accessItemAt( 4 ).toString(); 
                 assert tempStr.equals( testData[ 4 ] ) == true;
             
                 System.out.println( "\tStudent data: " 
                            + gSC.accessItemAt( 6 ).toString() + " tested.");
                 tempStr = gSC.accessItemAt( 6 ).toString(); 
                 assert tempStr.equals( testData[ 6 ] ) == true;
             
                 System.out.println( "\tStudent data: " 
                            + gSC.accessItemAt( 8 ).toString() + " tested.");
                 tempStr = gSC.accessItemAt( 8 ).toString(); 
                 assert tempStr.equals( testData[ 8 ] ) == true;
                }
             
             catch( AssertionError ae )
                {
                 System.out.print( "\t#################### " );
                 System.out.print( "ACCESS ITEM ERROR: " );
                 System.out.println( ae + " ####################" );                
                }
             
             try
                {
                 // Test copy constructor
                 System.out.println( "\nTesting copy constructor:");
             
                 GenericArrayClass<StudentClass> gSC_Copy 
                                = new GenericArrayClass<StudentClass>( gSC ); 
   
                 System.out.println( "\tStudent data: " 
                       + gSC_Copy.accessItemAt( 1 ).toString() + " tested.");
                 tempStr = gSC_Copy.accessItemAt( 1 ).toString(); 
                 assert tempStr.equals( testData[ 1 ] ) == true;
             
                 System.out.println( "\tStudent data: " 
                       + gSC_Copy.accessItemAt( 3 ).toString() + " tested.");
                 tempStr = gSC_Copy.accessItemAt( 3 ).toString(); 
                 assert tempStr.equals( testData[ 3 ] ) == true;
             
                 System.out.println( "\tStudent data: " 
                       + gSC_Copy.accessItemAt( 5 ).toString() + " tested.");
                 tempStr = gSC_Copy.accessItemAt( 5 ).toString(); 
                 assert tempStr.equals( testData[ 5 ] ) == true;
               
                 System.out.println( "\tStudent data: " 
                       + gSC_Copy.accessItemAt( 7 ).toString() + " tested.");
                 tempStr = gSC_Copy.accessItemAt( 7 ).toString(); 
                 assert tempStr.equals( testData[ 7 ] ) == true;
                }
             
             catch( AssertionError ae )
                {
                 System.out.print( "\t#################### " );
                 System.out.print( "COPY CONSTRUCTOR ERROR: " );
                 System.out.println( ae + " ####################" );                
                }
             
             try
                {
                 // Test remove, insert, getters
                 System.out.print( "\nTesting removeItemAt" );
                 System.out.println( " getCurrentSize, insertItemAt:");          
             
                 GenericArrayClass<StudentClass> gSC_Copy 
                                = new GenericArrayClass<StudentClass>( gSC ); 
   
                 System.out.println( "\tStudent data: " 
                               + gSC_Copy.accessItemAt( 5 ).toString() 
                                                 + " removed from index 5.");         
                 studentData = gSC_Copy.removeItemAt( 5 );
                 tempStr = gSC_Copy.accessItemAt( 5 ).toString(); 
                 assert tempStr.equals( testData[ 5 ] ) == false;
             
                 System.out.println( "\tSize should now be 9 ");
                 arraySize = gSC_Copy.getCurrentSize();
                 assert arraySize == 9;
             
                 System.out.println( "\tStudent data: " 
                         + studentData.toString() + " inserted at index 1.");
                 gSC_Copy.insertItemAt( 1,  studentData );
                 tempStr = gSC_Copy.accessItemAt( 1 ).toString(); 
                 assert tempStr.equals( testData[ 5 ] ) == true;
             
                 System.out.println( "\tSize should now be 10 ");
                 arraySize = gSC_Copy.getCurrentSize();
                 assert arraySize == 10;
             
                 System.out.println( "\tCapacity should now be 60 ");
                 arrayCapacity = gSC_Copy.getCurrentCapacity();
                 assert arrayCapacity == 60;
   
                 System.out.println( "\tisFull should be false");
                 assert gSC_Copy.isFull() == false;
   
                 System.out.println( "\tisEmpty should be false");
                 assert gSC_Copy.isEmpty() == false;
                }
             
             catch( AssertionError ae )
                {
                 System.out.print( "\t#################### " );
                 System.out.print( "REMOVE / INSERT ERROR: " );
                 System.out.println( ae + " ####################" );                
                }
             
             try
                {
                 // Test clear
                 System.out.println( "\nTesting clear:");
   
                 GenericArrayClass<StudentClass> gSC_Copy 
                                = new GenericArrayClass<StudentClass>( gSC ); 
   
                 gSC_Copy.clear();
   
                 System.out.println( "\tisFull should be false");
                 assert gSC_Copy.isFull() == false;
   
                 System.out.println( "\tisEmpty should be true");
                 assert gSC_Copy.isEmpty() == true;
   
                 System.out.println( "\tStudent data: " + testData[ 9 ] 
                                + " appended to cleared array, and tested.");
                 // testData[ 9 ] = "Buck, Phoebe/729716/M/3.701569389";
                 studentData = new StudentClass( "Buck, Phoebe", 729716, 
                                                          'F', 3.701569389 );
                 gSC_Copy.appendItem( studentData );
             
                 System.out.println( "\tStudent data: " + testData[ 8 ] 
                     + " inserted at index 0 in cleared array, and tested.");
                 // testData[ 8 ] = "Goffinet, Jahshua/722457/M/2.258524754";
                 studentData = new StudentClass( "Goffinet, Jahshua", 722457, 
                                                          'M', 2.258524754 );         
                 gSC_Copy.insertItemAt( 0,  studentData );
                 tempStr = gSC_Copy.accessItemAt( 1 ).toString(); 
                 assert tempStr.equals( testData[ 9 ] ) == true;
                 
                 tempStr = gSC_Copy.accessItemAt( 0 ).toString();
                 assert tempStr.equals( testData[ 8 ] ) == true;
                }   
             
             catch( AssertionError ae )
                {
                 System.out.print( "\t#################### " );
                 System.out.print( "CLEAR / RESTART ERROR: " +  ae );
                 System.out.println( " ####################" );                
                }          
             }
          
          else
             {
              System.out.println( 
                  "\nERROR: First Data access failure - Program aborted." );            
             }
        
          // reload data from file
          System.out.println( "\nData Retrieval from file - Begin");         
          gSC = getData( fileName, arrayCapacity );
          System.out.println( "Data Retrieval from file - End");          
       
          if( gSC != null )
             {
              gSC.runBubbleSort();  // default sort, by name, forward

              System.out.println( "\nAfter Bubble Sort (Name, Forward): ");
              
              index = 0;
              
              while( index < gSC.getCurrentSize() )
                 {
                  System.out.println( "Data: " + gSC.accessItemAt( index ) );
                  
                  index++;
                 }                          

              StudentClass.setSortKey( StudentClass.SORT_BY_GPA);
              StudentClass.setSortDirKey( StudentClass.SORT_BACKWARD );
       
              gSC.runInsertionSort();

              System.out.println( "\nAfter Insertion Sort (GPA, Backward): ");
              
              index = 0;
              
              while( index < gSC.getCurrentSize() )
                 {
                  System.out.println( "Data: " + gSC.accessItemAt( index ) );
                  
                  index++;
                 }                          
              
              StudentClass.setSortKey( StudentClass.SORT_BY_ID);
              StudentClass.setSortDirKey( StudentClass.SORT_FORWARD );
       
              gSC.runSelectionSort();

              System.out.println( "\nAfter Selection Sort (ID, Forward): ");
              
              index = 0;
              
              while( index < gSC.getCurrentSize() )
                 {
                  System.out.println( "Data: " + gSC.accessItemAt( index ) );
                  
                  index++;
                 }                          

             }
          
          else
             {
              System.out.println( 
                    "\nERROR: Second Data Access Failure - Program Aborted");
             }

          System.out.println( "\n --- End of Program--- " );         
         }

      /**
       * Local method uploads data character by character,
       * parses characters, and loads into StudentClass
       * type data
       * <p>
       * Exception: If there is a file failure such as file not found,
       * method will return null
       * <p>
       * Exception: If the capacity parameter is set to low for the data,
       * method will also return null
       * 
       * @param fileName name of file in local directory required for upload
       * 
       * @param setCapacity directs method to set capacity
       * of generic array
       * 
       * @return returns generic array holding StudentClass data
       */
      public static GenericArrayClass<StudentClass> 
                                  getData( String fileName, int setCapacity )
         {
          int value, initialCapacity = 10;

          StudentClass studentClassObject;
          GenericArrayClass<StudentClass> arrayClassObject 
                    = new GenericArrayClass<StudentClass>( initialCapacity );
          String nameStr, idStr, gpaStr;
          int idVal;
          char genderVal;
          double gpaVal;
          boolean failedAccess = false;

          //FileReader 
          fileIn = null;
          
          try
             {
              fileIn = new FileReader( fileName );
              
              // read prime, name
              value = fileIn.read();

              while( value != END_OF_FILE_MARKER && !failedAccess )
                 {
                  // reset input strings
                  nameStr = ""; idStr = ""; gpaStr = "";

                    // get name
                  while( value != ';')
                     {
                      nameStr += (char)value;
                      
                      value = fileIn.read();
                     }
                  
                  // skip spaces up to integer value characters
                  value = getNextCharInt( "0123456789" );
                  
                  // get id
                  while( value >= '0' && value <= '9' )
                     {
                      idStr += (char)value;
                      
                      value = fileIn.read();
                     }
                  
                  // translate id
                  idVal = Integer.parseInt( idStr );

                  // skip spaces up to Mm or Ff
                  value = getNextCharInt( "MFmf" );

                  // load gender
                  genderVal = (char)value;
                  
                  // skip empty spaces up to double value characters
                  value = getNextCharInt( "0123456789." );

                  // get gpa
                  while( ( value >= '0' && value <= '9' ) || value == '.' )
                     {
                      gpaStr += (char)value;
                      
                      value = fileIn.read();
                     }
                  
                  // translate gpa
                  gpaVal = Double.parseDouble( gpaStr );                  

                  // load data into StudentClass object
                  studentClassObject = new StudentClass( nameStr, idVal, 
                                                         genderVal, gpaVal );
                  
                  // test resize operation
                  if( arrayClassObject.isFull() )
                     {
                      arrayClassObject.resize( setCapacity );   
                     }
                  
                  // load StudentClass object into array
                  failedAccess 
                        = !arrayClassObject.appendItem( studentClassObject );
                  
                  // skip end of line, etc., up to next letter 
                  // (name on next line)             
                  value = getNextCharInt( 
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" );
                  
                 }  // end data collection loop
              
              if( fileIn != null )
                 {
                  fileIn.close();
                 }
             }
          
          catch( IOException ioe )
             {
              failedAccess = true;
             }
         
          if( failedAccess )
             {
              arrayClassObject = null;
             }
          
          return arrayClassObject;
         }

      /** Local method for getting next desired character 
       * from the file stream
       * 
       * @param rangeString set of desired characters
       *  
       * @return integer character for use in input process
       */
      private static int getNextCharInt( String rangeString )
         { 
          int nextCharInt = 0;
          
          try
             {
              nextCharInt = fileIn.read();
              
              while( nextCharInt != END_OF_FILE_MARKER 
                           && !isInString( (char)nextCharInt, rangeString ) )
                 {
                  nextCharInt = fileIn.read();
                 }
             }
          
          catch( IOException ioe )
             {
              System.out.println( "INPUT ERROR: Failure to capture character" );
             }
          
         // if( )
          
          return nextCharInt;
         }
      
      /** Local method that searches for character in a given string
       * 
       * @param testChar search character
       * 
       * @param testString string containing list of acceptable characters
       * 
       * @return true if character found in string; false otherwise
       */
      private static boolean isInString( char testChar, String testString )
      {
       int index;
         
       for( index = 0; index < testString.length(); index++ )
          {
           if( testString.charAt( index ) == testChar )
              {
               return true;
              }
          }
       
       return false;
      }
   }

