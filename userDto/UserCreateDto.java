package risrchanish.product.recommend.dto.user;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import risrchanish.product.recommend.dto.preference.PreferenceCreateDto;


public class UserCreateDto {

	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotNull(message = "Age is required")
	@Min(value = 12,message = "Minimum age is 12")
	private Integer age;
	
	@NotBlank(message = "Location is required")
	private String location;
	
	
	private List<PreferenceCreateDto> preferences;
	
	
	public UserCreateDto()
	{
		
	}


	public UserCreateDto(String name, String email, Integer age, String location, List<PreferenceCreateDto> preferences) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.location = location;
		this.preferences = preferences;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public List<PreferenceCreateDto> getPreferences() {
		return preferences;
	}


	public void setPreferences(List<PreferenceCreateDto> preferences) {
		this.preferences = preferences;
	}


	@Override
	public String toString() {
		return "UserCreateDto [name=" + name + ", email=" + email + ", age=" + age + ", location=" + location
				+ ", preferences=" + preferences + "]";
	}
	
	
	
	
}
