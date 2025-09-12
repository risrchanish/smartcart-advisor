package risrchanish.product.recommend.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import risrchanish.product.recommend.dto.preference.PreferenceCreateDto;
import risrchanish.product.recommend.dto.preference.PreferenceResponseDto;
import risrchanish.product.recommend.dto.preference.PreferenceUpdateDto;
import risrchanish.product.recommend.service.PreferenceService;

@RestController
@RequestMapping("/preference/api") // base url : http://localhost:8081/preference/api
public class PreferenceController {

	private PreferenceService preferenceService;
	
	PreferenceController(PreferenceService preferenceService)
	{
		this.preferenceService = preferenceService;
	}
	
	
	// Create Preferences
	@PostMapping
	public ResponseEntity<PreferenceResponseDto> createPreference(@RequestBody @Valid PreferenceCreateDto dto)
	{
		PreferenceResponseDto createPref = preferenceService.createPreference(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createPref);
	}
	
	//Get preference by Id
	
	@GetMapping("/{preferenceId}")    // url : http://localhost:8081/preference/api/{value}
	public ResponseEntity<PreferenceResponseDto> getPreferenceById(@PathVariable Long preferenceId)
	{
		PreferenceResponseDto getById = preferenceService.getPreferenceById(preferenceId);
		
		return ResponseEntity.status(HttpStatus.OK).body(getById);
	}
	
	// Get all Preferences
	
	@GetMapping("/all")  // url : http://localhost:8081/preference/api/all
	public ResponseEntity<Page<PreferenceResponseDto>> getAllPreferences(Pageable pageable)
	{
		Page<PreferenceResponseDto> getAllPref = preferenceService.getAllPreferences(pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(getAllPref);
	}
	
	
	
	// Get preferences by UserID
	@GetMapping("user/{userId}")       // http://localhost:8081/preference/api/user/{value}
	public ResponseEntity<Page<PreferenceResponseDto>> getPreferencesByUserId(@PathVariable Long userId, Pageable pageable)
	{
		Page<PreferenceResponseDto> preferenceByUserId = preferenceService.getPreferencesByUserId(userId, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(preferenceByUserId);
	}
	
	
	// Get preference by name
	@GetMapping("/name")     // url : http://localhost:8081/preference/api/name?name={value}
	public ResponseEntity<Page<PreferenceResponseDto>> searchPreferencesByName(@RequestParam(defaultValue = "") String name, Pageable pageable)
	{
		Page<PreferenceResponseDto> searchByName = preferenceService.searchPreferencesByName(name, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(searchByName);
	}
	
	
	// Update Preference
	
	@PutMapping("/update/{preferenceId}")  // url : http://localhost:8081/preference/api/update/{value}
	public ResponseEntity<PreferenceResponseDto> updatePreference(@PathVariable Long preferenceId, 
																@RequestBody PreferenceUpdateDto dto)
	{
		PreferenceResponseDto updateById = preferenceService.updatePreference(preferenceId, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateById);
	}
	
	
	// Delete a preference by Id
	@DeleteMapping("/delete/{preferenceId}")   // http://localhost:8081/preference/api/delete/{value}
	public ResponseEntity<String> deletePreference(@PathVariable Long preferenceId)
	{
		preferenceService.deletePreference(preferenceId);
		
		return ResponseEntity.status(HttpStatus.OK).body("Preference with Id: "+preferenceId+" deleted sucessfully!");
	}
	
}
