package edu.bsu.sked.model;

import org.junit.*;

import edu.bsu.sked.model.SkedDataContainer;

public class SkedDataTest {

	@Test
	public void testDefaultUserIsUnidentifiedUser() {
		SkedDataContainer data = SkedDataContainer.initialize();
		Assert.assertEquals("Unidentified user ", data.getName().getFullName());
	}

}
