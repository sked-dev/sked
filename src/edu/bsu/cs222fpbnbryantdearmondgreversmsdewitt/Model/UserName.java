package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

public class UserName {

	private String firstName;
	private String lastName;
	
	private UserName(Builder b) {
		firstName = b.firstName;
		lastName = b.lastName;
	}
	
	public static UserName unidentifiedUser() {
		Builder b = new Builder();
		b.firstName = "Unidentified user";
		b.lastName = "";
		return b.build();
	}
	
	public static class Builder {
		private String firstName = null;
		private String lastName = null;
		
		public static Builder withFirstName(String firstName) {
			Builder b = new Builder();
			b.firstName = firstName;
			return b;
		}
		
		public Builder andLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public UserName build() {
			checkRequiredFields();
			return new UserName(this);
		}

		private void checkRequiredFields() {
			if (firstName != null)
			if (lastName != null)
				return;
			throw new IllegalStateException("A required field has been left empty.");
		}
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
