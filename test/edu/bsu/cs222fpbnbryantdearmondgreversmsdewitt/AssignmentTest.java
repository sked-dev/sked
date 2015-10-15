package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt;

import org.junit.*;

public class AssignmentTest {
	private Assignment finalProject = new Assignment("Final Project");

	@Test
	public void AssignmentNameIsFinalProject() {
		String name = finalProject.getName();
		Assert.assertEquals(name, "Final Project");
	}

}
