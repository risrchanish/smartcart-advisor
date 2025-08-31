package risrchanish.product.recommend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.user.UserCreateDto;
import risrchanish.product.recommend.dto.user.UserResponseDto;
import risrchanish.product.recommend.dto.user.UserUpdateDto;
import risrchanish.product.recommend.entity.User;

public interface UserService {
	
	UserResponseDto createUser(UserCreateDto dto);

	UserResponseDto getUserById(Long userId);
	
	Page<UserResponseDto> getAllUsers(Pageable pageable);
	
	Page<UserResponseDto> getUsersByName(String name, Pageable pageable);
	
	Page<UserResponseDto> getUsersByLocation(String location, Pageable pageable);
	
	Page<UserResponseDto> getUsersByAgeRange(int minAge, int maxAge, Pageable pageable);
	
	Page<UserResponseDto> searchUsersByName(String name, Pageable pageable);
	
	Page<UserResponseDto> searchUsersByNameAndLocation(String name, String location, Pageable pageable);
	
	void deleteUser(Long userId);
	
	UserResponseDto updateUser(Long userId, UserUpdateDto dto);
}
