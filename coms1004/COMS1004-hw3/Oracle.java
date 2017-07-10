//***************************************
// Michael Introini
// mbi2105
//
// This is a template for your Oracle
// class
//
//***********************************

public class Oracle{

    private String pattern, digit, whichBull, whichCow;
    private int bullCount, cowCount;

    public Oracle(){
    // your code here for setting up
    // an Oracle object
    	digit = "";
    	bullCount = 0;
    	cowCount = 0;
        whichBull = "";
        whichCow = "";
    }

    public void setPattern(String solution){
    // This method is complete. Don't touch it.
    // This method is here to allow us and you
    // to test your code. You should only use
    // this method to help you test your code
    // while you are developing your program.

        pattern = solution;
    }

    public String getPattern(){
    // This method is complete. Don't touch it.
    // This method is here to allow us and you
    // to test your code. You should only use
    // this method to help you test your code
    // while you are developing your program.

        return pattern;
    }

    public int howManyBulls(String guess) {
        // your code here for
        // determining the number
        // of bulls the String
        // guess should earn

        // Count the total amount of Bulls
        bullCount = 0;
        // Keep track of which digit is a bull
        whichBull = "";

        // Loop through each digit and check against the computer pattern
        for (int i = 0; i < pattern.length(); i++) {
            if (guess.charAt(i) == pattern.charAt(i)) {
                whichBull += guess.charAt(i);
                bullCount++;
            }
        }
		return bullCount;
    }

    public int howManyCows(String guess){
        // your code here for
        // determine the number
        // of cows the String
        // guess should earn

        // Count the total amount of cows
        cowCount = 0;
        // Keep track of which digit is a cow
        whichCow = "";
        // Use the accessor method to see which bulls we already have.
        whichBull = getWhichBull();
        for (int i = 0; i < pattern.length(); i++) {
            digit = Character.toString(guess.charAt(i));
            // Is the guess partially correct?
            if (pattern.contains(digit)) {
                // If it's not a Bull and it it isn't already accounted for in
                // whichBull
                if (guess.charAt(i) != pattern.charAt(i)
                        && !whichBull.contains(digit)) {
                    // Check that it's not already counted as a Cow and add it.
                    if(!whichCow.contains(digit)){
                        whichCow += guess.charAt(i);
                        cowCount++;
                    }
                } else {
                    // Add to which
                    whichBull +=guess.charAt(i);
                }
            }

        }
        return cowCount;
    }

    // any other methods you might want
    // in this class can go here



    // Accessor method for returning which bulls have been counted.
    public String getWhichBull(){
        return whichBull;
    }

    // Check if there are any repeating numbers
    public boolean hasRepeating(String guess) {
        boolean match = false;
        char firstDigit = guess.charAt(0);
        char secondDigit = guess.charAt(1);
        char thirdDigit = guess.charAt(2);
        char fourthDigit = guess.charAt(3);

        // Compare the 1st digit to 2nd, 3rd, and 4th. Then 2nd to 4th, and
        // lastly check 3rd to 4th
        if (firstDigit == secondDigit || firstDigit == thirdDigit
                || firstDigit == fourthDigit ) {
            match = true;
        }  if ( secondDigit == thirdDigit || secondDigit == fourthDigit) {
            match = true;
        }  if ( thirdDigit == fourthDigit) {
            match = true;
        }

        //return if true or false
        return match;
    }

    // Method to create a secret number for the computer.
    public String secretNumber(){
        // choose a random 4 digit number
        int number = (int) (Math.random() * 9000) +1000;
        String numStr = Integer.toString(number);

        // Choose a new number if it has repeats
        while (hasRepeating(numStr)) {
            number = (int) (Math.random() * 9000) +1000;
            numStr = Integer.toString(number);
        }

        return numStr;
    }

}
