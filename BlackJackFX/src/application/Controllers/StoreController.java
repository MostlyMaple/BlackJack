package application.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * StoreController is a Controller class that shows items that the player can purchase with their balance.
 * 
 * UTSA CS 3443 - Final Project
 * Fall 2021
 */

public class StoreController implements Initializable {

	@FXML
	private ImageView monkey;

	@FXML
	private Button noBuyButton;

	@FXML
	private Button mainMenuButton;

	@FXML
	private TextArea totalBalanceText;

	@FXML
	private Button confirmBuyButton;

	@FXML
	private TextArea confirmBuyText;

	@FXML
	private ImageView tree;

	@FXML
	private ImageView dog;

	@FXML
	private ImageView food;

	// Initialize screen with users current balance
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set totalBalanceText to users current balance
		totalBalanceText.setText("Current balance: $" + Main.balance);

	}

	// Controls when one of the images are clicked
	@FXML
	void imageClicked(MouseEvent event) {
		// Image clicked Christmas tree
		if (event.getPickResult().getIntersectedNode().getId().compareTo("tree") == 0) {

			// Confirm users choice
			confirmBuyText.setText("Are you sure you want to buy the tree?");

			// if confirm button clicked
			confirmBuyButton.setOnAction(new EventHandler<ActionEvent>() {

				// Handle if user doesn't have enoughtin balance
				@Override
				public void handle(ActionEvent event) {
					// Print error message if not enough balance
					if (Main.balance - 200 < 0) {
						confirmBuyText.setText("You do not have enough money in your balance to buy the tree");
					} else {
						// decrease balance and print message if enough
						Main.balance = Main.balance - 200;
						totalBalanceText.setText("New Balance:" + Main.balance);
						confirmBuyText.setText("Merry Christmas!");
					}
				}

			});
			// if no button clicked
			// Image clicked dog
		} else if (event.getPickResult().getIntersectedNode().getId().compareTo("dog") == 0) {
			// Confirm users choice
			confirmBuyText.setText("Are you sure you want to buy the dog?");

			// if confirm button clicked
			confirmBuyButton.setOnAction(new EventHandler<ActionEvent>() {

				// Handle if user doesn't have enoughtin balance
				@Override
				public void handle(ActionEvent event) {
					// Print error message if not enough balance
					if (Main.balance - 400 < 0) {
						confirmBuyText.setText("You do not have enough money in your balance to buy the dog");
					} else {
						// decrease balance and print message if enough
						Main.balance = Main.balance - 400;
						totalBalanceText.setText("New Balance:" + Main.balance);
						confirmBuyText.setText("Congrats on the new dog!");
					}
				}

			});

			// Image clicked monkey
		} else if (event.getPickResult().getIntersectedNode().getId().compareTo("monkey") == 0) {
			// Confirm users choice
			confirmBuyText.setText("Are you sure you want to buy the monkey?");

			// if confirm button clicked
			confirmBuyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Print error message if not enough balance
					if (Main.balance - 500 < 0) {
						confirmBuyText.setText("You do not have enough money in your balance to buy the monkey");
					} else {
						// decrease balance and print message if enough
						Main.balance = Main.balance - 500;
						totalBalanceText.setText("New Balance:" + Main.balance);
						confirmBuyText.setText("Congrats on the new monkey!");
					}
				}

			});

			// Image clicked food
		} else if (event.getPickResult().getIntersectedNode().getId().compareTo("food") == 0) {
			confirmBuyText.setText("Are you sure you want to buy the meal?");

			// if confirm button clicked
			confirmBuyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Print error message if not enough balance
					if (Main.balance - 100 < 0) {
						confirmBuyText.setText("You do not have enough money in your balance to buy the meal");
					} else {
						// decrease balance and print message if enough
						Main.balance = Main.balance - 100;
						totalBalanceText.setText("New Balance:" + Main.balance);
						confirmBuyText.setText("Enjoy your food!");
					}

				}

			});

		}

	}

	// Takes user back to Main Meny once main menuu button is clicked
	@FXML
	void mainMenuHandle(ActionEvent event) throws IOException {
		URL url = new File("src/application/View/MainMenu.fxml").toURI().toURL();
		AnchorPane mainPane = FXMLLoader.load(url);
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();

	}

}
