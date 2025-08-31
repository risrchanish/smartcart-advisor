package risrchanish.product.recommend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.preference.PreferenceDto;

public interface PreferenceService {

	    PreferenceDto createPreference(PreferenceDto dto);

	    PreferenceDto updatePreference(Long preferenceId, PreferenceDto dto);

	    PreferenceDto getPreferenceById(Long preferenceId);

	    Page<PreferenceDto> getPreferencesByUserId(Long userId, Pageable pageable);

	    Page<PreferenceDto> searchPreferencesByName(String name, Pageable pageable);

	    void deletePreference(Long preferenceId);
	
}
