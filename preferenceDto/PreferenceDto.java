package risrchanish.product.recommend.dto.preference;


public class PreferenceDto {

//	@NotNull(message = "Preference id is required")
	private Long preferenceId;
	
//	@NotBlank(message = "Name is required")
	private String name;
	
	
	public PreferenceDto()
	{
		
	}
	
	public PreferenceDto(Long preferenceId, String name) {
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
		return "PreferenceDto [preferenceId=" + preferenceId + ", name=" + name + "]";
	}
	
	
	
}
