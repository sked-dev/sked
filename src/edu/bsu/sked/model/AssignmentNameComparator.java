package edu.bsu.sked.model;

import java.util.Comparator;

public class AssignmentNameComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment assignment1, Assignment assignment2) {
		String assignment1Name = assignment1.getName();
		String assignment2Name = assignment2.getName();
		return assignment1Name.compareTo(assignment2Name);
	}

}
