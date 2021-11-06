package application.Models;
import java.util.*;

public class BlackJack  {
	ArrayList<Card> userCards = new ArrayList<Card>(); 
	ArrayList<Card> dealerCards = new ArrayList<Card>(); 
	Deck curDeck = new Deck(true);
	boolean gameStarted = false;
	boolean userStayed = false;
	boolean userBusted = false;
	
	public void deal() {
		if (curDeck.shuffled) {
			curDeck.shuffleDeck(1000);
		}
		
		userCards.add(curDeck.getTopCard());
		userCards.add(curDeck.getTopCard());
		dealerCards.add(curDeck.getTopCard());
		dealerCards.add(curDeck.getTopCard());
		
		System.out.println("User Card 1: " + userCards.get(0).toString());
		System.out.println("User Card 2: " + userCards.get(1).toString());
		if (hasAce(userCards)) {
			System.out.println("User Card Total: " + getTotal(userCards)[0] + " or " + getTotal(userCards)[1] + "\n");
		} else {
			System.out.println("User Card Total: " + getTotal(userCards)[0] + "\n");
		}
		
		System.out.println("Dealer Card 1: " + dealerCards.get(0).toString());
		System.out.println("Dealer Card 2: " + dealerCards.get(1).toString());
		if (hasAce(dealerCards)) {
			System.out.println("Dealer Card Total: " + getTotal(dealerCards)[0] + " or " + getTotal(dealerCards)[1] + "\n");
		} else {
			System.out.println("Dealer Card Total: " + getTotal(dealerCards)[0] + "\n");
		}
	}
	
	public ArrayList<Card> getUserCards() {
		return userCards;
	}
	
	public ArrayList<Card> getDealerCards() {
		return dealerCards;
	}
	
	public int hitPlayer() {
		Scanner inp = new Scanner(System.in);
		Card newCard;
		int[] total = new int[2];
		total = getTotal(userCards);
		newCard = curDeck.getTopCard();
		userCards.add(newCard);
		System.out.println("You hit for a " + newCard.toString());
		total = getTotal(userCards);
		if (hasAce(userCards) && total[1] < 22) {
			System.out.println("User Card Total: " + getTotal(userCards)[0] + " or " + getTotal(userCards)[1] + "\n");
		} else {
			System.out.println("User Card Total: " + getTotal(userCards)[0] + "\n");
		}
			
		if (total[0] > 21) {
			System.out.println("BUST, You lose!");
			inp.close();
			return 0;
		} 
		
		inp.close();
		return 1;
		
	}
	
