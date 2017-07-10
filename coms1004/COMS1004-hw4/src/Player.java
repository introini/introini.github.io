/******************************************************************************
 * Michael Introini
 * mbi2105
 *
 * This class contains all information pertinent to the player
 *
 ******************************************************************************/


import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private ArrayList<Card> hand; // the player's cards

    public Player() {
        // create a player here
        hand = new ArrayList<Card>();
    }

    public void addCard(Card c) {
        // add the card c to the player's hand
        hand.add(c);
    }

    public void removeCard(Card c) {
        // remove the card c from the player's hand
        hand.remove(c);
    }

    // you will likely need more methods here

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> sortHand() {
        // sorts the hand in ascending order
        Collections.sort(hand);
        return hand;
    }

    public String toString() {
        String report = "";

        for (Card c: hand)
            report += c + "\n";

        return report;
    }
}