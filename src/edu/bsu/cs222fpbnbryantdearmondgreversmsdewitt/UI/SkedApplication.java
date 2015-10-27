package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SkedApplication extends Application {
	
	private final NavigationPane primaryPane = NavigationPane.configure();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		configure(primaryStage);
		primaryStage.show();
	}

	private void configure(Stage stage) {
		stage.setTitle("SKED");
		primaryPane.navigateToDefault();
		stage.setScene(new Scene(primaryPane));
	}

	
	
	

}
