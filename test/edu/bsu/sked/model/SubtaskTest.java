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
	public void testDescriptionIsEdited() {
		subtask1.editDescription("Write edit description test");
		String description = subtask1.getDescription();
		Assert.assertEquals(description, "Write edit description test");
	}

	@Test
	public void testDescriptionIsCorrect() {
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
	public void testCompletionIsTrue() {
		subtask3.setCompletion(true);
		boolean completion = subtask3.isComplete();
		Assert.assertEquals(completion, true);
	}
	
	@Test
	public void testGetCompletionWorks() {
		boolean completion = subtask1.getCompletion();
		Assert.assertFalse(completion);
	}
	
	@Test
	public void testComparisonWorks1() {
		boolean equal = subtask1.equals(subtask2);
		Assert.assertFalse(equal);
	}
	
	@Ignore("Lack of independence is causing this test to fail.  Will be fixed in refactoring.")
	@Test
	public void testComparisonWorks2() {
		boolean equal = subtask1.equals(subtask5);
		Assert.assertTrue(equal);
	}
	
	@Test
	public void testComparisonWorks3() {
		boolean equal = subtask1.equals(subtask4);
		Assert.assertFalse(equal);
	}
}
