package risrchanish.product.recommend.mapper;

import java.util.List;


import java.util.Optional;
import java.util.Collections;

import risrchanish.product.recommend.dto.preference.PreferenceCreateDto;
import risrchanish.product.recommend.dto.preference.PreferenceResponseDto;
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
		
		List<PreferenceResponseDto> preferencesDto = Optional.ofNullable(user.getPreferences())
													.orElse(Collections.emptyList())
													.stream()
												.map(pref -> 
												new PreferenceResponseDto(pref.getPreferenceId(),pref.getName(),user.getUserId())).toList();
		
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
			
			user.getPreferences().clear();
			user.getPreferences().addAll(preferences);
		}
		
		return user;
	}
	
	// PreferenceDto  ----> Preference
	
	public static Preference toEntity(PreferenceCreateDto dto, User user)
	{
		Preference pref = new Preference();
		
		pref.setName(dto.getName());
		pref.setUser(user);
		
		return pref;

	}
	
	//Preference  ---> PreferenceDto
	
	public static PreferenceResponseDto toResponseDto(Preference pref)
	{
		 return new PreferenceResponseDto(pref.getPreferenceId(),pref.getName(),pref.getUser().getUserId());
	}
	

}
