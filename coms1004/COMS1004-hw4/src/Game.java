/******************************************************************************
 * Michael Introini
 * mbi2105
 *
 * This class contains all instructions necessary to play the poker game
 *
 ******************************************************************************/

import java.util.*;

public class Game {
    private Player p;
    private Deck cards;
    private boolean match = false; // Match variable to validate checks
    private Scanner input;
    // you'll probably need some more here

    public Game(String[] testHand) {
        // This constructor is to help test your code
        // use the contents of testHand to
        // make a hand for the player
        // use the following encoding for cards
        // c = clubs
        // d = diamonds
        // h = hearts
        // s = spades
        // 1-13 correspond to ace - king
        // example: s1 = ace of spades
        // example: testhand = {s1, s13, s12, s11, s10} = royal flush

        p = new Player();
        cards = new Deck();
        input = new Scanner(System.in);

        int suit;
        int value;

        // Take the test values from the cmd line and parse the information
        // return the suit and value create a card, and add it to the player's.
        // hand,
        for (String tc : testHand) {
            suit = whatSuit(tc.charAt(0));
            if (tc.length() > 2) {
                value = Integer.valueOf(tc.substring(1, 3));
            } else {
                value = Integer.valueOf(tc.substring(1, 2));
            }
            p.addCard(new Card(suit, value));
        }
    }

    public Game() {
        // This constructor is to actually play a normal game
        p = new Player();
        cards = new Deck();
        input = new Scanner(System.in);
    }

    public void play() {
        int count = 0; // counter for inputs

        System.out.println("Here are your first set of cards.");
        System.out.println("Which of these would you like to discard?");
        System.out.println("Enter the number of the card that you want to" +
                " discard. Enter 'D' when you are done\n");

        // Generate the PLayer's first hand
        while (p.getHand().size() < 5) {
            p.addCard(cards.deal());
        }

        // Sort the HAND
        p.sortHand();

        // Print out the hand for the player to see
        for (int i = 0; i < p.getHand().size(); i++) {
            System.out.println((i) + ": " +p.getHand().get(i));
        }

        System.out.println("Card number to discard: ");

        // Remove the card/s specified from the player's hand and replace them
        while (input.hasNextInt() && count < 5) {
            int num = input.nextInt();
            // Create a card variable for the card to be removed
            Card cardToRemove = p.getHand().get(num);
            // Remove the card from the player's hand
            p.removeCard(cardToRemove);
            // Replace the card with a new one from the top of the deck
            p.addCard(cards.deal());
            count++;
        }
        p.sortHand();

        System.out.println("");
        System.out.println(checkHand(p.getHand())); //  Check the hand
    }

    public String toString() {

        String report;

        report = p.toString();

        return report;
    }

    public String checkHand(ArrayList<Card> hand) {
        // this method should take an ArrayList of cards
        // as input and then determine what evaluates to and
        // return that as a String
        String report;

        report = "Your hand is: \n";

        for (Card c: hand)
            report += c + "\n";
        report += "\n";
        report += "and you have: \n";

        // Check each possible hand from highest ranking to lowest and display
        // the output.
        if (straightFlush(hand)) {
            report += "Straight Flush";
        } else if (isFourOfAKind(hand)) {
            report += "Four of a Kind";
        }  else if (isFullHouse(hand)) {
            report += "Full House";
        } else if (isFlush(hand)) {
            report += "Flush";
        } else if (isStraight(hand)) {
            report += "Straight";
        } else if (isThreeOfAKind(hand)) {
            report += "Three of a Kind";
        } else if (isTwoPair(hand)) {
            report += "Two pair";
        } else if (isPair(hand)) {
            report += "A pair";
        } else {
            report += highCard(hand) + " high.";
        }

        return report;
    }

    private boolean straightFlush(ArrayList<Card> hand) {
        return (isStraight(hand) && isFlush(hand));
    }

    private boolean isFourOfAKind(ArrayList<Card> hand) {
        // Check for 4 of a kind
        int vals[] = new int[5];
        int matches = 0;
        int pos = 1;

        // Gather all card values and add the to vals array for proccessing
        for (int i = 0; i < hand.size(); i++) {
            vals[i] = hand.get(i).getValue();
        }
        // Loop through the array comparing all neighboring cards and increment
        // the counter when they match
        for (int i = 0; i < vals.length -1; i++) {
            int nextVal = vals[pos];
            if (vals[i] == nextVal) {
                matches++;
            }
            pos++;
        }
        // To avoid confusion with the full house mechanism, first check that
        // the hand is not a full house, then proceed.
        if (!isFullHouse(hand)) {
            if (matches == 3) {
                match = true;
            }
        } else {
            match = false;
        }

        return match;
    }

