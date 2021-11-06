package application.Models;

public class Card {
	int suit;
	int value;
	
	public Card() {
	}
	
	public Card(int suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	
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
	
	public int getCardValue() {
		int val = 0;
		if (this.value >= 10) {
			val = 10;
		} else {
			val = this.value;
		}
		return val;
	}
	
	public int getCardSuit() {
		return suit;
	}
}
