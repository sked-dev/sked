package edu.bsu.sked.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.bsu.sked.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CourseOverview extends VBox implements Initializable  {
	
	private boolean renaming = false;
	private final Course course;
	private List<Assignment> courseAssignments;
	private CourseViewPane parent;
	@FXML private Label courseNameLabel;
	@FXML private Label assignmentProgressLabel;
	@FXML private Label subtaskProgressLabel;
	@FXML private ToggleButton prioritizeButton;
	@FXML private HBox renameHBox;
	@FXML private Button renameButton;
	@FXML private TextField courseRenameField;
	@FXML private Label courseRenameErrorLabel;
	
	public CourseOverview(Course course, CourseViewPane parent) {
		super();
		this.parent = parent;
		this.course = course;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courseAssignments = getCourseAssignments();
		fillCourseFields();
	}

	private List<Assignment> getCourseAssignments() {
		return course.getAssignments();
	}

	private void fillCourseFields() {
		courseNameLabel.setText(course.getName());
		assignmentProgressLabel.setText(getAssignmentProgress());
		subtaskProgressLabel.setText(getSubtaskProgress());
		prioritizeButton.setSelected(course.isPrioritized());
		colorHeader();
	}
	
	private String getAssignmentProgress() {
		return courseAssignments.size() + " assignments.";
	}

	private String getSubtaskProgress() {
		return getCompleteSubtaskCount() + " of " + getSubtaskCount() + " subtasks completed";
	}

	private int getCompleteSubtaskCount() {
		int completeCount = 0;
		for (Assignment assignment : courseAssignments) {
			completeCount += getCompleteSubtaskCountForAssignment(assignment);
		}
		return completeCount;
	}

	private int getSubtaskCount() {
		int taskCount = 0;
		for (Assignment assignment : courseAssignments)
			taskCount += assignment.getSubtasks().size();
		return taskCount;
	}
	
	private int getCompleteSubtaskCountForAssignment(Assignment assignment) {
		int completeCount = 0;
		for (Subtask subtask : assignment.getSubtasks()) {
			completeCount += subtask.getCompletion() ? 1 : 0;
		}
		return completeCount;
	}

	@FXML
	private void handlePrioritizeButton() {
		course.setPrioritized(prioritizeButton.isSelected());
		colorHeader();
		save();
	}
	
	private void colorHeader() {
		if (course.isPrioritized()) {
			courseNameLabel.setTextFill(Color.RED);
		} else {
			courseNameLabel.setTextFill(Color.BLACK);
		}
	}
	
	@FXML
	private void handleRenameButton() {
		if (renaming) {
			renameHBox.setVisible(false);
			renameHBox.setManaged(false);
			courseNameLabel.setVisible(true);
			courseNameLabel.setManaged(true);
			renameButton.setText("Rename");
			renaming = false;
		} else {
			courseRenameField.setText(course.getName());
			renameHBox.setVisible(true);
			renameHBox.setManaged(true);
			courseNameLabel.setVisible(false);
			courseNameLabel.setManaged(false);
			renameButton.setText("Cancel");
			renaming = true;
		}
	}
	@FXML
	private void handleDeleteButton() {
		if (!confirmDelete()) {
			return;
		}
		SkedApplication.getSkedData().getCourses().remove(course);
		save();
		parent.refresh();
	}
	
	private boolean confirmDelete() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to delete " + course.getName() + "?\n\nThis cannot be undone.");
		alert.setTitle("Delete course");
		alert.showAndWait();
		return alert.getResult() == ButtonType.OK;
	}

	@FXML
	private void handleSaveName() {
		if (courseRenameField.getText().isEmpty()) {
			courseRenameErrorLabel.setText("Assignment name cannot be empty.");
			courseRenameErrorLabel.setVisible(true);
			return;
		}
		course.setName(courseRenameField.getText());
		save();
		parent.refresh();
	}
	
	private void save() {
		try {
			SkedApplication.saveSkedData();
		} catch (SkedDataWriteFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