    private boolean isFullHouse(ArrayList<Card> hand) {
        // Full House
        int vals[] = new int[5];
        // Get all Values
        for (int i = 0; i < hand.size(); i++) {
            vals[i] = hand.get(i).getValue();
        }
        // Check first three cards from three of a kind
        if (vals[2] == vals[1] && vals[1] == vals[0]) {
            // if yes, check last two for pair
            if (vals[3] == vals[4]) {
                match = true;
            }
        // Check last three for three of a kind
        } else if (vals[2] == vals[3] && vals[3] == vals[4]) {
            // if yes, check the first two for a pair
            if (vals[0] == vals[1]){
                match = true;;
            }
        } else {
            match = false;
        }


        return match;
    }

    private boolean isFlush(ArrayList<Card> hand) {
        int count = 0;
        // Compare every card to see if they have matching suits.
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).isSameSuit(hand.get(j))){
                    count++;
                }
            }
        }
        if (count == 10) {
            match = true;
        }
        return match;
    }

    private boolean isStraight(ArrayList<Card> hand) {
        int pos = 1;
        // Check each card against the next to ensure that the difference
        // between them is -1
        for (int i = pos - 1; i < hand.size() -1; i++) {
            Card nextCard = hand.get(pos);
            if (hand.get(i).difference(nextCard) == -1) {
                match = true;
            } else {
                match = false;
                break;
            }
            pos++;
        }

        return match;
    }

    private boolean isThreeOfAKind(ArrayList<Card> hand) {
        int vals[] = new int[5];
        // Get values for all cards
        for (int i = 0; i < hand.size(); i++) {
            vals[i] = hand.get(i).getValue();
        }
        // Check each section for sequences of three
        if (vals[2] == vals[1] && vals[1] == vals[0]) { // Check First Three
                match = true;
        } else if (vals[2] == vals[1] && vals[2] == vals[3]) { // Middle Three
            match = true;
        } else if (vals[2] == vals[3] && vals[3] == vals[4]) { // Last Three
                match = true;;
        } else {
            match = false;
        }

        return match;
    }

    private boolean isTwoPair(ArrayList<Card> hand) {
        Card currentCard = hand.get(0); // Current Card variable
        ArrayList<Card> pair = new ArrayList<Card>(); // Par List

        // Check each card against the next for a match and add it to the list
        for (int i = 1; i < hand.size(); i++) {
            Card previousCardCard = currentCard;
            currentCard = hand.get(i);
            if (currentCard.compareTo(previousCardCard) == 0 ) {
                pair.add(currentCard);
            }
        }

        if (pair.size() == 2) {
            match = true;
        }

        return match;
    }

    private boolean isPair(ArrayList<Card> hand) {
        int count = 0; // Counter
        Card currentCard = hand.get(0); // First card

        //Compare each card to the previous card and count the matches
        for (int i = 1; i < hand.size(); i++) {
            Card previousCardCard = currentCard;
            currentCard = hand.get(i);
            //check that they are equal and of different suits
            if (currentCard.compareTo(previousCardCard) == 0 &&
                    !(currentCard.isSameSuit(previousCardCard)) && count < 1) {
                count++;
            }
        }

        // If we have 1 matches of cards that are different suits, then we have
        // a pair
        if (count == 1)
            match = true;

        return match;
    }

    private Card highCard(ArrayList<Card> hand) {
        // Check for highest card
        Card highCard;
        Card ace = new Card(1,1);
        highCard = hand.get(0);
        // Check the first card to see if it's an Ace, if so set that as the
        // high card.
        if (hand.get(0).difference(ace) == 0) {
            highCard = hand.get(0);
        } else {
            for (int i = 1; i < hand.size(); i++) {
                if (highCard.compareTo(hand.get(i)) < 0) {
                    highCard = hand.get(i);
                }
            }
        }
        return highCard ;
    }

    private int whatSuit(char s){
        // Used to check the suit in the overloaded Game class

        int whatSuit;

        switch (s) {
            case 'c' : whatSuit = 1; break;
            case 'd' : whatSuit = 2; break;
            case 'h' : whatSuit = 3; break;
            case 's' : whatSuit = 4; break;
            default: whatSuit = -1; break;
        }

        return whatSuit;
    }

    // you will likely want many more methods here
    // see discussion in class
}