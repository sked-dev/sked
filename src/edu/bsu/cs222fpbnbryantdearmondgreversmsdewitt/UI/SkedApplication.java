package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SkedApplication extends Application {
	
	private final NavigationManager primaryPane = new NavigationManager();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryPane.navigateToDefault();
		configure(primaryStage);
		primaryStage.show();
	}

	private void configure(Stage stage) {
		stage.setTitle("SKED");
		stage.setScene(new Scene(primaryPane));
	}

	
	
	

}
