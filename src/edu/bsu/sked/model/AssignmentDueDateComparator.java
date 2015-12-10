package edu.bsu.sked.model;

import java.time.LocalDate;
import java.util.Comparator;

public class AssignmentDueDateComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment assignment1, Assignment assignment2) {
		LocalDate assignment1DueDate = assignment1.getDueDate();
		LocalDate assignment2DueDate = assignment2.getDueDate();
		return assignment1DueDate.compareTo(assignment2DueDate);
	}

}
