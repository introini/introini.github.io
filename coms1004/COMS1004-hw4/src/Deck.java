/******************************************************************************
 * Michael Introini
 * mbi2105
 *
 * This class contains all instructions necessary for the Deck class
 *
 ******************************************************************************/

public class Deck {
    private Card[] theDeck;
    private int top; // the index of the top of the deck

    public Deck() {
        // make a 52 card deck here
        theDeck = new Card[52];
        top = 0;
        fillDeck();
        shuffle();
    }

    public void shuffle() {
        // shuffle the deck here
        int count = 0;
        // Swap two random cards 1000 times
        while (count < 1000) {
            int randomNum1 = (int) (Math.random() * 52) ;
            int randomNum2 = (int) (Math.random() * 52) ;
            Card temp = theDeck[randomNum1];
            theDeck[randomNum1] = theDeck[randomNum2];
            theDeck[randomNum2] = temp;
            count++;
        }
    }

    public Card deal() {
        // deal the top card in the deck
        Card nextCard = theDeck[top];
        top++;

        return nextCard;
    }

    private void fillDeck() {
        // Create 4 sets of cards from 1-13 with different suits

        for (int i = 0; i < theDeck.length; i++){
            if (i >= 0 && i <= 13){
                theDeck[i] = new Card(1, i + 1);
            }
            if (i >= 13 && i <= 26){
                theDeck[i] = new Card(2, (i - 13) + 1);
            }
            if (i >= 26 && i <= 39){
                theDeck[i] = new Card(3, (i - 26) + 1);
            }
            if (i >= 39 && i <= 52){
                theDeck[i] = new Card(4, (i - 39) + 1);
            }
        }
    }

    public String toString() {
        String report;
        report = "------------------ \n";

        for (Card c : theDeck)
           report += c + "\n";

        report += "There are " + theDeck.length + " cards in the deck.";

        return report;
    }
    // add more methods here if needed
}