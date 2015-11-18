package edu.bsu.sked.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SkedDataContainer {

	private final List<Assignment> assignments;
	private final Set<Course> courses;
	private final UUID uuid;
	private final UserName name;

	public static class Builder {
		private List<Assignment> assignments = new ArrayList<>();
		private UserName name = UserName.unidentifiedUser();
		private Set<Course> courses = new LinkedHashSet<Course>();

		public static Builder getBuilder() {
			return new Builder();
		}

		public Builder withAssignmentList(List<Assignment> assignments) {
			this.assignments = new ArrayList<Assignment>(assignments);
			return this;
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
		assignments = b.assignments;
		name = b.name;
		courses = b.courses;
		uuid = UUID.randomUUID();
	}

	public static SkedDataContainer initialize() {
		return new SkedDataContainer();
	}

	private SkedDataContainer() {
		assignments = new ArrayList<Assignment>();
		courses = new LinkedHashSet<Course>();
		uuid = UUID.randomUUID();
		name = UserName.unidentifiedUser();
	}

	public List<Assignment> getAssignments() {
		return assignments;
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
