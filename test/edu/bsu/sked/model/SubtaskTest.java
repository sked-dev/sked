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
		int difficulty = subtask1.getDifficulty();
		Assert.assertEquals(difficulty, 2);
	}
	
	@Test
	public void completionDefaultFalse() {
		Assert.assertEquals(subtask2.isComplete(), false);
	}

	@Test
	public void completionIsFalse() {
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
