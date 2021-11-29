package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Main is a Java class that starts the application and holds some global variables.
 * 
 * UTSA CS 3443 - Final Project
 * Fall 2021
 */


public class Main extends Application {
	
	
	public static int balance = 1000;
	public static int ticket = 5;
	public static String buff = "";
	public static int raffleCounter = 0;
	
	
	/* Method Name: start
	 * Return type: void
	 * Parameters: None
	 * Description: Declares an AnchorPane and loads the .fxml file. Then Declares a scene and loads it onto a stage.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/View/MainMenu.fxml"));
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Method Name: Main
	 * Return type: void
	 * Parameters: None
	 * Description: Launches the application
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
