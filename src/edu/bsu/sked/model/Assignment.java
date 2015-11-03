package edu.bsu.sked.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Assignment {

	public static class Builder {
		private String name = null;
		private LocalDate dueDate = null;
		private LocalDate startDate = LocalDate.now();
		private ArrayList<Subtask> subtasks = new ArrayList<>();

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
		
		public Builder andSubtasks(List<Subtask> tasks) {
			this.subtasks = new ArrayList<Subtask>(tasks);
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
	private final ArrayList<Subtask> subtasks;

	private Assignment(Builder assignmentBuilder) {
		name = assignmentBuilder.name;
		dueDate = assignmentBuilder.dueDate;
		startDate = assignmentBuilder.startDate;
		subtasks = assignmentBuilder.subtasks;
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
	
	/**
	 * @return
	 * 		A <i>modifiable</i> ArrayList of subtasks.
	 */
	public ArrayList<Subtask> getSubtasks() {
		return subtasks;
	}
}