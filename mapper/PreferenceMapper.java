package risrchanish.product.recommend.mapper;




import risrchanish.product.recommend.dto.preference.PreferenceCreateDto;
import risrchanish.product.recommend.dto.preference.PreferenceResponseDto;
import risrchanish.product.recommend.dto.preference.PreferenceUpdateDto;
import risrchanish.product.recommend.entity.Preference;
import risrchanish.product.recommend.entity.User;

public class PreferenceMapper {

	//   Dto  ----> Entity
	
	public static Preference toEntity(PreferenceCreateDto dto, User user)
	{
		Preference preference = new Preference();
		
		preference.setName(dto.getName());
		preference.setUser(user);
		
		return preference;
	}
	
	
//	Entity  ----> Dto
	
	public static PreferenceResponseDto toDto(Preference preference)
	{
		PreferenceResponseDto dto = new PreferenceResponseDto(
						preference.getPreferenceId(),
						preference.getName(),
						preference.getUser().getUserId()
				);
		return dto;
	}
	
	// dto to Entity
	
	public static Preference toEntity(Preference preference, PreferenceUpdateDto dto)
	{
		
		preference.setName(dto.getName());
		
		return preference;
		
	}
}
