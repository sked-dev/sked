package edu.bsu.sked.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SkedDataContainer {

	private final Set<Course> courses;
	private final UUID uuid;
	private final UserName name;

	public static class Builder {
		private UserName name = UserName.unidentifiedUser();
		private Set<Course> courses = new LinkedHashSet<Course>();

		public static Builder getBuilder() {
			return new Builder();
		}

		public Builder withUserName(UserName name) {
			this.name = name;
			return this;
		}

		public Builder withCourses(Set<Course> courses) {
			this.courses = new LinkedHashSet<Course>(courses);
			return this;
		}

		public SkedDataContainer build() {
			return new SkedDataContainer(this);
		}
	}

	private SkedDataContainer(Builder b) {
		name = b.name;
		courses = b.courses;
		uuid = UUID.randomUUID();
	}

	public static SkedDataContainer initialize() {
		return new SkedDataContainer();
	}

	private SkedDataContainer() {
		courses = new LinkedHashSet<Course>();
		uuid = UUID.randomUUID();
		name = UserName.unidentifiedUser();
	}

	public Course getCourse(int index) {
		return new ArrayList<Course>(courses).get(index);
	}

	public List<Assignment> getAssignments() {
		List<Assignment> temp = new ArrayList<Assignment>();
		for (Course c : courses) {
			if(c.getAssignments() != null){
				temp.addAll(c.getAssignments());
			}
		}
		return temp;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public UUID getUuid() {
		return uuid;
	}

	public UserName getName() {
		return name;
	}
}
