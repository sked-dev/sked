package edu.bsu.sked.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	public static final Course NONE = new Course("none");
	
	private String name;
	private boolean prioritized = false;
	private List<Assignment> assignments = new ArrayList<Assignment>();

	public Course(String name) {
		this.name = validateName(name);
	}

	private String validateName(String name) {
		if (name.isEmpty())
			throw new IllegalArgumentException("Class name cannot be blank.");
		return name;
	}
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void addAssignment(Assignment assignment) { //TODO: test
		assignments.add(assignment);
	}

	public void setName(String name) {
		validateName(name);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPrioritized(boolean prioritized) {
		this.prioritized = prioritized;
	}

	public boolean isPrioritized() {
		return prioritized;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
