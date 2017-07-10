/******************************************************************************
 * Michael Introini
 * mbi2105
 *
 * This program scrapes a given website for all links that it contains
 *
 *****************************************************************************/
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Links {

     static ArrayList<String> getLinks (String location){
        //URL List to hold all links
        ArrayList<String> urlList = new ArrayList<String>();

        try {
            //Get Page
            URL pageLocation = new URL(location);
            Scanner input = new Scanner(pageLocation.openStream());

            //Set RegEx pattern for anything between <a> and </a>, including
            //the tags.
            Pattern p = Pattern.compile("<a\\b[^>]*>(.*?)</a>");

            //scan each line of the HTML
            while (input.hasNext()) {
                //if the line contains a pattern match add segment to the list
                if (input.findInLine(p) != null) {
                    urlList.add(input.match().group());
                }
                input.next();
            }
            try {
                //print out the results
                for (String s : urlList)
                    System.out.println(s);
                }
            finally {
                input.close();
            }

        }
        //Catch bad urls that don't exist or time out
        catch (UnknownHostException e) {
            System.out.println("Unknown Host");
        }
        //Catch any other IO exception
        catch (IOException e) {
            System.out.println(e);
        }

        return urlList;
    }

    public static void main (String[] args) throws IOException{
        //Change this string to search a different website.
        String URL = "http://www.columbia.edu/~mbi2105/";

        //Retrieve links
        getLinks(URL);
    }
}
