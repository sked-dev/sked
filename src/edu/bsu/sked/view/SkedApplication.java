package edu.bsu.sked.view;

import java.util.Map;

import edu.bsu.sked.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SkedApplication extends Application {
	
	private final NavigationPane primaryPane = NavigationPane.configure();
	private static SkedDataFile dataFile = new SkedDataFile();
	private static SkedDataContainer data = getData();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		checkParameters();
		getData();
		configure(primaryStage);
		primaryStage.show();
	}
	
	private void checkParameters() {
		Map<String, String> parameters = getParameters().getNamed();
		if (parameters.containsKey("file")) {
			dataFile = new SkedDataFile(parameters.get("file"));
		}
	}

	private static SkedDataContainer getData() {
		try {
			return new SkedDataFile().load();
		} catch (SkedDataReadFailedException e) {
			System.out.println("Could not load data");
			return SkedDataContainer.initialize();
		}
	}

	private void configure(Stage stage) {
		stage.setTitle("SKED");
		primaryPane.navigateToDefault();
		primaryPane.setPrefSize(650, 700);
		stage.setScene(new Scene(primaryPane));
	}

	public static SkedDataContainer getSkedData() {
		return data;
	}
	
	public static void saveSkedData() throws SkedDataWriteFailedException {
		dataFile.write(getSkedData());
	}

	
	
	

}
