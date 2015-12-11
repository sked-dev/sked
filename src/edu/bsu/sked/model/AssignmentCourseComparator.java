package edu.bsu.sked.model;

import java.util.Comparator;

public class AssignmentCourseComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment assignment1, Assignment assignment2) {
		String assignment1CourseName = assignment1.getCourse().getName();
		String assignment2CourseName = assignment2.getCourse().getName();
		return assignment1CourseName.compareTo(assignment2CourseName);
	}

}
