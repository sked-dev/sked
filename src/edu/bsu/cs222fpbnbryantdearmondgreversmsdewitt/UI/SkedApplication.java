package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class SkedApplication extends Application {
	
	private final SkedData data = SkedData.loadSavedUser();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		configure(primaryStage);
	}

	private void configure(Stage stage) {
		stage.setTitle("SKED");
	}

}
