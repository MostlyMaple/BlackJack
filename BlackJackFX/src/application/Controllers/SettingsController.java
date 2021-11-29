package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingsController implements Initializable{

    @FXML // fx:id="btn_fullscreen"
    private CheckBox btn_fullscreen; // Value injected by FXMLLoader
    
    @FXML // fx:id="choiceBox_Resolution"
    private ChoiceBox<String> choiceBox_Resolution; // Value injected by FXMLLoader

    @FXML // fx:id="btn_mainMenu"
    private Button btn_mainMenu; // Value injected by FXMLLoader

    @FXML
    private AnchorPane settingsPane;
    
    @FXML
    void openMainMenu(ActionEvent event) throws IOException {
  	  settingsPane = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));// pane you are GOING TO
      Scene scene = new Scene(settingsPane);// pane you are GOING TO show
      Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
      boolean isFullscreen = window.isFullScreen();
      window.setScene(scene);
      window.setFullScreen(isFullscreen);
      window.show();
    }
    
    /* 
     * Makes the window fullscreen ticking the "fullscreen" box.
     */
    @FXML
    void toggleFullscreen(ActionEvent event) {
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setFullScreen(btn_fullscreen.isSelected());
    }
    
    /*
     * Is run when the scene starts up.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		//Populates the choicebox with different resolutions.
		choiceBox_Resolution.getItems().removeAll(choiceBox_Resolution.getItems());
		choiceBox_Resolution.getItems().addAll("1920×1080","1600×1280","1600×1200", "1680×1050", "1024×768", "960×540", "800×600");
		
		// Ensures that when a choice is selected, the window is updated to the correct dimensions.
		choiceBox_Resolution.setOnAction((event)-> {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	switch(choiceBox_Resolution.getValue().toString()) {
	    	case "1920×1080":
	    		window.setWidth(1920);
	    		window.setHeight(1080);
	    		break;
	    	case "1600×1280":
	    		window.setWidth(1600);
	    		window.setHeight(1280);
				break;
	    	case "1600×1200":
	    		window.setWidth(1600);
	    		window.setHeight(1200);
				break;
	    	case "1680×1050":
	    		window.setWidth(1680);
	    		window.setHeight(1050);
	    		break;
	    	case "1024×768":
	    		window.setWidth(1024);
	    		window.setHeight(768);
	    		break;
	    	case "960×540":
	    		window.setWidth(960);
	    		window.setHeight(540);
	    		break;
	    	case "800×600":
	    		window.setWidth(800);
	    		window.setHeight(600);
	    		break;
	    	}
	    	
		  	
		});
	}

}
