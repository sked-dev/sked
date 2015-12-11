package edu.bsu.sked.model;

import java.util.Comparator;

import edu.bsu.sked.view.SkedApplication;

public class AssignmentCourseComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment assignment1, Assignment assignment2) {
		String assignment1CourseName = SkedApplication.getSkedData().getCourse(assignment1.getCourseIndex()).getName();
		String assignment2CourseName = SkedApplication.getSkedData().getCourse(assignment2.getCourseIndex()).getName();
		return assignment1CourseName.compareTo(assignment2CourseName);
	}

}
