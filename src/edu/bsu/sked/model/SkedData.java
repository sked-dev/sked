package edu.bsu.sked.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SkedData {
	
	private final ArrayList<Assignment> assignments;
	private final UUID uuid;
	private final UserName name;
	
	public static class Builder {
		private ArrayList<Assignment> assignments = new ArrayList<>();
		private UserName name = UserName.unidentifiedUser();
		
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
		
		public SkedData build() {
			return new SkedData(this);
		}
	}
	
	private SkedData(Builder b) {
		assignments = b.assignments;
		name = b.name;
		
		uuid = UUID.randomUUID();
	}

	public static SkedData initialize() {
		return new SkedData();
	}
	
	private SkedData() {
		assignments = new ArrayList<Assignment>();
		uuid = UUID.randomUUID();
		name = UserName.unidentifiedUser();
	}
	
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}

	public UUID getUuid() {
		return uuid;
	}

	public UserName getName() {
		return name;
	}
	
	

}
