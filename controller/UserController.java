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
import risrchanish.product.recommend.dto.user.UserCreateDto;
import risrchanish.product.recommend.dto.user.UserResponseDto;
import risrchanish.product.recommend.dto.user.UserUpdateDto;
import risrchanish.product.recommend.service.UserService;

@RestController
@RequestMapping("api/users") // base url: http://localhost:8081/api/users
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	
	// creating a user
	
	@PostMapping() // http://localhost:8081/api/users
	public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateDto dto)
	{
		UserResponseDto createdUser = userService.createUser(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	// Get user by Id
	
	@GetMapping("/{userId}")   // http://localhost:8081/api/users/{userId}
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId)
	{
		UserResponseDto getUser = userService.getUserById(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(getUser);
	}
	
	// Get all users
	
	@GetMapping("/all")  // http://localhost:8081/api/users/all
	public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable)
	{
		Page<UserResponseDto> getAll = userService.getAllUsers(pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(getAll);
	}
	
	
	// find user by name 
	
	@GetMapping("/name")   // http://localhost:8081/api/users/name?name={value}
	public ResponseEntity<Page<UserResponseDto>> getUsersByName(@RequestParam(defaultValue = "") String name, Pageable page)
	{
		Page<UserResponseDto> getByName = userService.getUsersByName(name, page);
		
		return ResponseEntity.status(HttpStatus.OK).body(getByName);
	}
	
	
	// Find by location
	
	@GetMapping("/location")  // http://localhost:8081/api/users/location?location={value}
	public ResponseEntity<Page<UserResponseDto>> getusersByLocation(@RequestParam(defaultValue ="") String location, Pageable pageable)
	{
		Page<UserResponseDto> getByLocation = userService.getUsersByLocation(location, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(getByLocation);
	}
	
	
	// find by age range
	
	@GetMapping("/ageRange")  // http://localhost:8081/api/users/agerange?minAge={value}&maxAge={value}
	public ResponseEntity<Page<UserResponseDto>> getUsersByAgeRange(@RequestParam int minAge, 
																@RequestParam int maxAge, Pageable pageable)
	{
		Page<UserResponseDto> getByAgeRange = userService.getUsersByAgeRange(minAge, maxAge, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(getByAgeRange);
	}
	
	
	// Search by name
	
	@GetMapping("searchName") // http://localhost:8081/api/users/searchName?name={value}
	public ResponseEntity<Page<UserResponseDto>> searchUsersByName(@RequestParam String name, Pageable pageable)
	{
		Page<UserResponseDto> searchByName = userService.searchUsersByName(name, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(searchByName);
	}
	
	
	// Search by name and location
	
	@GetMapping("nameAndLocation")  // http://localhost:8081/api/users/nameAndLocation?name={v1}&location={v2}
	public ResponseEntity<Page<UserResponseDto>> searchUsersByNameAndLocation(@RequestParam String name, 
													@RequestParam String location, Pageable pageable)
	
	{
		Page<UserResponseDto> searchByNameAndLocation = 
					userService.searchUsersByNameAndLocation(name, location, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(searchByNameAndLocation);
	}
	
	
	// Update a user
	
	@PutMapping("/update/{userId}") // http://localhost:8081/api/users/update/{value}
	ResponseEntity<UserResponseDto> updateUserById(@PathVariable Long userId, @RequestBody @Valid UserUpdateDto dto)
	{
		UserResponseDto updateUser = userService.updateUser(userId, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
	}
	
	
	// Delete a user
	
	@DeleteMapping("/delete/{userId}") // http://localhost:8081/api/users/delete/{value}
	ResponseEntity<String> deleteUserById(@PathVariable Long userId)
	{
		userService.deleteUser(userId);
		
		return ResponseEntity.ok("User with Id: "+userId +" deleted sucessfully !") ;
	}
	
}
