package risrchanish.product.recommend.dto.preference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PreferenceCreateDto {

	@NotBlank(message = "Preference name is required")
	private String name;
	
	@NotNull(message = "UserId is required")
	private Long userId;
	
	
	public PreferenceCreateDto()
	{}


	public PreferenceCreateDto(String name, Long userId) {
		this.name = name;
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "PreferenceCreateDto [name=" + name + ", userId=" + userId + "]";
	}
	
	
}
