package edu.bsu.sked.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.bsu.sked.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class AssignmentViewPane extends BorderPane implements NavigationTarget, Initializable {
	private static final String ASSIGNMENTS = "Assignments";
	
	@FXML private ScrollPane assignmentListScrollPane;
	@FXML private Button newAssignmentButton;
	
	public AssignmentViewPane() {
		super();
		setUpPane();
	}
	
	public void refresh(){
		setUpAssignmentGrid();
	}

	private void setUpPane() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignmentViewPane.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL resource, ResourceBundle resourceBundle) {
		refresh();
	}	

	private void setUpAssignmentGrid() {
		List<Assignment> assignments = SkedApplication.getSkedData().getAssignments();
		AssignmentListGrid list = new AssignmentListGrid(assignments);
		assignmentListScrollPane.setContent(list);
	}
	
	@FXML
	private void handleNewAssignmentButtonAction() {
		AssignmentDetailStage.newAssignment();
		setUpAssignmentGrid();
	}
	
	@FXML
	private void sortByAddOrder() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void sortByCourse() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void sortByStartDate() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void sortByDueDate() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void sortByDifficulty() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void handlePrioritizationFilterToggled() {
		// TODO Implement before merging to master
	}

	@Override
	public Image getIcon() {
		return Assets.IC_ASSIGNMENT;
	}

	@Override
	public String getLabel() {
		return ASSIGNMENTS;
	}
	
	@Override
	public BorderPane getNode() {
		return this;
	}
}
