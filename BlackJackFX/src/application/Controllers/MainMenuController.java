package application.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController {
	@FXML
	private Button playB;
	
	@FXML
	private Button storeB;
	
	@FXML
	private Button achievementsB;
	
	@FXML
	private Button settingsB;
	
	@FXML
	private AnchorPane mainPane;
	
	public void handle(ActionEvent event) throws IOException{
		Object source = event.getSource();
		
		if(source == playB) { //Takes you to play Blackjack.
			URL url = new File("src/application/View/BlackJack.fxml").toURI().toURL();
			mainPane = FXMLLoader.load(url);
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		}
		
		if(source == storeB) { //Takes you to the store page.
			URL url = new File("src/application/View/Store.fxml").toURI().toURL(); //Put the right FXML page name if needed.
			mainPane = FXMLLoader.load(url);
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		}
		
		if(source == achievementsB) { //Takes you to the achievements page.
			URL url = new File("src/application/View/Achievements.fxml").toURI().toURL(); //Put the right FXML page name if needed.
			mainPane = FXMLLoader.load(url);
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		}
		
		else { //Takes you to the settings page.
			URL url = new File("src/application/View/Settings.fxml").toURI().toURL();
			mainPane = FXMLLoader.load(url);
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		}
	}
}
