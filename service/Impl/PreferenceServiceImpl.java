package risrchanish.product.recommend.service.Impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.preference.PreferenceCreateDto;
import risrchanish.product.recommend.dto.preference.PreferenceResponseDto;
import risrchanish.product.recommend.dto.preference.PreferenceUpdateDto;
import risrchanish.product.recommend.entity.Preference;
import risrchanish.product.recommend.entity.User;
import risrchanish.product.recommend.exception.ResourceNotFoundException;
import risrchanish.product.recommend.mapper.PreferenceMapper;
import risrchanish.product.recommend.repository.PreferenceRepository;
import risrchanish.product.recommend.repository.UserRepository;
import risrchanish.product.recommend.service.PreferenceService;

@Service
public class PreferenceServiceImpl implements PreferenceService{
	
	private PreferenceRepository preferenceRepository;
	private UserRepository userRepository;
	
	public PreferenceServiceImpl(PreferenceRepository preferenceRepository, UserRepository userRepository)
	{
		this.preferenceRepository = preferenceRepository;
		this.userRepository = userRepository;
	}

	@Override
	public PreferenceResponseDto createPreference(PreferenceCreateDto dto) {
		
		User user = userRepository.findById(dto.getUserId())
						.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		Preference preference = PreferenceMapper.toEntity(dto, user);
		
		preferenceRepository.save(preference);
		
		return PreferenceMapper.toDto(preference);
	}

	@Override
	public PreferenceResponseDto updatePreference(Long preferenceId, PreferenceUpdateDto dto) {
		
		if(preferenceId == null)
		{
			throw new IllegalArgumentException("Preference Id must not be null");
		}
		
		Preference preference = preferenceRepository.findById(preferenceId)
									.orElseThrow(() -> new ResourceNotFoundException("Preference not found"));
		
		Preference updatedPreference = PreferenceMapper.toEntity(preference, dto);
		
		preferenceRepository.save(updatedPreference);
		
		return PreferenceMapper.toDto(updatedPreference);
	}

	@Override
	public PreferenceResponseDto getPreferenceById(Long preferenceId) {
		
		Preference preference = preferenceRepository.findById(preferenceId)
									.orElseThrow(() -> new ResourceNotFoundException("Preference not found"));
		
		return PreferenceMapper.toDto(preference);
	}

	@Override
	public Page<PreferenceResponseDto> getPreferencesByUserId(Long userId, Pageable pageable) {
		
		
		if(userId == null)
		{
			throw new IllegalArgumentException("User id not available");
		}
		
		Page<Preference> preferences = preferenceRepository.findByUser_UserId(userId, pageable);
		
		if(preferences.isEmpty())
		{
			return Page.empty(pageable);
		}
		
				
		return preferences.map(preference -> 
				new PreferenceResponseDto(preference.getPreferenceId(),
						preference.getName(),
						preference.getUser().getUserId()));
		
	}

	@Override
	public Page<PreferenceResponseDto> searchPreferencesByName(String name, Pageable pageable) {
		
		if(name == null || name.isBlank())
		{
			throw new IllegalArgumentException("Name not found");
		}
		
		Page<Preference> preferences = preferenceRepository
											.findByNameContainingIgnoreCase(name, pageable);
		
		
		if(preferences.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return preferences.map(preference -> 
			new PreferenceResponseDto(preference.getPreferenceId(),preference.getName(),preference.getUser().getUserId()));
	}

	@Override
	public void deletePreference(Long preferenceId) {
		
		if(preferenceId == null)
		{
			throw new IllegalArgumentException("Preference id not found");
		}
		
		Preference preference = preferenceRepository.findById(preferenceId)
									.orElseThrow(() -> new ResourceNotFoundException("Preference is not vailable"));
		
		preferenceRepository.delete(preference);
		
	}
	
	// Get all preferences

	@Override
	public Page<PreferenceResponseDto> getAllPreferences(Pageable pageable) {
		
		Page<Preference> preferences = preferenceRepository.findAll(pageable);
		
		if(preferences.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return preferences.map(preference -> PreferenceMapper.toDto(preference));
	}
	

}
