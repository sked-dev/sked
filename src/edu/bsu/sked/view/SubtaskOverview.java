package edu.bsu.sked.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.bsu.sked.model.Subtask;
import edu.bsu.sked.model.Subtask.Difficulty;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class SubtaskOverview extends HBox implements Initializable {
	public enum Mode {
		VIEW, EDIT
	}

	@FXML private HBox root;
	@FXML private CheckBox completion;
	@FXML private TextField subtaskDescription;
	@FXML private ComboBox<Subtask.Difficulty> difficultiesDisplay;
	@FXML private Button minus;

	private Subtask subtask;
	private SubtaskListVBox parent;
	
	public SubtaskOverview(Subtask subtask, SubtaskListVBox parent) {
		super();
		this.subtask = subtask;
		this.parent = parent;
		configureFXMLHBox();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setValues();
		addSubtaskNameListener();
	}

	private void setValues() {
		difficultiesDisplay.setItems(FXCollections.observableArrayList(Difficulty.values()));
		difficultiesDisplay.setValue(subtask.getDifficulty());
		completion.setSelected(subtask.isComplete());
		subtaskDescription.setText(subtask.getDescription());
	}

	private void addSubtaskNameListener() {
		subtaskDescription.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				subtask.setDescription(newValue);
			}
			
		});
	}

	@FXML
	public void handleCompletionAction() {
		subtask.setCompletion(completion.isSelected());
	}
	
	@FXML
	public void handleDescriptionAction() {
		subtask.setDescription(subtaskDescription.getText());
	}
	
	@FXML
	public void handleDifficultyChange() {
		subtask.setDifficulty(difficultiesDisplay.getValue());
	}

	@FXML
	public void removeSubtask() {
		parent.remove(subtask);
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
