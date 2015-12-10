package edu.bsu.sked.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.bsu.sked.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class AssignmentViewPane extends BorderPane implements NavigationTarget, Initializable {
	private static final String ASSIGNMENTS = "Assignments";
	
	@FXML private ScrollPane assignmentListScrollPane;
	@FXML private Button newAssignmentButton;
	@FXML private ToggleButton prioritizedFilterButton;
	private AssignmentListGrid list;
	private AssignmentListOrganizer assignmentOrganizer = new AssignmentListOrganizer(SkedApplication.getSkedData().getAssignments());
	
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
		list = new AssignmentListGrid(assignmentOrganizer.organize());
		assignmentListScrollPane.setContent(list);
	}
	
	@FXML
	private void handleNewAssignmentButtonAction() {
		AssignmentDetailStage.newAssignment();
		setUpAssignmentGrid();
	}
	
	@FXML
	private void sortByAddOrder() {
		assignmentOrganizer.setSortingMethod(AssignmentSortingMethod.NONE);
		refresh();
	}
	
	@FXML
	private void sortByName() {
		assignmentOrganizer.setSortingMethod(AssignmentSortingMethod.NAME);
		refresh();
	}
	
	@FXML
	private void sortByCourse() {
		assignmentOrganizer.setSortingMethod(AssignmentSortingMethod.COURSE);
		refresh();
	}
	
	@FXML
	private void sortByStartDate() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void sortByDueDate() {
		assignmentOrganizer.setSortingMethod(AssignmentSortingMethod.DUE_DATE);
		refresh();
	}
	
	@FXML
	private void sortByCompletion() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void sortByDifficulty() {
		// TODO Implement before merging to master
	}
	
	@FXML
	private void handlePrioritizationFilterToggled() {
		assignmentOrganizer.setFilterByCoursePriority(prioritizedFilterButton.isSelected());
		refresh();
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
