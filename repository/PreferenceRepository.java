package risrchanish.product.recommend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import risrchanish.product.recommend.entity.Preference;
import risrchanish.product.recommend.entity.User;

public interface PreferenceRepository extends JpaRepository<Preference, Long>{

	    Page<Preference> findByUser(User user, Pageable pageable);

	    Page<Preference> findByNameContainingIgnoreCase(String name, Pageable pageable);

	    @Query("SELECT p FROM Preference p WHERE p.user.userId = :userId")
	    Page<Preference> findByUserId(@Param("userId") Long userId, Pageable pageable);	

}
