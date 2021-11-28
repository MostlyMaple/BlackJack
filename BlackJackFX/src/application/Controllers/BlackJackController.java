package application.Controllers;


import java.io.IOException;

import application.Main;
import application.Models.BlackJack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BlackJackController {
	
	BlackJack game = new BlackJack();
	boolean gameStarted = false;
	boolean userStayed = false;
	final int HIDDEN = 1;
	final int NOT_HIDDEN = 0;
	int curWager = 0;
	
	@FXML
	private AnchorPane mainPane;
	
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
    private Button betOneBtn;
    
    @FXML
    private Button betTwentyFiveBtn;

    @FXML
    private Button betFiveBtn;
    
    @FXML
    private Button betHundredBtn;
    
    @FXML
    private Button betTenPerBtn;
    
    @FXML
    private Button betTwentyPerBtn;
    
    @FXML
    private Button betFiftyPerBtn;
    
    @FXML
    private Button betHundredPerBtn;
    
    /* Method Name: initialize
	 * Return type: void
	 * Parameters: None
	 * Description: Set all labels and hide labels that should be hidden.
	 */
    @FXML
    public void initialize() {
    	setLabels(HIDDEN);
    }
    
    
    /* Method Name: hitUser
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Give the user another card upon button press.
	 */
    @FXML
    void hitUser(ActionEvent event) {
    	if (game.isGameStarted() && !game.isUserStayed()) {
    		int[] userTotal = new int[2];
    		userTotal = game.getTotal(game.getUserCards());
    		if (userTotal[0] < 21) {
    			game.hitPlayer();
    		} 
    		userTotal = game.getTotal(game.getUserCards());
    		if (userTotal[0] > 21) {
    			game.setGameStarted(false);
    			curWager = 0;
    			bustLbl.setText("You Lose!");
    			setLabels(NOT_HIDDEN);
    		} else {
    			setLabels(HIDDEN);
    		}
    	}
    }

    
    /* Method Name: stayUser
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Checks whether the user has a higher, lower, or equal card value than the dealer and 
	 * distributes balance accordingly.
	 */
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
    					bustLbl.setText("You Lose!");
    					setLabels(NOT_HIDDEN);
    					curWager = 0;
    				} else if (result == 1){
    					bustLbl.setText("You Win!");
    					Main.balance = Main.balance + curWager * 2;
    					curWager = 0;
    					setLabels(NOT_HIDDEN);
    				} else {
    					bustLbl.setText("You Tie!");
    					Main.balance = Main.balance + curWager;
    					curWager = 0;
    					setLabels(NOT_HIDDEN);
    				}
    			} else {
    				bustLbl.setText("You Win!");
    				Main.balance = Main.balance + curWager * 2;
    				curWager = 0;
    				game.setGameStarted(false);
    				setLabels(NOT_HIDDEN);
    			}
    		}
    	}
    }

    
    /* Method Name: startGame
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Stops the user from betting during the game by setting a game start variable.
	 */
    @FXML
    void startGame(ActionEvent event) {
    	if (!game.isGameStarted() && curWager > 0) {
    		reset();
    		Main.balance = Main.balance - curWager;
    		game = new BlackJack();
    		game.deal();
    		Main.ticket = Main.ticket + 1;
    		setLabels(HIDDEN);
    		game.setGameStarted(true);
    	}
    }
    
    /* Method Name: setLabels
	 * Return type: void
	 * Parameters: int dealerHidden
	 * Description: Update all screen information to match the stored variables.
	 */
    void setLabels(int dealerHidden) {
    	int[] userTotal = new int[2];
    	int[] dealerTotal = new int[2];
    	userTotal = game.getTotal(game.getUserCards());
    	dealerTotal = game.getTotal(game.getDealerCards());
    	balanceLbl.setText("Balance: " + Main.balance);
    	if (dealerHidden == 0) {
    		bustLbl.setVisible(true);
    	} else {
    		bustLbl.setVisible(false);
    	}
    	wagerLbl.setText("Wager: " + curWager);
    	if (game.hasAce(game.getUserCards()) && userTotal[1] < 22) {
    		userTotalLbl.setText("User Card Total: " + userTotal[0] + " or " + userTotal[1] + "\n");
		} else {
			userTotalLbl.setText("User Card Total: " + userTotal[0] + "\n");
		}
    	if (dealerHidden == 0) {
    		if (game.hasAce(game.getDealerCards()) && dealerTotal[1] < 22) {
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
    
    /* Method Name: setUserCards
	 * Return type: void
	 * Parameters: None
	 * Description: Takes user card info and finds the image that matches the card and sets it on the screen.
	 */
    void setUserCards() {
    	Image[] cardImage = new Image[12];
    	String cardName;
    	for (int i = 0; i < game.getUserCards().size(); i++) {
    		cardName = "BlackJackImages/";
    		switch (game.getUserCards().get(i).getCardSuit()) {
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
    		if (game.getUserCards().get(i).getCardValue() < 10) {
    			cardName = cardName + "0" + game.getUserCards().get(i).getCardValue() + ".png";
    		} else {
    			cardName = cardName + game.getUserCards().get(i).getCardValue() + ".png";
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
    
    /* Method Name: setDealerCards
	 * Return type: void
	 * Parameters: int hidden
	 * Description: Takes dealer card info and finds the image that matches the card and sets it on the screen.
	 */
    void setDealerCards(int hidden) {
    	Image[] cardImage = new Image[12];
    	String backOfCard = "BlackJackImages/Card-Back-05.png";
    	Image back = new Image(backOfCard);
    	String cardName;
    	for (int i = 0; i < game.getDealerCards().size(); i++) {
    		cardName = "BlackJackImages/";
    		switch (game.getDealerCards().get(i).getCardSuit()) {
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
    		if (game.getDealerCards().get(i).getCardValue() < 10) {
    			cardName = cardName + "0" + game.getDealerCards().get(i).getCardValue() + ".png";
    		} else {
    			cardName = cardName + game.getDealerCards().get(i).getCardValue() + ".png";
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
    
    /* Method Name: reset
	 * Return type: void
	 * Parameters: None
	 * Description: Sets all cards to invisible and resets the game object.
	 */
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
    
    /* Method Name: betCustom
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Reads in a custom integer value to bet.
	 */
    @FXML
    void betCustom(ActionEvent event) {
    	String betAmount = manualBet.getText();
    	int strToInt;
    	setLabels(HIDDEN);
    	if (!game.isGameStarted()) {
    		try {
    			strToInt = Integer.parseInt(betAmount);
    			if (strToInt <= Main.balance && strToInt > 0) {
    				curWager = strToInt;
    				setLabels(HIDDEN);
    			}
    		} catch (Exception e) {
    			System.out.println("That is not an int");
    		}
    	}
    }
    
    /* Method Name: betOne
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Adds 1 to wager total.
	 */
    @FXML
    void betOne(ActionEvent event) {
    	reset();
    	setLabels(HIDDEN);
    	if (!game.isGameStarted()) {
    			if (curWager + 1 <= Main.balance) {
    				curWager = curWager + 1;
    				setLabels(HIDDEN);
    			}
    	}
    }

    /* Method Name: betFive
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Adds 5 to wager total.
	 */
    @FXML
    void betFive(ActionEvent event) {
    	setLabels(HIDDEN);
    	reset();
    	if (!game.isGameStarted()) {
    			if (curWager + 5 <= Main.balance) {
    				curWager = curWager + 5;
    				setLabels(HIDDEN);
    			}
    	}
    }

    
    /* Method Name: betTwentyFive
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Adds 1 to wager total.
	 */
    @FXML
    void betTwentyFive(ActionEvent event) {
    	setLabels(HIDDEN);
    	reset();
    	if (!game.isGameStarted()) {
    			if (curWager + 25 <= Main.balance) {
    				curWager = curWager + 25;
    				setLabels(HIDDEN);
    			}
    	}
    }

    
    /* Method Name: betHundred
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Adds 100 to wager total.
	 */
    @FXML
    void betHundred(ActionEvent event) {
    	setLabels(HIDDEN);
    	reset();
    	if (!game.isGameStarted()) {
    			if (curWager + 100 <= Main.balance) {
    				curWager = curWager + 100;
    				setLabels(HIDDEN);
    			}
    	}
    }
    
    /* Method Name: betTenPer
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Sets wager to 10% of balance.
	 */
    @FXML
    void betTenPer(ActionEvent event) {
    	setLabels(HIDDEN);
    	reset();
    	if (!game.isGameStarted()) {
    			curWager = (int)(Main.balance * .10);
    			setLabels(HIDDEN);
    	}
    }

    /* Method Name: betTwentyPer
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Sets wager to 25% of balance.
	 */
    @FXML
    void betTwentyPer(ActionEvent event) {
    	setLabels(HIDDEN);
    	reset();
    	if (!game.isGameStarted()) {
    			curWager = (int)(Main.balance * .25);
    			setLabels(HIDDEN);
    	}
    }

    /* Method Name: betFiftyPer
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Sets wager to 50% of balance.
	 */
    @FXML
    void betFiftyPer(ActionEvent event) {
    	setLabels(HIDDEN);
    	reset();
    	if (!game.isGameStarted()) {
    			curWager = (int)(Main.balance * .50);
    			setLabels(HIDDEN);
    	}
    }

    /* Method Name: betHundredPer
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Sets wager to 100% of balance.
	 */
    @FXML
    void betHundredPer(ActionEvent event) {
    	setLabels(HIDDEN);
    	reset();
    	if (!game.isGameStarted()) {
    			curWager = Main.balance;
    			setLabels(HIDDEN);
    	}
    }
    
    
    /* Method Name: mainMenu
	 * Return type: void
	 * Parameters: ActionEvent event
	 * Description: Switches window back to main menu.
	 */
    @FXML
    void mainMenu(ActionEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("/application/View/MainMenu.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		boolean isFullscreen = window.isFullScreen();
		window.setScene(scene);
		window.setFullScreen(isFullscreen);
		window.show();
    }

}

































