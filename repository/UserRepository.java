package risrchanish.product.recommend.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import risrchanish.product.recommend.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	
	public Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
	public Page<User> findByLocationContainingIgnoreCase(String location, Pageable pageable);
	
	public Page<User> findUsersByAgeBetween(int minimum, int maximum, Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	Page<User> searchUserByName(@Param("name") String name, Pageable pageable);
	

	@Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')) AND u.location = :location")
	Page<User> searchUserByNameAndLocation(@Param("name") String name, @Param("location") String location, Pageable pageable);
}
