package edu.bsu.sked.model;

import java.time.LocalDate;
import java.util.Iterator;

public class Assignment {

	public static class Builder {
		private String name = null;
		private LocalDate dueDate = null;
		private LocalDate startDate = LocalDate.now();

		public static Builder withName(String name) {
			Builder builder = new Builder();
			builder.name = name;
			return builder;
		}

		public Builder andDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
			return this;
		}

		public Builder andStartDate(LocalDate startDate) {
			this.startDate = startDate;
			return this;
		}

		public Assignment build() {
			if (!ready()) {
				throw new IllegalStateException("A required field has been left empty.");
			}
			return new Assignment(this);
		}

		public boolean ready() {
			return requiredFieldsAreSet();
		}

		private boolean requiredFieldsAreSet() {
			if (name == null)
				return false;
			if (startDate == null)
				return false;
			if (dueDate == null)
				return false;
			return true;
		}

	}

	private String name;
	private LocalDate dueDate;
	private LocalDate startDate;
	private SubtaskList subtasks = new SubtaskList();

	private Assignment(Builder assignmentBuilder) {
		name = assignmentBuilder.name;
		dueDate = assignmentBuilder.dueDate;
		startDate = assignmentBuilder.startDate;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate date) {
		this.dueDate = date;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate date) {
		this.startDate = date;
	}
	
	public void addSubtask(Subtask task) {
		subtasks.add(task);
	}
}