package edu.bsu.sked.model;

import org.junit.*;

import edu.bsu.sked.model.UserName;

public class UserNameTest {

	private UserName user;
	
	@Before
	public void generateTestUserName() {
		user = UserName.Builder//
				.withFirstName("Austin")//
				.andLastName("DeArmond")//
				.build();
	}
	
	@Test
	public void testDefaultUserIsUnidentifiedUser() {
		UserName anon = UserName.unidentifiedUser();
		Assert.assertEquals("Unidentified user ", anon.getFullName());
	}
	
	@Test
	public void testFirstNameIsAustin() {
		Assert.assertEquals("Austin", user.getFirstName());
	}
	
	@Test
	public void testLastNameIsDeArmond() {
		Assert.assertEquals("DeArmond", user.getLastName());
	}
	
	@Test
	public void testFullNameIsAustinDeArmond() {
		Assert.assertEquals("Austin DeArmond", user.getFullName());
	}
	
	@Test
	public void testFirstNameChangesAreReflectedInFullName() {
		user.setFirstName("Paco");
		Assert.assertEquals("Paco DeArmond", user.getFullName());
	}
	
	@Test
	public void testLastNameChangesAreReflectedInFullName() {
		user.setLastName("Person");
		Assert.assertEquals("Austin Person", user.getFullName());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testUnsetFirstNameThrowsException() {
		UserName.Builder b = new UserName.Builder();
		b.andLastName("DeArmond");
		b.build();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testUnsetLastNameThrowsException() {
		UserName.Builder.withFirstName("Austin").build();
	}
	
}
