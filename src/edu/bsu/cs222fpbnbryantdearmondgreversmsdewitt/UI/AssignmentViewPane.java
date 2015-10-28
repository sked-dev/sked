package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.AssignmentList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

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
		AssignmentList assignments = SkedApplication.getSkedData().getAssignments();
		AssignmentListGrid list = new AssignmentListGrid(assignments);
		list.setMinSize(120, 120);
		list.setPrefSize(400, 300);
		list.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		pane.setCenter(list);
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
