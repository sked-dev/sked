package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt;

import org.junit.*;
import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.*;

public class AssignmentTest {
	private Assignment finalProject = Assignment.Builder.withName("Final Project").build();

	@Test
	public void AssignmentNameIsFinalProject() {
		String name = finalProject.getName();
		Assert.assertEquals(name, "Final Project");
	}

}
