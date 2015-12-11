package edu.bsu.sked.model;

import java.util.Comparator;

public class AssignmentDifficultyComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment assignment1, Assignment assignment2) {
		Integer assignment1Difficulty = assignment1.getWeightedDifficulty();
		Integer assignment2Difficulty = assignment2.getWeightedDifficulty();
		return assignment1Difficulty.compareTo(assignment2Difficulty);
	}

}
