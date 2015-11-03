package edu.bsu.sked.model;

import org.junit.*;

import edu.bsu.sked.model.Subtask;

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
	
	private Subtask subtask5 = Subtask.Builder//
			.withDescription("Write edit test")//
			.andDifficulty(2).build();

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
	public void testDifficultyIsCorrect() {
		int difficulty = subtask1.getDifficulty();
		Assert.assertEquals(difficulty, 2);
	}
	
	@Test
	public void testCompletionDefaultFalse() {
		Assert.assertEquals(subtask2.isComplete(), false);
	}

	@Test
	public void testCompletionIsFalse() {
		Assert.assertEquals(subtask2.isNotComplete(), true);
	}

	@Test
	public void testCompletionIsTrue() {
		subtask3.setCompletion(true);
		boolean completion = subtask3.isComplete();
		Assert.assertEquals(completion, true);
	}

	@Test
	public void testCompletionIsTrue2() {
		Assert.assertEquals(subtask4.isNotComplete(), false);
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
