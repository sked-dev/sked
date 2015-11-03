package edu.bsu.sked.view;

import java.util.List;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AssignmentViewPane extends NavigationTargetPane {
	
	private final Image icon;
	private static final String ASSIGNMENTS = "Assignments";
	private final BorderPane pane = new BorderPane();
	
	public AssignmentViewPane() {
		super();
		icon = configureIcon();
		this.getChildren().add(pane);
		setUpPane();
	}
	
	private Image configureIcon() {
		try {
			return getImageFromAssetName("assignmentIcon.png");
		} catch (Exception e) {
			return null; 
		}
	}

	private void setUpPane() {
		setUpAssignmentGrid();
		
		addActionButtons();
	}

	private void setUpAssignmentGrid() {
		List<Assignment> assignments = SkedApplication.getSkedData().getAssignments();
		AssignmentListGrid list = new AssignmentListGrid(assignments);
		list.setMinSize(120, 120);
		list.setPrefSize(550, 300);
		list.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		pane.setCenter(list);
	}

	private void addActionButtons() {
		HBox actionButtons = new HBox();
		Button newAssignmentButton = new Button("Add assignment...");
		newAssignmentButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				AssignmentDetailEditorStage.newAssignment();
				setUpAssignmentGrid();
			}
		});
		actionButtons.getChildren().add(newAssignmentButton);
		pane.setBottom(actionButtons);
	}

	@Override
	public Image getIcon() {
		return icon;
	}

	@Override
	public String getLabel() {
		return ASSIGNMENTS;
	}

}
