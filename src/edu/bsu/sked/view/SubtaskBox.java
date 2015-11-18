package edu.bsu.sked.view;

import java.util.ArrayList;
import java.util.List;

import edu.bsu.sked.model.Assignment;
import edu.bsu.sked.model.Subtask;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class SubtaskBox extends ScrollPane {

	private List<Subtask> subtasks;
	private Assignment assignment;
	private VBox vbox = new VBox();
	private ScrollPane pane = new ScrollPane();
	
	public SubtaskBox(Assignment assignment) {
		super();
		this.assignment = assignment;
		configurePane();
	}

	private void configurePane() {
		configureVBox();
		pane.setContent(vbox);
	}

	private void configureVBox() {
		vbox.setMaxHeight(Double.MAX_VALUE);
		SubtaskOverview view = new SubtaskOverview();
		vbox.getChildren().add(view);
	}

	public ArrayList<Subtask> getSubtasks() {
		return (ArrayList<Subtask>) subtasks;
	}

	public void setEditable(boolean editable) {

	}

}
