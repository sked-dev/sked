package edu.bsu.sked.view;

import edu.bsu.sked.model.*;
import edu.bsu.sked.view.AssignmentDetailController.Mode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AssignmentDetailStage extends Stage {
	
	
	public static void newAssignment() {
		AssignmentDetailController controller = new AssignmentDetailController(null, Mode.CREATE);
		AssignmentDetailStage stage = new AssignmentDetailStage(controller);
		stage.showAndWait();
	}
	
	public static void edit(Assignment assignment) {
		AssignmentDetailController controller = new AssignmentDetailController(assignment, Mode.EDIT);
		AssignmentDetailStage stage = new AssignmentDetailStage(controller);
		stage.showAndWait();
	}
	
	private AssignmentDetailStage(AssignmentDetailController controller) {
		Parent pane = getFXMLPane(controller);
		setScene(new Scene(pane));
	}
	
	private Parent getFXMLPane(AssignmentDetailController controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignmentDetail.fxml"));
			loader.setController(controller);
			return loader.load();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
