package edu.bsu.sked.model;

import java.time.LocalDate;
import java.util.Comparator;

public class AssignmentStartDateComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment assignment1, Assignment assignment2) {
		LocalDate assignment1StartDate = assignment1.getStartDate();
		LocalDate assignment2StartDate = assignment2.getStartDate();
		return assignment1StartDate.compareTo(assignment2StartDate);
	}

}
