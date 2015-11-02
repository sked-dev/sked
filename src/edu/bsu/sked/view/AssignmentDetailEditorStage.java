package edu.bsu.sked.view;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AssignmentDetailEditorStage extends Stage {
	
	private Assignment assignment;
	private TextField nameTextField = new TextField();
	private DatePicker startDateField = new DatePicker(LocalDate.now());
	private DatePicker dueDateField = new DatePicker(LocalDate.now().plusDays(7));
	protected String titleText = "Edit assignment";

	public static void newAssignment() {
		AssignmentDetailEditorStage stage = new AssignmentDetailEditorStage(null);
		stage.titleText = "New assignment";
		stage.showAndWait();
	}
	
	public AssignmentDetailEditorStage(Assignment assignment) {
		this.assignment = assignment;
		fillValues();
		configure();
	}
	
	private void fillValues() {
		if (assignment == null) return;
		nameTextField.setText(assignment.getName());
		startDateField.setValue(assignment.getStartDate());
		dueDateField.setValue(assignment.getDueDate());
	}
	
	private void configure() {
		GridPane contentGrid = new GridPane();
		addContent(contentGrid);
		setTitleBar();
		setScene(new Scene(contentGrid));
	}

	private void addContent(GridPane grid) {
		grid.add(getTitleLabel(), 0, 0, 4, 1);
		grid.add(new Label("Name:"), 0, 1);
		grid.add(nameTextField, 1, 1);
		grid.add(new Label("Start date:"), 0, 2);
		grid.add(startDateField, 1, 2);
		grid.add(new Label("Due date:"), 2, 2);
		grid.add(dueDateField, 3, 2);
		grid.add(getSaveButton(), 3, 3);
		grid.add(getCancelButton(), 2, 3);
	}
	
	private void setTitleBar() {
		String title = titleText;
		if (!nameTextField.getText().isEmpty()) {
			title += " - " + nameTextField.getText();
		}
		setTitle(title);
	}
	
	private Label getTitleLabel() {
		Label l = new Label(titleText);
		l.setFont(Font.font(24));
		return l;
	}
	
	private Button getSaveButton() {
		Button saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				save();
			}
		});
		return saveButton;
	}
	
	private Button getCancelButton() {
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});
		return cancelButton;
	}
	
	private void save() {
		ArrayList<String> invalidFields = getInvalidFields();
		if (invalidFields.size() > 0) {
			showInvalidFieldsAlert(invalidFields);
		} else {
			write();
		}
		
	}

	private ArrayList<String> getInvalidFields() {
		ArrayList<String> invalidFields = new ArrayList<>();
		if (nameTextField.getText().isEmpty()) {
			invalidFields.add("assignment name");
		}
		if (startDateField.getValue().compareTo(dueDateField.getValue()) > 1) {
			invalidFields.add("start and due date");
		}
		return invalidFields;
	}

	private void showInvalidFieldsAlert(ArrayList<String> invalidFields) {
		Alert fieldsAlert = new Alert(AlertType.WARNING);
		fieldsAlert.setTitle("Invalid entries");
		fieldsAlert.setHeaderText(null);
		if (invalidFields.size() == 1) {
			fieldsAlert.setContentText("Please check the value in " + invalidFields.get(0));
		} else {
			String text = "Please check the values in ";
			for (int i = 0; i < invalidFields.size() - 1; i++) {
				text += invalidFields.get(i) + ", ";
			}
			text += "and " + invalidFields.get(invalidFields.size() - 1) + ".";
			fieldsAlert.setContentText(text);
		}
		fieldsAlert.showAndWait();
	}
	
	private void write() {
		if (assignment == null) {
			Assignment newAssignment = getAssignmentFromData();
			SkedApplication.getSkedData().getAssignments().add(newAssignment);
		} else {
			modifyAssignment();
		}
		try {
			SkedApplication.saveSkedData();
		} catch (SkedDataWriteFailedException e) {
			Alert writeAlert = new Alert(AlertType.ERROR);
			writeAlert.setTitle("Write error");
			writeAlert.setContentText("There was an error saving the data file.  Please check the permissions to write to sked.json in your home folder.");
			writeAlert.showAndWait();
			return;
		}
		close();
	}

	private Assignment getAssignmentFromData() {
		return Assignment.Builder//
				.withName(nameTextField.getText())//
				.andStartDate(startDateField.getValue())//
				.andDueDate(dueDateField.getValue())//
				.build();
	}

	private void modifyAssignment() {
		assignment.setName(nameTextField.getText());
		assignment.setDueDate(dueDateField.getValue());
		assignment.setStartDate(startDateField.getValue());
	}
	
}
