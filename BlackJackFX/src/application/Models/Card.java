package application.Models;

/**
 * Card is a Java model class that contains all the functions to use an individual playing card.
 * Hearts - 0
 * Diamonds - 1
 * Spades - 2
 * Clubs - 3
 * @Stephen_Dean
 * UTSA CS 3443 - Final Project
 * Fall 2021
 */

public class Card {
	int suit;
	int value;
	
	/* Method Name: Card
	 * Return type: void
	 * Parameters: None
	 * Description: Constructor.
	 */
	public Card() {
		
	}
	
	/* Method Name: Card
	 * Return type: int suit, int value
	 * Parameters: None
	 * Description: Constructor.
	 */
	public Card(int suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	/* Method Name: toString
	 * Return type: String
	 * Parameters: None
	 * Description: Returns a string that contains the individual card info in readable format.
	 */
	public String toString() {
		String cardPrint = "";
		if (this.value == 11) {
			cardPrint = cardPrint + "Jack";
		} else if (this.value == 12) {
			cardPrint = cardPrint + "Queen";
		} else if (this.value == 13) {
			cardPrint = cardPrint + "King";
		} else if (this.value == 1) {
			cardPrint = cardPrint + "Ace";
		} else {
			cardPrint = cardPrint + this.value;
		}
		
		if (this.suit == 0) {
			cardPrint = cardPrint + " of Hearts";
		} else if (this.suit == 1) {
			cardPrint = cardPrint + " of Diamonds";
		} else if (this.suit == 2) {
			cardPrint = cardPrint + " of Spades";
		} else if (this.suit == 3) {
			cardPrint = cardPrint + " of Clubs";
		}
		return cardPrint;
	}
	
	/* Method Name: getCardValue
	 * Return type: int
	 * Parameters: None
	 * Description: Returns the value of a card.
	 */
	public int getCardValue() {
		int val = 0;
		if (this.value >= 10) {
			val = 10;
		} else {
			val = this.value;
		}
		return val;
	}
	
	/* Method Name: getCardSuit
	 * Return type: int
	 * Parameters: None
	 * Description: Returns the suit of a card.
	 */
	public int getCardSuit() {
		return suit;
	}
}
