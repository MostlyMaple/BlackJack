package application.Models;
import java.util.*;

/**
 * BlackJack is a Java model class that contains all the functions to play BlackJack. It uses a deck of cards to deal
 * to two arrayLists of player and dealer cards.
 * 
 * 
 * @Stephen_Dean
 * UTSA CS 3443 - Final Project
 * Fall 2021
 */

public class BlackJack  {
	ArrayList<Card> userCards = new ArrayList<Card>(); 
	ArrayList<Card> dealerCards = new ArrayList<Card>(); 
	Deck curDeck = new Deck(true);
	boolean gameStarted = false;
	boolean userStayed = false;
	boolean userBusted = false;
	
	
	/* Method Name: deal
	 * Return type: void
	 * Parameters: None
	 * Description: Shuffles the deck object so the cards are randomized and deals the player and dealer two cards.
	 */
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
	
	
	/* Method Name: getUserCards
	 * Return type: ArrayList<Card>
	 * Parameters: None
	 * Description: Returns the users cards as an ArrayList of Cards
	 */
	public ArrayList<Card> getUserCards() {
		return userCards;
	}
	
	
	/* Method Name: getDealerCards
	 * Return type: ArrayList<Card>
	 * Parameters: None
	 * Description: Returns the dealers cards as an ArrayList of Cards
	 */
	public ArrayList<Card> getDealerCards() {
		return dealerCards;
	}
	
	
	/* Method Name: hitPlayer
	 * Return type: int
	 * Parameters: None
	 * Description: Gives the player a new card and returns 0 if they have a card total over 21.
	 */
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
	
	
	/* Method Name: dealerRound
	 * Return type: int
	 * Parameters: None
	 * Description: If the dealer has a card total less than 17 they are required to hit, so the dealer AI
	 * chooses to receive another card and will keep receiving new cards until they are >= 17 or bust.
	 */
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
	
	/* Method Name: showdown
	 * Return type: int
	 * Parameters: None
	 * Description: Checks to see if the user has a higher, lower, or equal card total to the dealer. There are 
	 * many different cases due to the fact that an ace can equal 1 or 11.
	 */
	public int showdown() {
		if (getTotal(userCards)[0] > 21) {
			System.out.println("DEALER WINS! 1");
			return 0;
		} else if (getTotal(dealerCards)[0] > 21) {
			System.out.println("PLAYER WINS!");
			return 1;
		}
		if (!hasAce(userCards) && !hasAce(dealerCards)) {
			if (getTotal(userCards)[0] == getTotal(dealerCards)[0]) {
				System.out.println("TIE!");
				return 2;
			} else if (getTotal(userCards)[0] > getTotal(dealerCards)[0]) {
				System.out.println("PLAYER WINS!");
				return 1;
			} else {
				System.out.println("DEALER WINS! 2");
				return 0;
			}
		} else if (hasAce(userCards) && !hasAce(dealerCards)) {
			if (getTotal(userCards)[1] < 22) {
				if (getTotal(userCards)[1] == getTotal(dealerCards)[0]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[1] > getTotal(dealerCards)[0]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 3");
					return 0;
				}
			} else {
				if (getTotal(userCards)[0] == getTotal(dealerCards)[0]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[0] > getTotal(dealerCards)[0]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 4");
					return 0;
				}
			}
		} else if (!hasAce(userCards) && hasAce(dealerCards)) {
			if (getTotal(dealerCards)[1] < 22) {
				if (getTotal(userCards)[0] == getTotal(dealerCards)[1]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[0] > getTotal(dealerCards)[1]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 5");
					return 0;
				}
			} else {
				if (getTotal(userCards)[0] == getTotal(dealerCards)[0]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[0] > getTotal(dealerCards)[0]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 6");
					return 0;
				}
			}
		} else if (hasAce(userCards) && hasAce(dealerCards)) {
			if (getTotal(dealerCards)[1] < 22 && getTotal(userCards)[1] < 22) {
				if (getTotal(userCards)[1] == getTotal(dealerCards)[1]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[1] > getTotal(dealerCards)[1]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 7");
					return 0;
				}
			} else if (!(getTotal(dealerCards)[1] < 22) && getTotal(userCards)[1] < 22) {
				if (getTotal(userCards)[1] == getTotal(dealerCards)[0]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[1] > getTotal(dealerCards)[0]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 8");
					return 0;
				}
			} else if (getTotal(dealerCards)[1] < 22 && !(getTotal(userCards)[1] < 22)){
				if (getTotal(userCards)[0] == getTotal(dealerCards)[1]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[0] > getTotal(dealerCards)[1]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 9");
					return 0;
				}
			} else {
				if (getTotal(userCards)[0] == getTotal(dealerCards)[0]) {
					System.out.println("TIE!");
					return 2;
				} else if (getTotal(userCards)[0] > getTotal(dealerCards)[0]) {
					System.out.println("PLAYER WINS!");
					return 1;
				} else {
					System.out.println("DEALER WINS! 10");
					return 0;
				}
			}
		}
		return -1;
	}
	
	/* Method Name: getTotal
	 * Return type: int[]
	 * Parameters: None
	 * Description: The card total of either the user or dealer is calculated and two possible values are returned
	 * due to the fact that an ace can equal both 1 or 11.
	 */
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
	
	
	/* Method Name: printDealerCards
	 * Return type: void
	 * Parameters: None
	 * Description: Formats and prints the dealer cards to console
	 */
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
	
	
	/* Method Name: hasAce
	 * Return type: boolean
	 * Parameters: ArrayList<Card> hand
	 * Description: Checks whether or not the input hand has an ace or not.
	 */
	public boolean hasAce(ArrayList<Card> hand) {
		int totalCards = hand.size();
		for(int i = 0; i < totalCards; i++) {
			if (hand.get(i).value == 1) {
				return true;
			}
		}
		return false;
	}
	
	
	/* Method Name: printUserCards
	 * Return type: void
	 * Parameters: None
	 * Description: Formats and prints the user's cards to console
	 */
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

	
	/* Method Name: isGameStarted
	 * Return type: boolean
	 * Parameters: none
	 * Description: Returns true if a hand has been dealt.
	 */
	public boolean isGameStarted() {
		return gameStarted;
	}

	/* Method Name: setGameStarted
	 * Return type: void
	 * Parameters: boolean gameStarted
	 * Description: Set whether or not a hand has been dealt
	 */
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	
	/* Method Name: isUserStayed
	 * Return type: boolean
	 * Parameters: none
	 * Description: Returns true if the user stays with the current hand
	 */
	public boolean isUserStayed() {
		return userStayed;
	}

	
	/* Method Name: setUserStayed
	 * Return type: void
	 * Parameters: boolean userStayed
	 * Description: Set whether or not the user is staying with their hand.
	 */
	public void setUserStayed(boolean userStayed) {
		this.userStayed = userStayed;
	}
	
	/* Method Name: isUserBusted
	 * Return type: boolean
	 * Parameters: none
	 * Description: Returns true if the user busts
	 */
	public boolean isUserBusted() {
		return userBusted;
	}

	
	/* Method Name: setUserBusted
	 * Return type: void
	 * Parameters: boolean user	Busted
	 * Description: Set whether or not the user has busted their hand.
	 */
	public void setUserBusted(boolean userBusted) {
		this.userBusted = userBusted;
	}
}
