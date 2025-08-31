package risrchanish.product.recommend.mapper;

import java.util.List;


import java.util.Optional;
import java.util.Collections;
import risrchanish.product.recommend.dto.preference.PreferenceDto;
import risrchanish.product.recommend.dto.user.UserCreateDto;
import risrchanish.product.recommend.dto.user.UserResponseDto;
import risrchanish.product.recommend.dto.user.UserUpdateDto;
import risrchanish.product.recommend.entity.Preference;
import risrchanish.product.recommend.entity.User;

public class UserMapper {
	
	// CreateDto ---> Entity
	
	public static User toEntity(UserCreateDto dto)
	{
		User user = new User();
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setAge(dto.getAge());
		user.setLocation(dto.getLocation());
		
		// converting PreferenceDto to Preference
		
		if(dto.getPreferences() != null)
		{
		
			List<Preference> preferences =  dto.getPreferences().stream()
										.map(prefDto -> {
											
											Preference pref = new Preference();
											pref.setName(prefDto.getName());
											pref.setUser(user);
											return pref;
										}).toList();
			user.setPreferences(preferences);
		}
		return user;
	}
	
	
	// Entity ----> UserResponseDto
	
	public static UserResponseDto toResponseDto(User user)
	{
		
		// PreferenceDto from Preference
		
		List<PreferenceDto> preferencesDto = Optional.ofNullable(user.getPreferences())
													.orElse(Collections.emptyList())
													.stream()
												.map(pref -> 
												new PreferenceDto(pref.getPreferenceId(),pref.getName())).toList();
		
		return new UserResponseDto(
					
					user.getUserId(),
					user.getName(),
					user.getEmail(),
					user.getAge(),
					user.getLocation(),
					preferencesDto
				
				);
		
	}
	
	// updateDto ----> Entity
	
	public static User toUpdateDto(User user, UserUpdateDto dto)
	{
		user.setName(dto.getName());
		user.setAge(dto.getAge());
		user.setEmail(dto.getEmail());
		user.setLocation(dto.getLocation());
		
		
		if(dto.getPreferences() != null)
		{
			List<Preference> preferences = Optional.ofNullable(dto.getPreferences())
					.orElse(Collections.emptyList())
					.stream()
					.map(prefDto -> {
		
						Preference pref = new Preference();
						pref.setName(prefDto.getName());
						pref.setUser(user);
						return pref;
					}).toList();
			
			user.setPreferences(preferences);
		}
		
		return user;
	}
	
	// PreferenceDto  ----> Preference
	
	public static Preference toEntity(PreferenceDto dto, User user)
	{
		Preference pref = new Preference();
		
		pref.setName(dto.getName());
		pref.setPreferenceId(dto.getPreferenceId());
		pref.setUser(user);
		
		return pref;

	}
	
	//Preference  ---> PreferenceDto
	
	public static PreferenceDto toDto(Preference pref)
	{
		 return new PreferenceDto(pref.getPreferenceId(),pref.getName());
	}
	

}
