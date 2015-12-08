package edu.bsu.sked.view;

import java.io.File;
import java.io.IOException;

import edu.bsu.sked.model.SkedDataFile;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartupFileDialog extends Stage {

	private FileChooser fileChooser = new FileChooser();
	private File result;
	@FXML ImageView createButtonIcon;
	@FXML ImageView openButtonIcon;

	public static SkedDataFile getFile() {
		StartupFileDialog dialog = new StartupFileDialog();
		dialog.showAndWait();
		return new SkedDataFile(dialog.getPath());
	}

	public StartupFileDialog() {
		configureExtensions();
		configureScene();
	}

	private void configureExtensions() {
		fileChooser.getExtensionFilters().add(new ExtensionFilter("SKED JSON file (*.sked, *.json)", "*.sked", "*.json"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All files", "*"));
	}

	private void configureScene() {
		Parent root = getRootFromFxml();
		openButtonIcon.setImage(Assets.IC_FOLDER_OPEN);
		createButtonIcon.setImage(Assets.IC_NOTE_ADD);
		setScene(new Scene(root));
		sizeToScene();
		setResizable(false);
		setTitle("SKED");
		setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				onCancelButton();
			}
		});
	}

	private Parent getRootFromFxml() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StartupFileDialog.fxml"));
		loader.setController(this);
		try {
			return loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	public void onCreateButton() {
		fileChooser.setInitialFileName("New SKED file.sked");
		fileChooser.setTitle("Create SKED file");
		result = fileChooser.showSaveDialog(this);
		if (result != null) {
			this.close();
		}
	}

	@FXML
	public void onOpenButton() {
		fileChooser.setInitialFileName("*.sked");
		fileChooser.setTitle("Open SKED file");
		result = fileChooser.showOpenDialog(this);
		if (result != null) {
			this.close();
		}
	}
	
	public String getPath() {
		return result.getAbsolutePath();
	}
	
	@FXML
	private void onCancelButton() {
		System.exit(1);
	}
}
