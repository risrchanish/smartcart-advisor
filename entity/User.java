package risrchanish.product.recommend.entity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	private String name;
	private String email;
	private Integer age;
	private String location;
	
	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Preference> preferences;
	
	// No argument constructor
	public User() {
		
	}
	
	// this constructor is used to fetch the data
	public User( String name, String email, Integer age, String location, List<Preference> preferences) {
		
		this.name = name;
		this.age = age;
		this.location = location;
		this.email = email;
		this.preferences = preferences;
	}
	
	// All argument constructor

	public User(Long userId, String name,String email, Integer age, String location, List<Preference> preferences) {
		
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.location = location;
		this.email = email;
		this.preferences = preferences;
	}

	public Long getUserId() {
		return userId;
	}

//	public void setUser_id(Long user_id) {
//		this.user_id = user_id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}
	
	@Override
	public String toString() {
		
		// Avoiding circular loop
		List<Long> preferenceIds = preferences != null ?
									preferences.stream().map(preference -> preference.getPreferenceId())
									.collect(Collectors.toList()) 
									:Collections.emptyList();
		
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", age=" + age + ", location="
			+ location + ", preferenceIds=" + preferenceIds + "]";
				
	}
		
	
}
