package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import java.util.ArrayList;
import java.util.Iterator;

public class SubtaskList extends ArrayList<Subtask> {
	
	public SubtaskList() {
		super();
	}
	
	@Override
	public Iterator<Subtask> iterator() {
		return new SubtaskIterator(this);
	}

}
