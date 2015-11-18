package edu.bsu.sked.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.bsu.sked.model.Subtask;
import edu.bsu.sked.model.Subtask.Difficulty;
import javafx.scene.layout.HBox;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class SubtaskOverview extends HBox implements Initializable {
	public enum Mode {
		VIEW, EDIT
	}

	@FXML HBox root;
	@FXML CheckBox completion;
	@FXML TextField subtaskDescription;
	@FXML ComboBox<Subtask.Difficulty> difficultiesDisplay;
	@FXML Button plus;
	@FXML Button minus;

	private Subtask subtask;
	private Parent hbox;

	public SubtaskOverview(Subtask subtask) {
		super();
		this.subtask = subtask;
		configureFXMLHBox();
	}

	public SubtaskOverview() {
		super();
		subtask = Subtask.emptySubtask();
		configureFXMLHBox();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		difficultiesDisplay.getItems().clear();
		difficultiesDisplay.getItems().addAll(Difficulty.values());
		difficultiesDisplay.getSelectionModel().select(Difficulty.NORMAL);
	}

	@FXML
	public void selectDificulty() {
		Difficulty selection = difficultiesDisplay.getSelectionModel().getSelectedItem();
		subtask.setDifficulty(selection);
	}

	@FXML
	public void removeSubtask() {

	}

	@FXML
	public void addSubtask() {
	}

	@FXML
	public void fire() {
		completion.fire();
	}

	private void configureFXMLHBox() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SubtaskDetail.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
