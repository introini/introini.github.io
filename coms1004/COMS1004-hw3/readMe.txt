/******************************************************************************\
*                             Michael Introini                                 *
*                                  mbi2105                                     *
*                                                                              *
*                                 COMS 1004                                    *
\******************************************************************************/

Files Submitted: 3

1 - BullsAndCows.java
2 - Game.java
3 - Oracle.java

1 - RUN THIS FILE - This file is the test class provided to run the Bulls and
Cows game. It simply starts the game.

2 - This file contains all of the necessary information to start the game and
play until the human player wins. There are two methods in this class that
drive the game. The playGame method sets the computers secret number and starts
a loop that checks to see how many bulls the human player recieved until he/she
reaches 4 bulls and wins. While the player has less than 4 bulls, this method
calls the playOneTurn method. The playOneTurn method asks the player to input 4
numbers and captures that data. The method also ensures that the player inputs
no more or no less than 4 numbers.

3 - This file contains the most important part of the game. There are two
crucial methods in this class that track how many bulls were earned and how
many cows were earned. These methods implement a series of if statements and
loops to compare each individual number. Additionally, the howManyCows method
ensures that repeated numbers only count 1 time for Cows, while the
howManyBulls method ensures that Bulls are counted once as well (if in the
correct place). Lastly,There is an additional method that ensures that the
computer generates a random 4-digit number that does not have any repeats.