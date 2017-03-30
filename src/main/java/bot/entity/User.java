package bot.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bot.entity.constans.State;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;
	
	@Column(name = "messagerId")
	private long messagerId;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<PrivacyPolicy> privacyPolicies; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

	public List<PrivacyPolicy> getPrivacyPolicies() {
		return privacyPolicies;
	}

	public void setPrivacyPolicies(List<PrivacyPolicy> privacyPolicies) {
		this.privacyPolicies = privacyPolicies;
	}

	public long getMessagerId() {
		return messagerId;
	}

	public void setMessagerId(long messagerId) {
		this.messagerId = messagerId;
	}
	
	

	public User(String firstName, String lastName, State state, long messagerId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = state;
		this.messagerId = messagerId;
	}	

	public User() {
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", state=" + state
				+ ", messagerId=" + messagerId + "]";
	}

}
