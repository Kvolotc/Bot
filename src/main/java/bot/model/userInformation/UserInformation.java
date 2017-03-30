package bot.model.userInformation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInformation {
	
	@JsonProperty(value = "first_name")
	private String firstName;
	
	@JsonProperty(value = "last_name")
	private String lastName;

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

	@Override
	public String toString() {
		return "UserInformation [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
