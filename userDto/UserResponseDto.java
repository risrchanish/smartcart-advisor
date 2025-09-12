package risrchanish.product.recommend.dto.user;

import java.util.List;

import risrchanish.product.recommend.dto.preference.PreferenceResponseDto;

public record UserResponseDto(
		Long userId,
		String name,
		String email,
		Integer age,
		String location,
		List<PreferenceResponseDto> preferences
		
		
		) {}
