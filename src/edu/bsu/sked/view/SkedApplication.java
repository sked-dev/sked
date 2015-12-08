package edu.bsu.sked.view;

import java.util.Map;

import edu.bsu.sked.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SkedApplication extends Application {
	
	private NavigationPane primaryPane;
	private static SkedDataFile dataFile;
	private static SkedDataContainer data;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		configureDataFile();
		getData();
		configure(primaryStage);
		primaryStage.show();
	}
	
	private void configureDataFile() {
		Map<String, String> parameters = getParameters().getNamed();
		if (parameters.containsKey("file")) {
			dataFile = new SkedDataFile(parameters.get("file"));
		} else {
			dataFile = StartupFileDialog.getFile();
		}
	}

	private static void getData() {
		try {
			data = dataFile.load();
		} catch (SkedDataReadFailedException e) {
			/*TODO*/System.out.println("Could not load data");
			data = SkedDataContainer.initialize();
		}
	}

	private void configure(Stage stage) {
		stage.setTitle("SKED");
		primaryPane = NavigationPane.configure();
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
