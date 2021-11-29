/* 
 * The MainMenuController has a button for each of the views.
 * It uses the handle method with a source object to know
 * which button is pressed. Then the view gets changed to 
 * whatever button the user pressed.
 */

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
	private Button playB; //play button.
	
	@FXML
	private Button storeB; //store button.
	
	@FXML
	private Button achievementsB; //achievements button.
	
	@FXML
	private Button settingsB; //settings button.
	
	@FXML
	private AnchorPane mainPane;
	
	public void handle(ActionEvent event) throws IOException{
		Object source = event.getSource(); //Used to know which button is pressed.
		
		if(source == playB) { //Takes you to play Blackjack.
			mainPane = FXMLLoader.load(getClass().getResource("/application/View/BlackJack.fxml"));
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		}else if(source == storeB) { //Takes you to the store page.
			mainPane = FXMLLoader.load(getClass().getResource("/application/View/Store.fxml"));
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		}else if(source == achievementsB) { //Takes you to the achievements page.
			URL url = new File("src/application/View/Achievement.fxml").toURI().toURL(); //Put the right FXML page name if needed.
			mainPane = FXMLLoader.load(url);
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		} else { //Takes you to the settings page.
			URL url = new File("src/application/View/Settings.fxml").toURI().toURL();
			mainPane = FXMLLoader.load(url);
			Scene scene = new Scene(mainPane);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			boolean isFullscreen = window.isFullScreen();
			window.setScene(scene);
			window.setFullScreen(isFullscreen);
			window.show();
		}
	} //End of handle method.
} //End of MainMenuController class.
