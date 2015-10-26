package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import org.junit.*;

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
	public void defaultUserIsUnidentifiedUser() {
		UserName anon = UserName.unidentifiedUser();
		Assert.assertEquals("Unidentified user ", anon.getFullName());
	}
	
	@Test
	public void firstNameIsAustin() {
		Assert.assertEquals("Austin", user.getFirstName());
	}
	
	@Test
	public void lastNameIsDeArmond() {
		Assert.assertEquals("DeArmond", user.getLastName());
	}
	
	@Test
	public void fullNameIsAustinDeArmond() {
		Assert.assertEquals("Austin DeArmond", user.getFullName());
	}
	
	@Test
	public void firstNameChangesAreReflectedInFullName() {
		user.setFirstName("Paco");
		Assert.assertEquals("Paco DeArmond", user.getFullName());
	}
	
	@Test
	public void lastNameChangesAreReflectedInFullName() {
		user.setLastName("Person");
		Assert.assertEquals("Austin Person", user.getFullName());
	}
	
	@Test(expected=IllegalStateException.class)
	public void unsetFirstNameThrowsException() {
		UserName.Builder b = new UserName.Builder();
		b.andLastName("DeArmond");
		b.build();
	}
	
	@Test(expected=IllegalStateException.class)
	public void unsetLastNameThrowsException() {
		UserName.Builder.withFirstName("Austin").build();
	}
	
}
