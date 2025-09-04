package risrchanish.product.recommend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.rating.RatingCreateDto;
import risrchanish.product.recommend.dto.rating.RatingResponseDto;
import risrchanish.product.recommend.dto.rating.RatingUpdateDto;
import risrchanish.product.recommend.entity.Rating;

public interface RatingService {
	
	RatingResponseDto createRating(RatingCreateDto dto);

	Page<RatingResponseDto> getRatingsByProduct(Long productId, Pageable pageable);
	
	Page<RatingResponseDto> getRatingsByUser(Long userId, Pageable pageable);
	
	RatingResponseDto updateRatingByProductId(Long productId, RatingUpdateDto dto);
	
	void deleteRating(Long ratingId);
}
