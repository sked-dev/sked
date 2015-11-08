package edu.bsu.sked.view;

import java.util.List;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AssignmentViewPane implements NavigationTarget {
	
	private final Image icon;
	private static final String ASSIGNMENTS = "Assignments";
	private final BorderPane pane = new BorderPane();
	
	public AssignmentViewPane() {
		super();
		icon = configureIcon();
		setUpPane();
	}
	
	private Image configureIcon() {
		try {
			return Assets.getImageFromAssetName("assignmentIcon.png");
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
		ScrollPane listWrapper = wrapInScrollPane(list);
		list.setMinSize(120, 120);
		list.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		pane.setCenter(listWrapper);
	}

	private ScrollPane wrapInScrollPane(Node node) {
		ScrollPane pane = new ScrollPane();
		pane.setContent(node);
		pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane.setFitToWidth(true);
		return pane;
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
	
	@Override
	public BorderPane getNode() {
		return pane;
	}

}
