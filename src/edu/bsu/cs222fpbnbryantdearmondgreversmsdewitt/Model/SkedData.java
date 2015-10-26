package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import java.util.UUID;

public class SkedData {
	
	private final AssignmentList assignments;
	private final UUID uuid;
	private final UserName name;
	
	public static class Builder {
		private AssignmentList assignments = new AssignmentList();
		private UserName name = UserName.unidentifiedUser();
		
		public static Builder getBuilder() {
			return new Builder();
		}
		
		public Builder withAssignmentList(AssignmentList assignments) {
			this.assignments = assignments;
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
		assignments = new AssignmentList();
		uuid = UUID.randomUUID();
		name = UserName.unidentifiedUser();
	}
	
	public AssignmentList getAssignments() {
		return assignments;
	}

	public UUID getUuid() {
		return uuid;
	}

	public UserName getName() {
		return name;
	}
	
	

}
