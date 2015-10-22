package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import java.time.LocalDate;

public class Assignment {
	
	public static class Builder {
		private String name;
		private LocalDate dueDate;
		private LocalDate startDate;
		
		public static Builder withName(String name) {
			Builder builder = new Builder();
			builder.name = name;
			return builder;
		}
		
		public Builder andDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
			return this;
		}
		
		public Builder andStartDate(LocalDate startDate){
			this.startDate =startDate;
			return this;
		}
		
		public Assignment build() {
			return new Assignment(this);
		}
		
	}
	
	private String name;
	private LocalDate dueDate;
	private LocalDate startDate;
	
	private Assignment(Builder assignmentBuilder) {
		name = assignmentBuilder.name;
		dueDate = assignmentBuilder.dueDate;
		startDate = assignmentBuilder.startDate;
	}
	
	public String getName() {
		return name;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public LocalDate getStartDate(){
		return startDate;
	}
}
