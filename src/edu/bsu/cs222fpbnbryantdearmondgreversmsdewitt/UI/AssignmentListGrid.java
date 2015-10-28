package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class AssignmentListGrid extends Pane {
	private final FlowPane grid = new FlowPane();
	private final AssignmentList assignments;

	public AssignmentListGrid(AssignmentList assignments) {
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
