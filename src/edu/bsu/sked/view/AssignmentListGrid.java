package edu.bsu.sked.view;

import java.util.List;

import edu.bsu.sked.model.*;
import javafx.scene.layout.FlowPane;

public class AssignmentListGrid extends FlowPane {
	private final List<Assignment> assignments;

	public AssignmentListGrid(List<Assignment> assignments) {
		super();
		this.assignments = assignments;
		setVgap(15);
		setHgap(15);
		configureGrid();
	}

	private void configureGrid() {
		for (Assignment assignment : assignments) {
			getChildren().add(new AssignmentOverview(assignment));
		}
	}
	
	

}
