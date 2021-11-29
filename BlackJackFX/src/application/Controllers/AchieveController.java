package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Models.RaffleTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * AchieveController is a Controller class that shows goals for the user to achieve and allows the user to check
 * if they have completed the goals. It also allows the user to raffle off tickets to earn prizes for playing the game.
 * 
 * UTSA CS 3443 - Final Project
 * Fall 2021
 */

public class AchieveController implements Initializable {
	
	@FXML
    private Text Title;
	
    @FXML
    private Text balance;

    @FXML
    private Text ticketNum;
    
    @FXML
    private Text smallLog;
    
    @FXML
    private TextArea notes;
    
    @FXML
    private Button raffleBt;
    
    @FXML
    private ImageView lock1, lock2, lock3, lock4;
    
    @FXML
    private ImageView Bronze, Gold, Diamond, c1, c2, c3;
    
    // CheckBox for Money
    @FXML
    private CheckBox ck1500, ck2000, ck3000, ck5000;
    
    // CheckBox for ticket
    @FXML
    private CheckBox tk3, tk5, tk10, tk30;
    
    // CheckBox for raffle
    @FXML 
    private CheckBox rf1, rf5, rf10;
    
    // CheckBox for Medals
    @FXML
    private CheckBox bzm, gdm, allM;
    
    @FXML
    private AnchorPane blackJack;
    
    // receive money from game
    public void setBalance(int money) {
    	balance.setText(String.valueOf(money));
    }
	
    
	/**
	 * @param event
	 * @throws IOException
	 * 
	 * This function will back to the main game page!
	 */
	@FXML
    public void backSwitch(ActionEvent event) throws IOException {
		// save information
		Main.buff = notes.getText();
		
		// load the role view
		FXMLLoader loader = new FXMLLoader();
		URL url = getClass().getResource("/application/View/MainMenu.fxml");
		loader.setLocation(url);
		blackJack = loader.load();
        Scene scene = new Scene(blackJack);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
	
	/**
	 * @param event
	 * @throws InterruptedException
	 * 
	 * This is a muti-Thread function will play the anime and the store information in
	 * the main buffer.
	 */
	@FXML
	public void raffleSwitch(ActionEvent event) throws InterruptedException {
		if(Main.ticket >= 5) {
			Main.ticket -= 5;
			Main.raffleCounter++;
			ticketNum.setText(String.valueOf(Main.ticket));
			
			RaffleTask tk1 = new RaffleTask(c1, notes, balance, ticketNum);
			Thread t1 = new Thread(tk1);
			t1.start();
			
			RaffleTask tk2 = new RaffleTask(c2, notes, balance, ticketNum);
			Thread t2 = new Thread(tk2);
			t2.start();
			
			RaffleTask tk3 = new RaffleTask(c3, notes, balance, ticketNum);
			Thread t3 = new Thread(tk3);
			t3.start();
		} else {
			notes.appendText("Not Enough Ticket\n");
		}
		Main.buff = notes.getText();
	}
    
	
	
	
	// These functions will check if we meet specific requirement, then 
	// mark the check box and change the unlock image
    @FXML
    public void CheckLock1(MouseEvent event) {
    	int ck1 = check(ck1500, Main.balance, 1500);
    	int ck2 = check(tk3, Integer.parseInt(ticketNum.getText()), 3);
    	resetImage(lock1, ck1+ck2, 2);
    }

    @FXML
    public void CheckLock2(MouseEvent event) {
    	int ck1 = check(ck2000, Main.balance, 2000);
    	int ck2 = check(tk5, Integer.parseInt(ticketNum.getText()), 5);
    	int ck3 = check(rf1, Main.raffleCounter, 1);
    	int ck4 = stringCheck(bzm, notes.getText(), "Bronze");
    	resetImage(lock2, ck1+ck2+ck3+ck4, 4);
    	
    }

    @FXML
    public void CheckLock3(MouseEvent event) {
    	int ck1 = check(ck3000, Main.balance, 3000);
    	int ck2 = check(tk10, Integer.parseInt(ticketNum.getText()), 10);
    	int ck3 = check(rf5, Main.raffleCounter, 5);
    	int ck4 = stringCheck(gdm, notes.getText(), "Golden");
    	resetImage(lock3, ck1+ck2+ck3+ck4, 4);
    }

    @FXML
    public void CheckLock4(MouseEvent event) {
    	int ck1 = check(ck5000, Main.balance, 5000);
    	int ck2 = check(tk30, Integer.parseInt(ticketNum.getText()), 30);
    	int ck3 = check(rf10, Main.raffleCounter, 10);
    	int ck4 = stringCheck(allM, notes.getText(), "Diamond");
    	resetImage(lock4, ck1+ck2+ck3+ck4, 4);
    }
    
    
    
    // Helper function for the small notes  
    @FXML
    public void showInfor(MouseEvent event) {
    	smallLog.setVisible(true);
    	smallLog.setText("Cost 5 Tickets ~");
    }

    @FXML
    public void disableInfor(MouseEvent event) {
    	smallLog.setVisible(false);
    	smallLog.setText("");
    }
    
    
    
    
    // check function for integer and string
    public int check(CheckBox box, int curr, int target) {
    	if(curr >= target) {
    		box.setDisable(false);
    		box.setSelected(true);
    		return 1;
    	} else {
    		System.out.println("not enough");
    		return 0;
    	}
    }
    
    public int stringCheck(CheckBox box, String buff, String target) {
    	if(buff.contains(target)) {
    		box.setDisable(false);
    		box.setSelected(true);
    		return 1;
    	} else {
    		System.out.println("not enough");
    		return 0;
    	}
    }
    
    
    
    // reset lock image to unlocked image
    public void resetImage(ImageView lock, int curr, int target) {
    	Image ig = new Image("/AchieveImages/unlock.png");
    	if(curr == target) {
    		lock.setImage(ig);
    	} else {
    		System.out.println("erquirement not meet");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ticketNum.setText(String.valueOf(Main.ticket));
		notes.setText(Main.buff);
		balance.setText(Integer.toString(Main.balance));
		ck1500.setDisable(true);
		ck2000.setDisable(true);
		ck3000.setDisable(true);
		ck5000.setDisable(true);
		tk3.setDisable(true);
		tk5.setDisable(true);
		tk10.setDisable(true);
		tk30.setDisable(true);
		rf1.setDisable(true);
		rf5.setDisable(true);
		rf10.setDisable(true);
		bzm.setDisable(true);
		gdm.setDisable(true);
		allM.setDisable(true);
	}
}
