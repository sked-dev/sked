package edu.bsu.sked.model;

public class Subtask {
	
	public static Subtask emptySubtask() {
		Builder b = new Builder();
		b.description = "";
		return b.build();
	}

	public static class Builder {
		private String description = null;
		private boolean completion = false;
		private Difficulty difficulty = Difficulty.EASY;

		public static Builder withDescription(String description) {
			Builder builder = new Builder();
			builder.description = description;
			return builder;
		}

		public Builder andDifficulty(Difficulty dif) {
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
			if (description == null) {
				return false;
			}
			return true;
		}
	}
	
	public enum Difficulty {
		EASY(1), NORMAL(2), DIFFICULT(4);
		
		private int weight;
		
		Difficulty(int weight) {
			this.weight = weight;
		}
		
		public int getWeight() {
			return weight;
		}
	}

	private String description;
	private boolean completion;
	private Difficulty difficulty;

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
		return completion;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void setCompletion(boolean b) {
		completion = b;
	}

	public boolean getCompletion() {
		return completion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (completion ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subtask other = (Subtask) obj;
		if (completion != other.completion)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (difficulty != other.difficulty)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return description + '\n' + completion + '\n' + difficulty ;
	}
}
