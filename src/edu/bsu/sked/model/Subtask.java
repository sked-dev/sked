package edu.bsu.sked.model;

public class Subtask {

	public static class Builder {
		private String description = null;
		private boolean completion = false;
		private int difficulty = Difficulty.EASY;

		public static Builder withDescription(String description) {
			Builder builder = new Builder();
			builder.description = description;
			return builder;
		}

		public Builder andDifficulty(int dif) {
			this.difficulty = dif;
			return this;
		}

		public Builder andCompletion(boolean b) {
			this.completion = b;
			return this;
		}

		public Subtask build() {
			if (ready()) {
				return new Subtask(this);
			} else {
				throw new IllegalStateException("Invalid field state.");
			}
		}
		
		public boolean ready() {
			if (!Difficulty.isValidDifficulty(difficulty)) {
				return false;
			} else if (description == null) {
				return false;
			}
			return true;
		}
	}
	
	public static final class Difficulty {
		public static final int EASY = 1;
		public static final int MEDIUM = 2;
		public static final int HARD = 3;
		
		public static boolean isValidDifficulty(int i) {
			return i == EASY || i == MEDIUM || i == HARD;
		}
	}

	private String description;
	private boolean completion;
	private int difficulty;

	private Subtask(Builder subtaskBuilder) {
		description = subtaskBuilder.description;
		completion = subtaskBuilder.completion;
		difficulty = subtaskBuilder.difficulty;
	}

	public String getDescription() {
		return description;
	}

	public void editDescription(String str) {
		description = str;
	}

	public boolean isComplete() {
		if (completion == true) {
			return true;
		}
		return false;
	}

	public boolean isNotComplete() {
		if (completion == false) {
			return true;
		}
		return false;
	}

	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		if (Difficulty.isValidDifficulty(difficulty)) {
			this.difficulty = difficulty;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void setCompletion(boolean b) {
		completion = b;
	}

	public boolean getCompletion() {
		return completion;
	}

	public boolean equals(Subtask subtask) {
		if (description.equals(subtask.getDescription())
				&& (completion == subtask.getCompletion())
				&& (difficulty == subtask.getDifficulty())) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return description + '\n' + completion + '\n' + difficulty ;
	}
}
