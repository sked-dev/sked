package edu.bsu.sked.model;

import java.util.Comparator;

public enum AssignmentSortingMethod {
	NONE(null), NAME(new AssignmentNameComparator()), DUE_DATE(new AssignmentDueDateComparator()),
	COURSE(new AssignmentCourseComparator());
	
	public static final AssignmentSortingMethod DEFAULT = NONE;
	private final Comparator<Assignment> comparator;
	
	AssignmentSortingMethod(Comparator<Assignment> comparator) {
		this.comparator = comparator;
	}
	
	public boolean isSet() {
		return this == NONE;
	}
	
	public Comparator<Assignment> getComparator() {
		return comparator;
	}
	
}