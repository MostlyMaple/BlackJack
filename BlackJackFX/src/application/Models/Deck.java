package application.Models;
import java.util.Random;

public class Deck {
	int cardCount = 52;
	boolean shuffled;
	Card[] cardStack;
	int topOfDeck = 0;
	boolean deckEmpty = false;
	
	public Deck(boolean shuffled) {
		this.shuffled = shuffled;
		cardStack = new Card[cardCount];
		this.createCards();
	}
	
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
	
	public String toString() {
		String deckPrint = "";
		
		for (int i = 0; i < cardCount; i++) {
			deckPrint = deckPrint + cardStack[i].toString();
		}
		
		return deckPrint;
	}
	
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
