package risrchanish.product.recommend.repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import risrchanish.product.recommend.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	Page<Rating> findByProduct_ProductId(Long productId, Pageable pageable);
	
	Page<Rating> findByUser_UserId(Long userId, Pageable pageable);
	
	Page<Rating> findByProduct_ProductIdAndVerifiedTrue(Long productId, Pageable pageable);
	
	Optional<Rating> findByUser_UserIdAndProduct_ProductId(Long userId, Long productId);

}
