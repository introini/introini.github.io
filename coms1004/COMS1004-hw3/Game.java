//***************************************
// Michael Introini
// mbi2105
//
// This is the Game class for the Bull and Cows
// program. You will need to modify this template
//
//**********************************************

import java.util.Scanner;
public class Game {

    private int turns, bulls;
    private Oracle computer;
    private Scanner input;

    // you may need more instance variables here


    public Game(){
    // your code for the Game constructor goes here
        // Keep track of turns
        turns = 0;
        // Input object
    	input = new Scanner(System.in);
    	// Computer Object
        computer = new Oracle();

    }


    public void playGame(){
    // your code for the Game playGame method goes here
      	System.out.println("Welcome to Bulls and Cows!");
      	System.out.println("Dare to guess the computer's secret number?");

        // Generate secret number
        String secret = computer.secretNumber();

        // Set secret number as computer pattern
        computer.setPattern(secret);

        // Start game loop, play one turn at a time until the player gets 4
        // bulls.
        while (bulls < 4) {
      	  playOneTurn();
          if (bulls == 4) {
              if (turns > 1) {
                  System.out.println("You Won! And it only took you " + turns
                          + " turns to do it!");
              } else {
                  System.out.println("You won on your first try!");
              }
          } else {
              System.out.println("Looks like that wasn't it, try again.");
          }
        }
    }

    public void playOneTurn(){
    // your code for the Game playOneTurn method goes here
        String playerGuess;
        int cows;

        // Get input
        playerGuess = input.next();
        // Some sanitation to make sure we only get 4 numbers
        while (playerGuess.length() != 4) {
            System.out.println("You can only use 4 numbers, no more, no " +
                    "less. Try Again!    ");
            playerGuess = input.next();
        }

        // Return the current score
        bulls = computer.howManyBulls(playerGuess);
        cows = computer.howManyCows(playerGuess);

        // Display Score
        System.out.println("Bulls: " + bulls);
        System.out.println("Cows: " + cows);

        //Count the turn
    	turns++;
    }

    public void setPattern(String solution){
    // This method is complete. Don't touch it.
    // it is here to allow us and you to test your code.
    // You should only use this method to help you test
    // your code while developing.

        computer.setPattern(solution);
    }

    public String getPattern(){
    // This method is complete. Don't touch it.
    // This method is here to allow us and you
    // to test your code. You should only use
    // this method to help you test your code
    // while you are developing your program.

        return computer.getPattern();
    }

}
