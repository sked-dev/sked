package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import java.net.URL;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SkedApplication extends Application {
	
	private final SkedDataFile file = new SkedDataFile();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		configure(primaryStage);
	}

	private void configure(Stage stage) throws Exception {
		stage.setTitle("SKED");
		Scene scene = getMainScene();
		stage.setScene(scene);
		stage.show();
		
	}

	private Scene getMainScene() throws Exception {
		Pane pane = (Pane) FXMLLoader.load(getClass().getResource("fxml_application_frame.fxml"));
		return new Scene(pane);
	}

}
