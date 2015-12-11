package edu.bsu.sked.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AssignmentOverview extends GridPane implements Initializable{
	
	@FXML private HBox root;
	@FXML private Label assignmentNameLabel;
	@FXML private Label subtaskLeftLabel;
	@FXML private Label courseNameLabel;
	@FXML private Label timeLeftLabel;
	@FXML private DropShadow dropShadow;
	
	private Assignment assignment;
	private ProgressBar progressBar;
	
	public AssignmentOverview(Assignment assignment) {
		super();
		progressBar = new ProgressBar(assignment);
		this.assignment = assignment;
		this.add(progressBar.getPaneasNode(), 1, 5);
		configureFXMLHBox();
	}
	
	@FXML private void mouseEntered(){
		Scene scene = this.getScene();
		Cursor cursor = scene.getCursor();
		scene.setCursor(cursor.HAND);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setRadius(12);
	}

	@FXML private void mouseExited(){
		Scene scene = this.getScene();
		Cursor cursor = scene.getCursor();
		scene.setCursor(cursor.DEFAULT);
		dropShadow.setOffsetX(0.0);
		dropShadow.setOffsetY(0.0);
		dropShadow.setRadius(10);
	}
	
	@FXML private void mouseClicked(){
		AssignmentDetailStage.edit(assignment);
		getViewPane().refresh();
	}

	private void getValues() {
		assignmentNameLabel.setText(assignment.getName());
		timeLeftLabel.setText(assignment.getRelativeDueDate());
		subtaskLeftLabel.setText(assignment.getSubtaskCompletionDescription());
		courseNameLabel.setText(SkedApplication.getSkedData().getCourse(assignment.getCourseIndex()).getName());
	}
	
	private void configureFXMLHBox() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("assignmentOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private AssignmentViewPane getViewPane() {
		return (AssignmentViewPane) this.getParent().getParent().getParent().getParent().getParent(); //TODO: Can this be cleaned up?
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getValues();
	}
	
}