	public int dealerRound() {
		int[] dealerTotal = new int[2];
		dealerTotal[0] = 0;
		dealerTotal[1] = 0;
		dealerTotal	= getTotal(dealerCards);
		Card newCard;
		if (hasAce(dealerCards)) {
			while (dealerTotal[1] < 17) {
				newCard = curDeck.getTopCard();
				dealerCards.add(newCard);
				System.out.println("Dealer hits for a " + newCard.toString());
				dealerTotal	= getTotal(dealerCards);
				System.out.println("Dealer Card Total: " + getTotal(dealerCards)[0] + " or " + getTotal(dealerCards)[1] + "\n");
			}
			
			while (dealerTotal[0] < 17 && dealerTotal[1] > 21) {
				newCard = curDeck.getTopCard();
				dealerCards.add(newCard);
				System.out.println("Dealer hits for a " + newCard.toString());
				dealerTotal	= getTotal(dealerCards);
			}
		} else {
			while (dealerTotal[0] < 17) {
				newCard = curDeck.getTopCard();
				dealerCards.add(newCard);
				System.out.println("Dealer hits for a " + newCard.toString());
				dealerTotal	= getTotal(dealerCards);
			}
		}
		if (dealerTotal[0] <= 21 || dealerTotal[1] <= 21) {
			printDealerCards(dealerCards);
			System.out.println("Dealer stands!");
			if (hasAce(dealerCards) && dealerTotal[1] < 22) {
				System.out.println("Dealer Card Total: " + getTotal(dealerCards)[1] + "\n");
			} else {
				System.out.println("Dealer Card Total: " + getTotal(dealerCards)[0] + "\n");
			}
		} else {
			System.out.println("Dealer busts!");
			return 0;
		}
		return 1;
	}
	
	
	public int showdown() {
		
		if (getTotal(userCards)[0] > 21) {
			System.out.println("DEALER WINS!");
			return 0;
		} else if (getTotal(dealerCards)[0] > 21) {
			System.out.println("PLAYER WINS!");
			return 1;
		}
		if (!hasAce(userCards) && !hasAce(dealerCards)) {
			if (getTotal(userCards)[0] > getTotal(dealerCards)[0]) {
				System.out.println("PLAYER WINS!");
				return 1;
			} else {
				System.out.println("DEALER WINS!");
				return 0;
			}
		} else if (hasAce(userCards) && !hasAce(dealerCards)) {
			if (getTotal(userCards)[1] > getTotal(dealerCards)[0]) {
				System.out.println("PLAYER WINS!");
				return 1;
			} else {
				System.out.println("DEALER WINS!");
				return 0;
			}
		} else if (!hasAce(userCards) && hasAce(dealerCards)) {
			if (getTotal(userCards)[0] > getTotal(dealerCards)[1]) {
				System.out.println("PLAYER WINS!");
				return 1;
			} else {
				System.out.println("DEALER WINS!");
				return 0;
			}
		} else if (hasAce(userCards) && hasAce(dealerCards)) {
			if (getTotal(userCards)[1] > getTotal(dealerCards)[1]) {
				System.out.println("PLAYER WINS!");
				return 1;
			} else {
				System.out.println("DEALER WINS!");
				return 0;
			}
		}
		return -1;
	}
	
	
	public int[] getTotal(ArrayList<Card> checkCards) {
		int totalCards = checkCards.size();
		int[] total = new int[2];
		total[0] = 0;
		total[1] = 0;
		Card curCard;
		for (int i = 0; i < totalCards; i++) {
			curCard = checkCards.get(i);
			total[0] = total[0] + curCard.getCardValue();
			if (curCard.getCardValue() == 1) {
				total[1] = total[1] + curCard.getCardValue() + 10;
			} else {
				total[1] = total[1] + curCard.getCardValue();
			}
		}
		return total;
	}
	
	public void printDealerCards(ArrayList<Card> cPrint) {
		int totalCards = cPrint.size();
		Card curCard;
		int numCard = 1;
		String toPrint = "";
		for (int i = 0; i < totalCards; i++) {
			curCard = cPrint.get(i);
			toPrint = toPrint + "Dealer Card " + numCard + ": " + curCard.toString() + "\n";
			numCard = numCard + 1;
		}
		System.out.println(toPrint);
	}
	
	public boolean hasAce(ArrayList<Card> hand) {
		int totalCards = hand.size();
		for(int i = 0; i < totalCards; i++) {
			if (hand.get(i).value == 1) {
				return true;
			}
		}
		return false;
	}
	
	public void printUserCards(ArrayList<Card> cPrint) {
		int totalCards = cPrint.size();
		Card curCard;
		int numCard = 1;
		String toPrint = "";
		for (int i = 0; i < totalCards; i++) {
			curCard = cPrint.get(i);
			toPrint = toPrint + "User Card " + numCard + ": " + curCard.toString() + "\n";
			numCard = numCard + 1;
		}
		System.out.println(toPrint);
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public boolean isUserStayed() {
		return userStayed;
	}

	public void setUserStayed(boolean userStayed) {
		this.userStayed = userStayed;
	}
	
	public boolean isUserBusted() {
		return userBusted;
	}

	public void setUserBusted(boolean userBusted) {
		this.userBusted = userBusted;
	}
}
