/******************************************************************************
 * Michael Introini
 * mbi2105
 *
 * This class contains all information pertinent to a Card
 *
 ******************************************************************************/

public class Card implements Comparable<Card> {

    private int suit;// use integers 1-4 to encode the suit
    private int value; // use integers 1-13 to encode the value

    public Card(int s, int v) {
        //make a card with suit s and value v
        suit = s;
        value = v;
    }

    private String whatSuit(int s){
        String whatSuit;
        // Cases for each suit
        switch (s) {
            case 1: whatSuit = "Clubs"; break;
            case 2: whatSuit = "Diamonds"; break;
            case 3: whatSuit = "Hearts"; break;
            case 4: whatSuit = "Spades"; break;
            default: whatSuit = ""; break;
        }

        return whatSuit;
    }

    private String whatValue (int v){
        String whatValue;
        // Cases for face cards and the Aces
        switch (v) {
            case 1:  whatValue = "Ace"; break;
            case 11: whatValue = "Jack"; break;
            case 12: whatValue = "Queen"; break;
            case 13: whatValue = "King"; break;

            default: whatValue = Integer.toString(v); break;
        }

        return whatValue;
    }

    public int compareTo(Card other) {
        // use this method to compare cards so they
        // may be easily sorted

        // Simply check the value of the current card against the other card
        // and return a -1 if lower, 1 if higher, and 0 if the same
        if (this.value < other.value) {
            return -1;
        } else if (this.value > other.value) {
            return 1;
        } else {
            return 0;
        }

    }

    public boolean isSameSuit(Card other) {
        // This method is to determine if one suit is the same as another
        boolean match =  false;
        if (this.suit == other.suit) {
            match = true;
        }

        return match;
    }

    public int getValue() {
        // return the value of a particular card
        return this.value;
    }

    public int difference(Card other) {
        // return the difference in value between one card and another
        return this.value - other.value;
    }

    public String toString() {
        // use this method to easily print a Card object
        String cardDescription;

        cardDescription = whatValue(value) + " of " + whatSuit(suit);

        return cardDescription;
    }
}