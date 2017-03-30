package bot.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "privacy_policies")
public class PrivacyPolicy {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "app_name")
	private String appName;
	
	@Column(name = "email")
	private String eMail;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "is_edit")
	private boolean isEdit;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "token")
	private String token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	@Override
	public String toString() {
		return "PrivacyPolicy [id=" + id + ", appName=" + appName + ", eMail=" + eMail + ", address=" + address
				+ ", isActive=" + isActive + ", isEdit=" + isEdit + ", user=" + user + ", token=" + token + "]";
	}

	public PrivacyPolicy(boolean isActive, boolean isEdit, User user) {
		this.isActive = isActive;
		this.isEdit = isEdit;
		this.user = user;
	}

	public PrivacyPolicy() {
		
	}
	
}
