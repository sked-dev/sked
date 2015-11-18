package edu.bsu.sked.view;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AssignmentOverview extends GridPane {
	
	private Assignment assignment;
	private Label assignmentName = new Label();
	private Label assignmentDueDate = new Label();
	private ProgressBar progressBar = new ProgressBar();
	
	public AssignmentOverview(Assignment assignment) {
		super();
		this.assignment = assignment;
		this.setStyle("-fx-background-color: white; -fx-border-color: black;");
		this.setPadding(new Insets(15));
		getValues();
		this.add(assignmentName, 0, 0, 1, 1);
		this.add(assignmentDueDate, 0, 1);
		this.add(getEditHyperlink(), 0, 2);
		this.setPrefWidth(300);
		this.add(progressBar.buildProgressBar(),0,3);
	}
	


	private void getValues() {
		assignmentName.setText(assignment.getName());
		assignmentDueDate.setText(assignment.getRelativeDueDate());
	}

	private Hyperlink getEditHyperlink() {
		Hyperlink editLink = new Hyperlink("Edit");
		editLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AssignmentDetailStage.edit(assignment);
				getViewPane().refresh();
			}
			
		});
		return editLink;
		
	}

	private AssignmentViewPane getViewPane() {
		return (AssignmentViewPane) this.getParent().getParent().getParent().getParent().getParent(); //TODO: Can this be cleaned up?
	}
	
}
