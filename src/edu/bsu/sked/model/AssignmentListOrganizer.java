package edu.bsu.sked.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.bsu.sked.view.SkedApplication;

public class AssignmentListOrganizer {
	
	private List<Assignment> oldList;
	private boolean inverted;
	private boolean filterByCoursePriority = false;
	private AssignmentSortingMethod sortingMethod = AssignmentSortingMethod.DEFAULT;

	public AssignmentListOrganizer(List<Assignment> assignments) {
		this.oldList = assignments;
	}

	public List<Assignment> organize() {
		List<Assignment> organizedList = new LinkedList<>(oldList);
		if (filterByCoursePriority) {
			removeNonPrioritizedAssignments(organizedList);
		}
		if (sortingMethod != AssignmentSortingMethod.NONE) {
			organizedList.sort(sortingMethod.getComparator());
		}
		if (inverted) {
			Collections.reverse(organizedList);
		}
		return organizedList;
	}
	
	public boolean isInverted() {
		return inverted;
	}
	
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	private void removeNonPrioritizedAssignments(List<Assignment> modifiableList) {
		LinkedList<Assignment> safeCopy = new LinkedList<>(modifiableList);
		for (Assignment assignment : safeCopy) {
			if (SkedApplication.getSkedData().getCourse(assignment.getCourseIndex()).isPrioritized())
				modifiableList.remove(assignment);
		}
	}

	public void setFilterByCoursePriority(boolean value) {
		filterByCoursePriority = value;
	}

	public void setSortingMethod(AssignmentSortingMethod sortingMethod) {
		this.sortingMethod = sortingMethod;
	}

}
