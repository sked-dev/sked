package edu.bsu.sked.view;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


import edu.bsu.sked.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AssignmentDetailController implements Initializable {

	public enum Mode {
		VIEW, EDIT, CREATE
	}

	@FXML private Label stageTitle;
	@FXML private Label assignmentNameTitle;
	@FXML private TextField assignmentNameField;
	@FXML private DatePicker assignmentStartDateField;
	@FXML private DatePicker assignmentDueDateField;
	@FXML private ScrollPane subtasks;
	@FXML private Button closeButton;
	@FXML private Button saveButton;
	@FXML private Button revertButton;
	@FXML private Button editButton;
	@FXML private Button deleteButton;
	@FXML private Separator deleteSeparator;
	@FXML private ComboBox<Course> coursesComboBox;
	@FXML private Button addButton;
	private SubtaskListVBox subtaskList;
	private Assignment assignment;
	private Mode mode;
	private boolean saved = true;
	private Stage parent;

	public AssignmentDetailController(Assignment assignment, Mode mode) {
		this.assignment = assignment;
		this.mode = mode;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillContent();
		configureMode();
	}

	private void fillContent() {
		setTitle();
		fillCourseComboBox();
		fillAssignmentDetails();
		setSubtaskBox();
	}

	private void fillCourseComboBox() {
		Collection<Course> courses = SkedApplication.getSkedData().getCourses();
		coursesComboBox.setItems(FXCollections.observableArrayList(courses));
	}

	private void setSubtaskBox() {
		if (assignment != null) {
			subtaskList = new SubtaskListVBox(assignment.getSubtasks());
			subtasks.setContent(subtaskList);
		} else {
			subtaskList = SubtaskListVBox.create();
			subtasks.setContent(subtaskList);
		}
	}

	private void configureMode() {
		if (mode == Mode.VIEW) {
			makeEditable(false);
			configureViewButtons();
		} else if (mode == Mode.EDIT) {
			makeEditable(true);
			configureEditButtons();
		} else {
			makeEditable(true);
			configureCreateButtons();
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
			assignmentNameTitle.setText("");
			subtaskList = SubtaskListVBox.create();
			return;
		}
		assignmentNameTitle.setText(assignment.getName());
		assignmentNameField.setText(assignment.getName());
		assignmentStartDateField.setValue(assignment.getStartDate());
		assignmentDueDateField.setValue(assignment.getDueDate());
		coursesComboBox.setValue(assignment.getCourse());
		subtaskList = new SubtaskListVBox(assignment.getSubtasks());
	}

	private void makeEditable(boolean editable) {
		assignmentNameField.setEditable(editable);
		assignmentStartDateField.setEditable(editable);
		assignmentDueDateField.setEditable(editable);
		//subtasks.setEditable(editable);
	}

	private void configureViewButtons() {
		saveButton.setManaged(false);
		revertButton.setManaged(false);
		editButton.setManaged(true);
		deleteButton.setManaged(true);
		deleteSeparator.setManaged(true);
		addButton.setManaged(true);
		closeButton.setText("Close");
	}

	private void configureEditButtons() {
		saveButton.setManaged(true);
		revertButton.setManaged(true);
		editButton.setManaged(false);
		deleteButton.setManaged(true);
		deleteSeparator.setManaged(true);
		addButton.setManaged(true);
		closeButton.setText("Close");
	}

	private void configureCreateButtons() {
		saveButton.setManaged(true);
		revertButton.setManaged(false);
		editButton.setManaged(false);
		deleteButton.setManaged(false);
		deleteSeparator.setManaged(false);
		addButton.setManaged(true);
		closeButton.setText("Cancel");
	}

	public void setMode(Mode mode) {
		this.mode = mode;
		configureMode();
	}
	
	@FXML
	public void addSubtask() {
		subtaskList.addBlankSubtask();
	}

	@FXML
	public void close() {
		parent.fireEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void handleClose(WindowEvent windowEvent) {
		if (!(saved || confirm("You have unsaved changes to this assignment.  " + 
	"Closing this window will delete this changes.\n\nAre you sure you want to do this?"))) {
			windowEvent.consume();
		}
	}

	private boolean confirm(String prompt) {
		Alert unsavedAlert = new Alert(AlertType.CONFIRMATION);
		unsavedAlert.setTitle("Unsaved changes");
		unsavedAlert.setContentText(prompt);
		unsavedAlert.showAndWait();
		return unsavedAlert.getResult() == ButtonType.OK;
	}
	
	private void alert(String prompt) {
		Alert unsavedAlert = new Alert(AlertType.WARNING);
		unsavedAlert.setContentText(prompt);
		unsavedAlert.showAndWait();
	}

	@FXML
	public void revert() {
		/*if (confirm("This will delete all of your changes to this assignment.  Are you sure?")) {
			setMode(Mode.VIEW);
			fillContent();
		}*/
	}

	@FXML
	public void save() {
		boolean valid = validate();
		if (!valid) {
			return;
		} else if (assignment == null) {
			assignment = Assignment.Builder//
					.withName(assignmentNameField.getText())//
					.andCourse(coursesComboBox.getValue())//
					.andStartDate(assignmentStartDateField.getValue())//
					.andDueDate(assignmentDueDateField.getValue())//
					.andSubtasks(subtaskList.getSubtasks())//
					.build();
			SkedApplication.getSkedData().getAssignments().add(assignment);
		} else {
			assignment.setName(assignmentNameField.getText());
			assignment.setStartDate(assignmentStartDateField.getValue());
			assignment.setDueDate(assignmentDueDateField.getValue());
			assignment.setCourse(coursesComboBox.getValue());
			assignment.setSubtasks(subtaskList.getSubtasks());
		}
		try {
			SkedApplication.saveSkedData();
		} catch (SkedDataWriteFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}

	private boolean validate() {
		if (assignmentNameField.getText().isEmpty()) {
			alert("You must enter an assignment name");
			return false;
		}
		if (assignmentDueDateField.getValue() == null || assignmentStartDateField.getValue() == null) {
			alert("You must enter a start date and due date.");
			return false;
		}
		if (assignmentDueDateField.getValue().compareTo(assignmentStartDateField.getValue()) < 0) {
			alert("Your due date must come after your start date.");
			return false;
		}
		if (!(subtaskList.isValid())) {
			alert("Your subtasks are invalid.");
			return false;
		}
		return true;
	}

	@FXML
	public void delete() {
		if (!confirm("Are you sure you want to delete this assignment and all of its subtasks?\n\nThis cannot be undone."))
			return;
		SkedApplication.getSkedData().getAssignments().remove(assignment);
		try {
			SkedApplication.saveSkedData();
			close();
		} catch (SkedDataWriteFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setParent(Stage parent) {
		this.parent = parent;
	}

}