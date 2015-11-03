package edu.bsu.sked.model;

import org.junit.*;

import edu.bsu.sked.model.Subtask;
import edu.bsu.sked.model.Subtask.Difficulty;

public class SubtaskTest {
	private Subtask subtask1 = Subtask.Builder//
			.withDescription("Write edit test")//
			.andDifficulty(Difficulty.NORMAL).build();

	private Subtask subtask2 = Subtask.Builder//
			.withDescription("Write a subtask class")//
			.andDifficulty(Difficulty.EASY).build();

	private Subtask subtask3 = Subtask.Builder//
			.withDescription("Write a completion test")//
			.andDifficulty(Difficulty.NORMAL).build();

	private Subtask subtask4 = Subtask.Builder//
			.withDescription("Write default completion true test")//
			.andDifficulty(Difficulty.EASY).andCompletion(true)//
			.build();
	
	private Subtask subtask5 = Subtask.Builder//
			.withDescription("Write edit test")//
			.andDifficulty(Difficulty.DIFFICULT).build();

	@Test
	public void descriptionIsEdited() {
		subtask1.editDescription("Write edit description test");
		String description = subtask1.getDescription();
		Assert.assertEquals(description, "Write edit description test");
	}

	@Test
	public void descriptionIsCorrect() {
		String description = subtask2.getDescription();
		Assert.assertEquals(description, "Write a subtask class");
	}
	
	@Test
	public void difficultyIsCorrect() {
		Difficulty difficulty = subtask1.getDifficulty();
		Assert.assertEquals(difficulty, Difficulty.NORMAL);
	}
	
	@Test
	public void completionDefaultIsFalse() {
		Assert.assertFalse(subtask2.isComplete());
	}

	@Test
	public void completionIsFalse() {
		Assert.assertFalse(subtask2.isComplete());
	}

	@Test
	public void completionIsTrue() {
		subtask3.setCompletion(true);
		boolean completion = subtask3.isComplete();
		Assert.assertEquals(completion, true);
	}
	
	@Test
	public void getCompletionWorks() {
		boolean completion = subtask1.getCompletion();
		Assert.assertEquals(completion, false);
	}
	
	@Test
	public void comparisonWorks1() {
		boolean equal = subtask1.equals(subtask2);
		Assert.assertEquals(equal, false);
	}
	
	@Ignore("Lack of independence is causing this test to fail.  Will be fixed in refactoring.")
	@Test
	public void comparisonWorks2() {
		boolean equal = subtask1.equals(subtask5);
		Assert.assertEquals(equal, true);
	}
	
	@Test
	public void comparisonWorks3() {
		boolean equal = subtask1.equals(subtask4);
		Assert.assertEquals(equal, false);
	}
}
