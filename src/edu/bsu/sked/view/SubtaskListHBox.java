package edu.bsu.sked.view;

import java.util.ArrayList;
import java.util.List;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SubtaskListHBox extends HBox {
	
	private List<Subtask> subtasks;
	
	public static SubtaskListHBox create() {
		return new SubtaskListHBox(new ArrayList<Subtask>());
	}

	public SubtaskListHBox(List<Subtask> subtasks) {
		this.subtasks = subtasks;
		setLayout();
		refresh();
	}
	
	private void setLayout() {
		this.setSpacing(5);
		this.setPadding(new Insets(5));
		this.setAlignment(Pos.TOP_CENTER);
	}
	
	public void refresh() {
		if (subtasks.size() < 1) {
			addCreateButton();
		} else {
			fillSubtasks();
		}
	}

	private void addCreateButton() {
		Button button = new Button("Create subtasks");
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				getChildren().remove(button);
				getChildren().add(new SubtaskOverview());
			}
		});
		getChildren().add(button);
	}

	private void fillSubtasks() {
		for (Subtask subtask : subtasks) {
			this.getChildren().add(new SubtaskOverview(subtask));
		}
	}
	
}
