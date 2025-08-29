package risrchanish.product.recommend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="preferences")
public class Preference {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long preferenceId;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	

	public Preference(String name, User user) {

		this.name = name;
		this.user = user;
	}
	
	public Preference(Long preferenceId, String name, User user) {
		
		this.preferenceId = preferenceId;
		this.name = name;
		this.user = user;
	}

	public Long getPreferenceId() {
		return preferenceId;
	}

//	public void setPreferenceId(Long preferenceId) {
//		this.preferenceId = preferenceId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Preference [preferenceId=" + preferenceId + 
				", name=" + name + 
				
				", user=" + (user != null ? user.getUserId() : null) + "]";		
		
	}
		
}
