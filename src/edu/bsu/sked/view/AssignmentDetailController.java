package edu.bsu.sked.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.bsu.sked.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AssignmentDetailController implements Initializable {
	
	public enum Mode {
		VIEW, EDIT, CREATE
	}
	
	@FXML private Label stageTitle;
	@FXML private Label assignmentNameTitle;
	@FXML private TextField assignmentNameField;
	@FXML private DatePicker assignmentStartDateField;
	@FXML private DatePicker assignmentDueDateField;
	private Assignment assignment;
	private Mode mode;
	
	public AssignmentDetailController(Assignment assignment, Mode mode) {
		this.assignment = assignment;
		this.mode = mode;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillContent();
	}
	
	private void fillContent() {
		setTitle();
		if (assignment != null) {
			fillAssignmentDetails();
		}
	}

	private void setTitle() {
		if (mode == Mode.CREATE) {
			stageTitle.setText("Create assignment");
		} else if (mode == Mode.EDIT) {
			stageTitle.setText("Edit assignment");
		} else {
			stageTitle.setText("View assignment");
		}
	}

	private void fillAssignmentDetails() {
		if (assignment == null) {
			return;
		}
		assignmentNameTitle.setText(assignment.getName());
		assignmentNameField.setText(assignment.getName());
		assignmentStartDateField.setValue(assignment.getStartDate());
		assignmentDueDateField.setValue(assignment.getDueDate());
	}
	
}
