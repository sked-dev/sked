package edu.bsu.sked.model;

import java.util.LinkedList;
import java.util.List;

public class AssignmentListOrganizer {
	
	private List<Assignment> assignments;
	private boolean filterByCoursePriority = false;

	public AssignmentListOrganizer(List<Assignment> assignments) {
		this.assignments = new LinkedList<>(assignments);
	}

	public List<Assignment> organize() {
		if (filterByCoursePriority) {
			removeNonPrioritizedAssignments();
		}
		return assignments;
	}

	private void removeNonPrioritizedAssignments() {
		LinkedList<Assignment> safeCopy = new LinkedList<>(assignments);
		for (Assignment assignment : safeCopy) {
			if (!assignment.getCourse().isPrioritized())
				assignments.remove(assignment);
		}
	}

	public void setFilterByCoursePriority(boolean value) {
		filterByCoursePriority = value;
	}

}
