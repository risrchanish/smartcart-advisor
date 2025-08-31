package risrchanish.product.recommend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.rating.RatingResponseDto;

public interface RatingService {

	Page<RatingResponseDto> getRatingsByProduct(Long productId, Pageable pageable);
	
	Page<RatingResponseDto> getRatingsByUser(Long userId, Pageable pageable);
}
