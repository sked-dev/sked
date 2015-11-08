package edu.bsu.sked.view;

import java.util.List;

import edu.bsu.sked.model.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class AssignmentListGrid extends Pane {
	private final FlowPane grid = new FlowPane();
	private final List<Assignment> assignments;

	public AssignmentListGrid(List<Assignment> assignments) {
		super();
		this.assignments = assignments;
		
		grid.setVgap(15);
		grid.setHgap(15);
		this.getChildren().add(grid);
		configureGrid();
	}

	private void configureGrid() {
		for (Assignment assignment : assignments) {
			grid.getChildren().add(new AssignmentOverview(assignment));
		}
	}
	
	

}
