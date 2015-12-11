package edu.bsu.sked.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Assignment {

	public static class Builder {
		private String name = null;
		private LocalDate dueDate = null;
		private LocalDate startDate = LocalDate.now();
		private ArrayList<Subtask> subtasks = new ArrayList<>();
		private int courseIndex;
		
		public static Assignment emptyAssignment() {
			Builder b = new Builder();
			b.name="";
			b.dueDate= LocalDate.now();
			b.startDate= LocalDate.now();
			return b.build();
		}
		

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

		public Builder andCourseIndex(int courseIndex) {
			this.courseIndex = courseIndex;
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
	private List<Subtask> subtasks;
	private int courseIndex;

	private Assignment(Builder assignmentBuilder) {
		name = assignmentBuilder.name;
		dueDate = assignmentBuilder.dueDate;
		startDate = assignmentBuilder.startDate;
		subtasks = assignmentBuilder.subtasks;
		courseIndex = assignmentBuilder.courseIndex;
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

	public String getRelativeDueDate() {
		LocalDate today = LocalDate.now();
		return generateRelativeDateString(today);
	}

	String generateRelativeDateString(LocalDate today) {
		Period period = today.until(dueDate);
		PeriodStringGenerator generator = new PeriodStringGenerator(period);
		if (period.isZero()) {
			return "Due today!";
		} else if (period.isNegative()) {
			return generator.getPeriodString() + " overdue.";
		} else {
			return "Due in " + generator.getPeriodString() + ".";
		}
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
	
	public String getSubtaskCompletionDescription() {
			return getNumberOfCompleteSubtasks() +//
					" of " + getNumberOfSubtasks()//
					+ " tasks completed";	
	}
	
	public int getNumberOfSubtasks() {
		return subtasks.size();
	}
	
	public int getNumberOfCompleteSubtasks() {
		int completeTasks = 0;
		for(Subtask task:subtasks){
			if(task.isComplete()){
				completeTasks+=1;
			}
		}
		return completeTasks;
	}

	/**
	 * @return A <i>modifiable</i> ArrayList of subtasks.
	 */
	public List<Subtask> getSubtasks() {
		return subtasks;
	}

	public int getWeightedDifficulty() {
		int weightedDifficulty = 0;
		for (Subtask task : subtasks) {
			weightedDifficulty += task.getDifficulty().getWeight();
		}
		return weightedDifficulty;
	}

	public int getWeightedCompletion() {
		int weightedCompletion = 0;
		for (Subtask task : subtasks) {
			weightedCompletion += getCompletionWeight(task);
		}
		return weightedCompletion;
	}

	private int getCompletionWeight(Subtask task) {
		return task.getCompletion() ? task.getDifficulty().getWeight() : 0;
	}

	public double getCompletionPercent() {
		if (getWeightedDifficulty() == 0) {
			return getDaysPassedPercent();
		} else
			return (double) getWeightedCompletion() / (double) getWeightedDifficulty();
	}
	
	public double getDaysPassedPercent() {
		long numberOfAssignmentDays = Period.between(getStartDate(), getDueDate()).get(ChronoUnit.DAYS);
		long numberOfDaysSinceStart = Period.between(getStartDate(), LocalDate.now()).get(ChronoUnit.DAYS);
		return (double)numberOfDaysSinceStart / numberOfAssignmentDays;
	}

	public void setSubtasks(List<Subtask> list) {
		this.subtasks = list;
	}

	public int getCourseIndex() {
		return courseIndex;
	}

	public void setCourseIndex(int courseIndex) {
		this.courseIndex = courseIndex;
	}
}