package edu.bsu.sked.model;

import java.util.Comparator;

public class AssignmentCompletionComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment assignment1, Assignment assignment2) {
		Double assignment1CompletionPercent = assignment1.getCompletionPercent();
		Double assignment2CompletionPercent = assignment2.getCompletionPercent();
		return assignment1CompletionPercent.compareTo(assignment2CompletionPercent);
	}

}
