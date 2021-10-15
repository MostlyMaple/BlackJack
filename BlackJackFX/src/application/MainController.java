package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {
	
	BlackJack game = new BlackJack();
	boolean gameStarted = false;
	boolean userStayed = false;
	final int HIDDEN = 1;
	final int NOT_HIDDEN = 0;
	int curWager = 0;
	
    @FXML
    private ImageView table;

    @FXML
    private Label dealerTotalLbl;

    @FXML
    private ImageView userCard10;

    @FXML
    private ImageView userCard11;

    @FXML
    private Label wagerLbl;

    @FXML
    private ImageView dealerCard11;

    @FXML
    private Button startBtn;

    @FXML
    private ImageView dealerCard10;

    @FXML
    private Label balanceLbl;

    @FXML
    private ImageView dealerCard9;

    @FXML
    private Label userTotalLbl;

    @FXML
    private Button stayBtn;

    @FXML
    private ImageView dealerCard1;

    @FXML
    private ImageView userCard4;

    @FXML
    private ImageView dealerCard2;

    @FXML
    private ImageView userCard3;

    @FXML
    private ImageView dealerCard3;

    @FXML
    private ImageView userCard6;

    @FXML
    private ImageView dealerCard4;

    @FXML
    private ImageView userCard5;

    @FXML
    private ImageView dealerCard5;

    @FXML
    private ImageView userCard8;

    @FXML
    private ImageView dealerCard6;

    @FXML
    private ImageView userCard7;

    @FXML
    private ImageView dealerCard7;

    @FXML
    private Label bustLbl;

    @FXML
    private ImageView dealerCard8;

    @FXML
    private ImageView userCard9;

    @FXML
    private Button hitBtn;

    @FXML
    private ImageView userCard0;

    @FXML
    private ImageView userCard2;

    @FXML
    private ImageView dealerCard0;

    @FXML
    private ImageView userCard1;
    
    @FXML
    private TextField manualBet;
    
    @FXML
    private Button customBtn;
    
    
    @FXML
    void betCustom(ActionEvent event) {
    	String betAmount = manualBet.getText();
    	int strToInt;
    	if (!game.isGameStarted()) {
    		try {
    			strToInt = Integer.parseInt(betAmount);
    			if (strToInt <= Main.balance) {
    				curWager = strToInt;
    				setLabels(NOT_HIDDEN);
    			}
    		} catch (Exception e) {
    			System.out.println("That is not an int");
    		}
    	}
    }
    
    
    
    @FXML
    void hitUser(ActionEvent event) {
    	if (game.isGameStarted() && !game.isUserStayed()) {
    		int[] userTotal = new int[2];
    		userTotal = game.getTotal(game.userCards);
    		if (userTotal[0] < 21) {
    			game.hitPlayer();
    		} 
    		userTotal = game.getTotal(game.userCards);
    		if (userTotal[0] > 21) {
    			game.setGameStarted(false);
    			curWager = 0;
    			setLabels(NOT_HIDDEN);
    		} 
    		setLabels(HIDDEN);
    	}
    }

    @FXML
    void stayUser(ActionEvent event) {
    	int result;
    	int dealerCheck;
    	if (game.isGameStarted() && !game.isUserStayed()) {
    		if (!game.isUserBusted()) {
    			game.setUserStayed(true);
    			dealerCheck = game.dealerRound();
    			setLabels(NOT_HIDDEN);
    			if (dealerCheck > 0) {
    				result = game.showdown();
    				game.setGameStarted(false);
    				if (result == 0) {
    					setLabels(NOT_HIDDEN);
    					curWager = 0;
    				} else {
    					Main.balance = Main.balance + curWager * 2;
    					curWager = 0;
    					setLabels(NOT_HIDDEN);
    				}
    			} else {
    				Main.balance = Main.balance + curWager * 2;
    				curWager = 0;
    				game.setGameStarted(false);
    				setLabels(NOT_HIDDEN);
    			}
    		}
    	}
    }

    @FXML
    void startGame(ActionEvent event) {
    	if (!game.isGameStarted() && curWager > 0) {
    		reset();
    		Main.balance = Main.balance - curWager;
    		game = new BlackJack();
    		game.deal();
    		setLabels(HIDDEN);
    		game.setGameStarted(true);
    	}
    }
    
    
    void setLabels(int dealerHidden) {
    	int[] userTotal = new int[2];
    	int[] dealerTotal = new int[2];
    	userTotal = game.getTotal(game.userCards);
    	dealerTotal = game.getTotal(game.dealerCards);
    	balanceLbl.setText("Balance: " + Main.balance);
    	wagerLbl.setText("Wager: " + curWager);
    	if (game.hasAce(game.userCards) && userTotal[1] < 22) {
    		userTotalLbl.setText("User Card Total: " + userTotal[0] + " or " + userTotal[1] + "\n");
		} else {
			userTotalLbl.setText("User Card Total: " + userTotal[0] + "\n");
		}
    	if (dealerHidden == 0) {
    		if (game.hasAce(game.dealerCards) && dealerTotal[1] < 22) {
    			dealerTotalLbl.setText("Dealer Card Total: " + dealerTotal[0] + " or " + dealerTotal[1] + "\n");
			} else {
				dealerTotalLbl.setText("Dealer Card Total: " + dealerTotal[0] + "\n");
			}
    	} else {
    		dealerTotalLbl.setText("Dealer Card Total: HIDDEN\n");
    	}
    	setUserCards();
    	setDealerCards(dealerHidden);
    }
    
    
    void setUserCards() {
    	Image[] cardImage = new Image[12];
    	String cardName;
    	for (int i = 0; i < game.userCards.size(); i++) {
    		cardName = "application/Classic/";
    		switch (game.userCards.get(i).suit) {
    			case(0):
    				cardName = cardName + "h";
    				break;
    			case(1):
    				cardName = cardName + "d";
					break;
    			case(2):
    				cardName = cardName + "s";
    				break;
    			case(3):
    				cardName = cardName + "c";
    				break;
    			default:
    				cardName = cardName + "s";
    		}
    		if (game.userCards.get(i).value < 10) {
    			cardName = cardName + "0" + game.userCards.get(i).value + ".png";
    		} else {
    			cardName = cardName + game.userCards.get(i).value + ".png";
    		}
			cardImage[i] = new Image(cardName);
			switch(i) {
				case(0):
					userCard0.setImage(cardImage[i]);
					userCard0.setVisible(true);
					break;
				case(1):
					userCard1.setImage(cardImage[i]);
					userCard1.setVisible(true);
					break;
				case(2):
					userCard2.setImage(cardImage[i]);
					userCard2.setVisible(true);
					break;
				case(3):
					userCard3.setImage(cardImage[i]);
					userCard3.setVisible(true);
					break;
				case(4):
					userCard4.setImage(cardImage[i]);
					userCard4.setVisible(true);
					break;
				case(5):
					userCard5.setImage(cardImage[i]);
					userCard5.setVisible(true);
					break;
				case(6):
					userCard6.setImage(cardImage[i]);
					userCard6.setVisible(true);
					break;
				case(7):
					userCard7.setImage(cardImage[i]);
					userCard7.setVisible(true);
					break;
				case(8):
					userCard8.setImage(cardImage[i]);
					userCard8.setVisible(true);
					break;
				case(9):
					userCard9.setImage(cardImage[i]);
					userCard9.setVisible(true);
					break;
				case(10):
					userCard10.setImage(cardImage[i]);
					userCard10.setVisible(true);
					break;
				case(11):
					userCard11.setImage(cardImage[i]);
					userCard11.setVisible(true);
					break;
				default:
					break;
			}
    	}
    }
    
    
    void setDealerCards(int hidden) {
    	Image[] cardImage = new Image[12];
    	String backOfCard = "backs/Card-Back-05.png";
    	Image back = new Image(backOfCard);
    	String cardName;
    	for (int i = 0; i < game.dealerCards.size(); i++) {
    		cardName = "application/Classic/";
    		switch (game.dealerCards.get(i).suit) {
    			case(0):
    				cardName = cardName + "h";
    				break;
    			case(1):
    				cardName = cardName + "d";
					break;
    			case(2):
    				cardName = cardName + "s";
    				break;
    			case(3):
    				cardName = cardName + "c";
    				break;
    			default:
    				cardName = cardName + "s";
    		}
    		if (game.dealerCards.get(i).value < 10) {
    			cardName = cardName + "0" + game.dealerCards.get(i).value + ".png";
    		} else {
    			cardName = cardName + game.dealerCards.get(i).value + ".png";
    		}
			cardImage[i] = new Image(cardName);
			switch(i) {
				case(0):
					if (hidden == 1) {
						dealerCard0.setImage(back);
					} else {
						dealerCard0.setImage(cardImage[i]);
					}
					dealerCard0.setVisible(true);
					break;
				case(1):
					dealerCard1.setImage(cardImage[i]);
					dealerCard1.setVisible(true);
					break;
				case(2):
					dealerCard2.setImage(cardImage[i]);
					dealerCard2.setVisible(true);
					break;
				case(3):
					dealerCard3.setImage(cardImage[i]);
					dealerCard3.setVisible(true);
					break;
				case(4):
					dealerCard4.setImage(cardImage[i]);
					dealerCard4.setVisible(true);
					break;
				case(5):
					dealerCard5.setImage(cardImage[i]);
				    dealerCard5.setVisible(true);
					break;
				case(6):
					dealerCard6.setImage(cardImage[i]);
					dealerCard6.setVisible(true);
					break;
				case(7):
					dealerCard7.setImage(cardImage[i]);
					dealerCard7.setVisible(true);
					break;
				case(8):
					dealerCard8.setImage(cardImage[i]);
					dealerCard8.setVisible(true);
					break;
				case(9):
					dealerCard9.setImage(cardImage[i]);
					dealerCard9.setVisible(true);
					break;
				case(10):
					dealerCard10.setImage(cardImage[i]);
					dealerCard10.setVisible(true);
					break;
				case(11):
					dealerCard11.setImage(cardImage[i]);
					dealerCard11.setVisible(true);
					break;
				default:
					break;
			}
    	}
    }
    
    void reset() {
    	game = new BlackJack();
		dealerCard0.setVisible(false);
		dealerCard1.setVisible(false);
		dealerCard2.setVisible(false);
		dealerCard3.setVisible(false);
		dealerCard4.setVisible(false);
	    dealerCard5.setVisible(false);
		dealerCard6.setVisible(false);
		dealerCard7.setVisible(false);
		dealerCard8.setVisible(false);
		dealerCard9.setVisible(false);
		dealerCard10.setVisible(false);
		dealerCard11.setVisible(false);
		userCard0.setVisible(false);
		userCard1.setVisible(false);
		userCard2.setVisible(false);
		userCard3.setVisible(false);
		userCard4.setVisible(false);
		userCard5.setVisible(false);
		userCard6.setVisible(false);
		userCard7.setVisible(false);
		userCard8.setVisible(false);
		userCard9.setVisible(false);
		userCard10.setVisible(false);
		userCard11.setVisible(false);
		setLabels(HIDDEN);
    }

}

































