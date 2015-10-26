package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import org.junit.*;

public class SkedDataTest {

	@Test
	public void defaultUserIsUnidentifiedUser() {
		SkedData data = SkedData.initialize();
		Assert.assertEquals("Unidentified user ", data.getName().getFullName());
	}	
	
}
