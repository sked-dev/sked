package edu.bsu.sked.model;

public class Course {
	
	private String name;
	private boolean prioritized = false;

	public Course(String name) {
		this.name = validateName(name);
	}

	private String validateName(String name) {
		if (name.isEmpty())
			throw new IllegalArgumentException("Class name cannot be blank.");
		return name;
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
