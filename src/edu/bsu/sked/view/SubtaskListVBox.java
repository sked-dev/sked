package edu.bsu.sked.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SubtaskListVBox extends VBox {
	
	private final List<Subtask> subtasks;
	private List<SubtaskOverview> overviews = new ArrayList<>();
	
	public static SubtaskListVBox create() {
		return new SubtaskListVBox(new ArrayList<Subtask>());
	}

	public SubtaskListVBox(List<Subtask> subtasks) {
		this.subtasks = new LinkedList<Subtask>(subtasks);
		setLayout();
		refresh();
	}
	
	private void setLayout() {
		this.setSpacing(5);
		this.setAlignment(Pos.TOP_CENTER);
		this.setFillWidth(true);
		this.setPrefWidth(USE_COMPUTED_SIZE);
		this.setMaxWidth(Double.MAX_VALUE);
	}
	
	public void refresh() {
		if (subtasks.size() < 1) {
			addCreateButton();
		} else {
			fillSubtasks();
		}
	}

	private void addCreateButton() {
		getChildren().clear();
		Button button = new Button("Create subtasks");
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getChildren().remove(button);
				addBlankSubtask();
			}
		});
		getChildren().add(button);
	}

	private void fillSubtasks() {
		getChildren().clear();
		overviews.clear();
		for (Subtask subtask : subtasks) {
			overviews.add(new SubtaskOverview(subtask, this));
		}
		getChildren().addAll(overviews);
	}
	
	public void addBlankSubtask() {
		subtasks.add(Subtask.emptySubtask());
		refresh();
	}
	
	public void remove(Subtask subtask) {
		subtasks.remove(subtask);
		refresh();
	}
	
	public boolean isValid() {
		for (Subtask subtask : subtasks) 
			if (subtask.getDescription().isEmpty()) {
				return false;
			}
		return true;
		
	}
	
	public List<Subtask> getSubtasks() {
		if (!isValid()) {
			throw new IllegalStateException();
		}
		return subtasks;
	}
	
}
