package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

public class Subtask {

	public static class Builder {
		private String description;
		private boolean completion = false;
		private int difficulty;

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
			this.completion= b;
			return this;
		}

		public Subtask build() {
			return new Subtask(this);
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

	public void setCompletion(boolean b) {
		completion = b;
	}
}
