package risrchanish.product.recommend.service.Impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.user.UserCreateDto;
import risrchanish.product.recommend.dto.user.UserResponseDto;
import risrchanish.product.recommend.dto.user.UserUpdateDto;

import risrchanish.product.recommend.entity.User;
import risrchanish.product.recommend.exception.ResourceNotFoundException;
import risrchanish.product.recommend.mapper.UserMapper;
import risrchanish.product.recommend.repository.UserRepository;
import risrchanish.product.recommend.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	// Constructor injection
	public UserServiceImpl(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	// Creating an user and saving into DB
	@Override
	public UserResponseDto createUser(UserCreateDto dto) {
		
		User user = UserMapper.toEntity(dto);
		
		User savedUser = userRepository.save(user);
		
		return UserMapper.toResponseDto(savedUser);
	}

	// Fetching the user by an id.
	
	@Override
	public UserResponseDto getUserById(Long userId) {
	
		Optional<User> user = userRepository.findById(userId);
		
		return user.map(users -> UserMapper.toResponseDto(users))
						.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
	}
	
	//  Fetching all user using pagination

	@Override
	public Page<UserResponseDto> getAllUsers(Pageable pageable) {
		
		Page<User> users = userRepository.findAll(pageable);
		
		Page<UserResponseDto> usersList = users.map(user -> UserMapper.toResponseDto(user));
		
		return usersList;
	}
	
	// get users by name

	@Override
	public Page<UserResponseDto> getUsersByName(String name, Pageable pageable) {
		
		Page<User> users = userRepository.findByName(name, pageable);
		
		Page<UserResponseDto> userDto = users.map(user -> UserMapper.toResponseDto(user));
		
		return userDto;
	}
	
	// get users by location

	@Override
	public Page<UserResponseDto> getUsersByLocation(String location, Pageable pageable) {
		
		Page<User> users = userRepository.findByLocation(location, pageable);
		
		Page<UserResponseDto> userDto = users.map(user -> UserMapper.toResponseDto(user));
		
		return userDto;
	}
	
	// get users by age range

	@Override
	public Page<UserResponseDto> getUsersByAgeRange(int minAge, int maxAge, Pageable pageable) {
		
		Page<User> users = userRepository.findUsersByAgeBetween(minAge, maxAge, pageable);
		
		Page<UserResponseDto> userDto = users.map(user -> UserMapper.toResponseDto(user));
		
		return userDto;
	}
	
	// Search by name

	@Override
	public Page<UserResponseDto> searchUsersByName(String name, Pageable pageable) {
		
		Page<User> users = userRepository.searchUserByName(name, pageable);
		
		Page<UserResponseDto> userDto = users.map(user -> UserMapper.toResponseDto(user));
		
		return userDto;
	}
	
	// Search by name and location

	@Override
	public Page<UserResponseDto> searchUsersByNameAndLocation(String name, String location, Pageable pageable) {
		
		Page<User> users = userRepository.searchUserByNameAndLocation(name, location, pageable);
		
		Page<UserResponseDto> userDto = users.map(user -> UserMapper.toResponseDto(user));
		
		return userDto;
	}
	
	// Delete User

	@Override
	public void deleteUser(Long userId) {
		
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty())
		{
			throw new ResourceNotFoundException("User not found with Id: "+ userId);
		}
		
		userRepository.deleteById(userId);
	}

	// Update User
	@Override
	public UserResponseDto updateUser(Long userId, UserUpdateDto dto) {
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(userOptional.isEmpty())
		{
			throw new ResourceNotFoundException("User not found with id: "+userId);
		}
		
		User user = userOptional.get();
		
		User updatedUser = UserMapper.toUpdateDto(user, dto);
		
		userRepository.save(updatedUser);
		
		return UserMapper.toResponseDto(updatedUser);
	}

}
