package edu.bsu.sked.model;

import java.util.LinkedList;
import java.util.List;

public class AssignmentListOrganizer {
	
	private List<Assignment> oldList;
	private boolean filterByCoursePriority = false;

	public AssignmentListOrganizer(List<Assignment> assignments) {
		this.oldList = assignments;
	}

	public List<Assignment> organize() {
		List<Assignment> organizedList = new LinkedList<>(oldList);
		if (filterByCoursePriority) {
			removeNonPrioritizedAssignments(organizedList);
		}
		return organizedList;
	}

	private void removeNonPrioritizedAssignments(List<Assignment> modifiableList) {
		LinkedList<Assignment> safeCopy = new LinkedList<>(modifiableList);
		for (Assignment assignment : safeCopy) {
			if (!assignment.getCourse().isPrioritized())
				modifiableList.remove(assignment);
		}
	}

	public void setFilterByCoursePriority(boolean value) {
		filterByCoursePriority = value;
	}

}
