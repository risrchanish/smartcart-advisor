package risrchanish.product.recommend.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.preference.PreferenceDto;
import risrchanish.product.recommend.repository.PreferenceRepository;
import risrchanish.product.recommend.service.PreferenceService;

@Service
public class PreferenceServiceImpl implements PreferenceService{
	
	private PreferenceRepository preferenceRepository;

	@Override
	public PreferenceDto createPreference(PreferenceDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceDto updatePreference(Long preferenceId, PreferenceDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceDto getPreferenceById(Long preferenceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PreferenceDto> getPreferencesByUserId(Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PreferenceDto> searchPreferencesByName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePreference(Long preferenceId) {
		// TODO Auto-generated method stub
		
	}

}
