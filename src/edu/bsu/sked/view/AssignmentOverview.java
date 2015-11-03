package edu.bsu.sked.view;

import java.time.LocalDate;
import java.time.Period;

import edu.bsu.sked.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AssignmentOverview extends GridPane {
	
	private Assignment assignment;
	private Label assignmentName = new Label();
	private Label assignmentDueDate = new Label();
	
	public AssignmentOverview(Assignment assignment) {
		super();
		this.assignment = assignment;
		this.setStyle("-fx-background-color: white; -fx-border-color: black;");
		this.setPadding(new Insets(15));
		getValues();
		this.add(assignmentName, 0, 0, 1, 1);
		this.add(assignmentDueDate, 0, 1);
		this.add(getEditHyperlink(), 0, 2);
	}
	
	private void getValues() {
		assignmentName.setText(assignment.getName());
		assignmentDueDate.setText(getDueDateString(assignment.getDueDate()));
	}

	private Hyperlink getEditHyperlink() {
		Hyperlink editLink = new Hyperlink("Edit");
		editLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AssignmentDetailEditorStage editor = new AssignmentDetailEditorStage(assignment);
				editor.showAndWait();
				getValues();
			}
			
		});
		return editLink;
		
	}

	private String getDueDateString(LocalDate dueDate) {
		LocalDate today = LocalDate.now();
		if (dueDate.compareTo(today) < 0) {
			return getOverdueDateString(dueDate);
		} else if (dueDate.compareTo(today) > 0) {
			return getUpcomingDueDateString(dueDate);
		} else {
			return "Due today!";
		}
	}

	private String getOverdueDateString(LocalDate dueDate) {
		Period period = dueDate.until(LocalDate.now());
		String value = "";
		if (period.getMonths() > 1) {
			value += period.getMonths() + " months";
		} else if (period.getMonths() == 1) {
			value += "1 month";
		} 
		if (period.getMonths() > 0 && period.getDays() > 0) {
			value += " and ";
		}
		if (period.getDays() > 1) {
			value += period.getDays() + " days";
		} else if (period.getDays() == 1) {
			value += "1 day";
		}
		value += " overdue.";
		return value;
	}

	private String getUpcomingDueDateString(LocalDate dueDate) {
		Period period = LocalDate.now().until(dueDate);
		String value = "Due in";
		if (period.getMonths() > 1) {
			value += " " + period.getMonths() + " months";
		} else if (period.getMonths() == 1) {
			value += " 1 month";
		} 
		if (period.getMonths() > 0 && period.getDays() > 0) {
			value += " and";
		}
		if (period.getDays() > 1) {
			value += " " + period.getDays() + " days";
		} else if (period.getDays() == 1) {
			value += " 1 day";
		}
		value += ".";
		return value;
	}
	
}
