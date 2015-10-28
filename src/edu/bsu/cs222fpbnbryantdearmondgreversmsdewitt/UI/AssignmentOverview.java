package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import java.time.LocalDate;
import java.time.Period;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AssignmentOverview extends GridPane {
	
	public AssignmentOverview(Assignment assignment) {
		super();
		this.setStyle("-fx-background-color: white; -fx-border-color: black;");
		this.setPadding(new Insets(15));
		this.add(new Label(assignment.getName()), 0, 0, 1, 1);
		this.add(new Label(getDueDateString(assignment.getDueDate())), 0, 1);
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
