package edu.bsu.sked.view;

import edu.bsu.sked.model.*;
import edu.bsu.sked.view.AssignmentDetailController.Mode;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AssignmentDetailStage extends Stage {
	
	private Parent pane;
	private AssignmentDetailController controller;
	
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
		this.controller = controller;
		pane = getFXMLPane(controller);
		setScene(new Scene(pane));
		controller.setParent(this);
		configureStage();
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
	
	private void configureStage() {
		setTitle("Assignment details");
		setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent windowEvent) {
				controller.handleClose(windowEvent);
			}
			
		});
	}
	
}
