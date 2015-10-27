package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import java.util.Iterator;

import org.junit.*;

public class SubtaskIteratorTest {
	private Subtask subtask1 = Subtask.Builder//
			.withDescription("Write edit test")//
			.andDifficulty(2).build();

	private SubtaskList subtasks = new SubtaskList();
	
	@Test
	public void subIteratorHasNext() {
		subtasks.add(subtask1);
		SubtaskIterator subIterator = new SubtaskIterator(subtasks);
		Assert.assertTrue(subIterator.hasNext());
	}

	@Test
	public void subIteratorNext() {
		subtasks.add(subtask1);
		Iterator<Subtask> subIterator = subtasks.iterator();
		Assert.assertTrue(subtask1.equals(subIterator.next()));
	}
	
	@Test
	public void subIteratorRemove() {
		subtasks.add(subtask1);
		Iterator<Subtask> subIterator = subtasks.iterator();
		subIterator.remove();
		Assert.assertTrue(subIterator.hasNext());
	}
}
