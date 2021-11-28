package application.Models;
import java.util.Random;

/**
 * Deck is a Java model class that contains all the functions to use a deck of cards.
 * 
 * 
 * @Stephen_Dean
 * UTSA CS 3443 - Final Project
 * Fall 2021
 */

public class Deck {
	int cardCount = 52;
	boolean shuffled;
	Card[] cardStack;
	int topOfDeck = 0;
	boolean deckEmpty = false;
	
	/* Method Name: Deck
	 * Return type: void
	 * Parameters: None
	 * Description: Constructor.
	 */
	public Deck(boolean shuffled) {
		this.shuffled = shuffled;
		cardStack = new Card[cardCount];
		this.createCards();
	}
	
	
	/* Method Name: createCards
	 * Return type: void
	 * Parameters: None
	 * Description: Adds 52 cards to the deck of each value / suit.
	 */
	public void createCards() {
		int curCard = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 13; j++) {
				Card newCard = new Card(i,j);
				cardStack[curCard] = newCard;
				curCard++;
			}
		}
	}
	
	/* Method Name: shuffleDeck
	 * Return type: void
	 * Parameters: int shuffleAmount
	 * Description: Shuffles the deck by swapping two random card location of shuffleAmount times.
	 */
	public void shuffleDeck(int shuffleAmount) {
		Random rand = new Random();
		Card placeHolder = new Card();
		int val1, val2;
		for(int i = 0; i < shuffleAmount; i++) {
			val1 = rand.nextInt(52);
			val2 = rand.nextInt(52);
			if (val1 == val2) {
				continue;
			}
			placeHolder = cardStack[val1];
			cardStack[val1] = cardStack[val2];
			cardStack[val2] = placeHolder;
		}
	}
	
	/* Method Name: toString
	 * Return type: String
	 * Parameters: None
	 * Description: Formats the deck to a printable string format.
	 */
	public String toString() {
		String deckPrint = "";
		
		for (int i = 0; i < cardCount; i++) {
			deckPrint = deckPrint + cardStack[i].toString();
		}
		
		return deckPrint;
	}
	
	/* Method Name: getTopCard
	 * Return type: Card
	 * Parameters: None
	 * Description: Returns the top card of the deck.
	 */
	public Card getTopCard() {
		Card topCard = cardStack[topOfDeck];
		if (topOfDeck < 51) {
			topOfDeck++;
		} else {
			deckEmpty = true;
		}
		return topCard;
	}
	
}
