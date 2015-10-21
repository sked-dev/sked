package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt;

import java.time.LocalDate;

public class Assignment {
	
	public static class Builder {
		private String name;
		
		public static Builder withName(String name) {
			Builder builder = new Builder();
			builder.name = name;
			return builder;
		}
		
		public Assignment build() {
			return new Assignment(this);
		}
	}
	
	private String name;
	
	private Assignment(Builder assignmentBuilder) {
		name = assignmentBuilder.name;
	}
	
	public String getName() {
		return name;
	}
}
