package edu.bsu.sked.model;

import java.util.ArrayList;
import java.util.Iterator;

public class SubtaskList extends ArrayList<Subtask> {
	
	private static final long serialVersionUID = 1L;

	public SubtaskList() {
		super();
	}
	
	@Override
	public Iterator<Subtask> iterator() {
		return new SubtaskIterator(this);
	}

}
