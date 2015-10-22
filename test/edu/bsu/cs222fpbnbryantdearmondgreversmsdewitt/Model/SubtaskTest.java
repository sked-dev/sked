package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import org.junit.*;

public class SubtaskTest {
	private Subtask subtask1 = Subtask.Builder//
			.withDescription("Write edit test")//
			.andDifficulty(2).build();

	private Subtask subtask2 = Subtask.Builder//
			.withDescription("Write a subtask class")//
			.andDifficulty(1).build();

	private Subtask subtask3 = Subtask.Builder//
			.withDescription("Write a completion test")//
			.andDifficulty(2).build();

	private Subtask subtask4 = Subtask.Builder//
			.withDescription("Write default completion true test")//
			.andDifficulty(2).andCompletion(true)//
			.build();

	@Test
	public void subtaskDescriptionIsEdited() {
		subtask1.editDescription("Write edit description test");
		String description = subtask1.getDescription();
		Assert.assertEquals(description, "Write edit description test");
	}

	@Test
	public void subtaskDescriptionIsCorrect() {
		String description = subtask2.getDescription();
		Assert.assertEquals(description, "Write a subtask class");
	}
	
	@Test
	public void subtaskDifficultyIsCorrect() {
		int difficulty = subtask1.getDifficulty();
		Assert.assertEquals(difficulty, 2);
	}
	
	@Test
	public void subtaskCompletionDefaultFalse() {
		Assert.assertEquals(subtask2.isComplete(), false);
	}

	@Test
	public void subtaskCompletionIsFalse() {
		Assert.assertEquals(subtask2.isNotComplete(), true);
	}

	@Test
	public void completionIsTrue() {
		subtask3.setCompletion(true);
		boolean completion = subtask3.isComplete();
		Assert.assertEquals(completion, true);
	}

	@Test
	public void completionIsTrue2() {
		Assert.assertEquals(subtask4.isNotComplete(), false);
	}
}
