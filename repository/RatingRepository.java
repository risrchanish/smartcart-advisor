package risrchanish.product.recommend.repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import risrchanish.product.recommend.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	Page<Rating> findByProductId(Long productId, Pageable pageable);
	
	Page<Rating> findByUserId(Long userId, Pageable pageable);
	
	Page<Rating> findByProductIdAndIsVerifiedTrue(Long productId, Pageable pageable);
	
	Optional<Rating> findByUserIdAndProductId(Long userId, Long productId);

}
