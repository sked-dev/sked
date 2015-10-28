package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SkedApplication extends Application {
	
	private final NavigationPane primaryPane = NavigationPane.configure();
	private static final SkedDataFile dataFile = new SkedDataFile();
	private static SkedData data = getData();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		getData();
		configure(primaryStage);
		primaryStage.show();
	}

	private static SkedData getData() {
		try {
			return new SkedDataFile().load();
		} catch (SkedDataReadFailedException e) {
			System.out.println("Could not load data");
			return SkedData.initialize();
		}
	}

	private void configure(Stage stage) {
		stage.setTitle("SKED");
		primaryPane.navigateToDefault();
		primaryPane.setPrefSize(650, 700);
		stage.setScene(new Scene(primaryPane));
	}

	public static SkedData getSkedData() {
		return data;
	}
	
	public static void saveSkedData() throws SkedDataWriteFailedException {
		dataFile.write(getSkedData());
	}

	
	
	

}
