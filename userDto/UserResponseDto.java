package risrchanish.product.recommend.dto.user;

import java.util.List;

import risrchanish.product.recommend.dto.preference.PreferenceDto;

public record UserResponseDto(
		Long userId,
		String name,
		String email,
		Integer age,
		String location,
		List<PreferenceDto> preferences
		
		
		) {}
