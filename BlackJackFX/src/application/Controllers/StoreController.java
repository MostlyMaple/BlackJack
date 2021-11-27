package application.Controllers;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



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
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	//confirmBuyText.setText("Click on image to buy!");
    	totalBalanceText.setText("Current balance " + Main.balance);
		
	}

    @FXML
    void imageClicked(MouseEvent event)  {
    	if(event.getPickResult().getIntersectedNode().getId().compareTo("tree")==0) {
    		
    		confirmBuyText.setText("Are you sure you want to buy the tree");
    
    		//if confirm button clicked
    		confirmBuyButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(Main.balance - 200 < 0) {
						confirmBuyText.setText("You're not that rich :(");
					}
					else {
						Main.balance = Main.balance - 200;
						totalBalanceText.setText("New Balance:" + Main.balance);
					}
				}
    			
    		});
    		//if no button clicked
    		
    		
    	}
    	else if(event.getPickResult().getIntersectedNode().getId().compareTo("dog")==0) {
    		confirmBuyText.setText("Are you sure you want to buy the dog");
    		
    		//if confirm button clicked
    		confirmBuyButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(Main.balance - 400 < 0) {
						confirmBuyText.setText("You're not that rich :(");
					}
					else {
						Main.balance = Main.balance - 400;
						totalBalanceText.setText("New Balance:" + Main.balance);
					}
				}
    			
    		});
    			
    		
    		
    		//if no button clicked
    		//confirmBuyText.setText("Click on image to buy!");
    		
    	}
    	else if(event.getPickResult().getIntersectedNode().getId().compareTo("monkey")==0) {
    		confirmBuyText.setText("Are you sure you want to buy the monkey");
    		
    		//if confirm button clicked
    		confirmBuyButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(Main.balance - 500 < 0) {
						confirmBuyText.setText("You're not that rich :(");
					}
					else {
						Main.balance = Main.balance - 500;
						totalBalanceText.setText("New Balance:" + Main.balance);
					}
				}
    			
    		});
    		//if no button clicked
    		
    		
    		
    	}
    	else if(event.getPickResult().getIntersectedNode().getId().compareTo("food")==0) {
    		confirmBuyText.setText("Are you sure you want to buy the food");
    		
    		//if confirm button clicked
    		confirmBuyButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(Main.balance - 100 < 0) {
						confirmBuyText.setText("You're not that rich :(");
					}
					else {
						Main.balance = Main.balance - 100;
						totalBalanceText.setText("New Balance:" + Main.balance);
					}
		    		
				}
    			
    		});
    		
    		
    	}
    	
    }
   /* @FXML
    void mainMenuHandle(ActionEvent event) {
    	URL url = new File("src/application.View/MainMenu.fxml");
    	AnchorPane mainPane = FXMLLoader.load(url);
    	Scene scene = new Scene(mainPane);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();

    }*/



    @FXML
    void changeMindHandle(ActionEvent event) {

    }




	



}

