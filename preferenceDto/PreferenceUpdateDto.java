package risrchanish.product.recommend.dto.preference;

import jakarta.validation.constraints.NotBlank;

public class PreferenceUpdateDto {

	private Long preferenceId;
	
	@NotBlank(message = "Preference name is required")
	private String name;
	
	public PreferenceUpdateDto()
	{}

	
	public PreferenceUpdateDto(Long preferenceId, String name) {
		this.preferenceId = preferenceId;
		this.name = name;
	}


	public Long getPreferenceId() {
		return preferenceId;
	}


	public void setPreferenceId(Long preferenceId) {
		this.preferenceId = preferenceId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "PreferenceUpdateDto [preferenceId=" + preferenceId + ", name=" + name + "]";
	}
	
	
	
}
