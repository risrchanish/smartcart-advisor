package risrchanish.product.recommend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.preference.PreferenceCreateDto;
import risrchanish.product.recommend.dto.preference.PreferenceResponseDto;
import risrchanish.product.recommend.dto.preference.PreferenceUpdateDto;


public interface PreferenceService {

	    PreferenceResponseDto createPreference(PreferenceCreateDto dto);

	    PreferenceResponseDto updatePreference(Long preferenceId, PreferenceUpdateDto dto);

	    PreferenceResponseDto getPreferenceById(Long preferenceId);
	    
	    Page<PreferenceResponseDto> getAllPreferences(Pageable pageable);

	    Page<PreferenceResponseDto> getPreferencesByUserId(Long userId, Pageable pageable);

	    Page<PreferenceResponseDto> searchPreferencesByName(String name, Pageable pageable);

	    void deletePreference(Long preferenceId);
	
}
