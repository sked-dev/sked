package edu.bsu.sked.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SubtaskIterator implements Iterator<Subtask> {

	private ArrayList<Subtask> subtasks;
	private int index;

	public SubtaskIterator(ArrayList<Subtask> subtasks) {
		this.subtasks = subtasks;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		return index != subtasks.size();
	}

	@Override
	public Subtask next() {
		if (hasNext()) {
			return subtasks.get(index++);
		} else {
			System.out.println(subtasks.size());
			throw new NoSuchElementException();
		}
	}

	@Override
	public void remove() {
		if (hasNext()) {
			subtasks.remove(index--);
		} else {
			throw new NoSuchElementException();
		}

	}
}
