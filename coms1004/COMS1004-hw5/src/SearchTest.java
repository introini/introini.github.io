/******************************************************************************
 * Michael Introini
 * mbi2105
 *
 * This program searches through a text file for a given query and returns the
 * amount of times it found a match
 *
 *****************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchTest {
    static void search (String query, File file) throws FileNotFoundException {

        // Scan the file provided to this method
        Scanner input = new Scanner(file);
        String line;
        // Variable to enumerate each line of the file
        int lineNumber = 1;
        // Varbiable to keep count of each find
        int count = 0;

        //loop through each line in the file
        while (input.hasNextLine()) {
            line = input.nextLine();
            //convert all strings to lowercase and check for queried word
            if (line.toLowerCase().contains(query.toLowerCase())) {
                count++;
                System.out.println(lineNumber + ": " + line);
            }
            lineNumber++;
        }

        input.close();
        //Print out total matches
        System.out.println("Total matches: " + count);

    }
    public static void main(String[] args)   {
        boolean again = true;
        while (again) {
            try {
                //Asign first argument to be the file we read from
                File inFile = new File(args[0]);
                String searchString = args[1];

                //Start the search
                search(searchString, inFile);
                again = false;
            }
            //Catch file not found and have the user retry
            catch(FileNotFoundException e){
                System.out.println("Please try again with correct input " +
                        "file name");
                System.out.println("Make sure to use the following " +
                        "format: \n");
                System.out.println("SearchTest FILENAME QUERY");
                Scanner scan = new Scanner(System.in);
                args[0]=scan.next();
            }
            //Catch missing query
            catch(Exception e){
                System.out.println("Your missing a search argument");
                again = false;
            }
        }
    }

}