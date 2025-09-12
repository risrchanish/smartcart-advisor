package risrchanish.product.recommend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.rating.RatingCreateDto;
import risrchanish.product.recommend.dto.rating.RatingResponseDto;
import risrchanish.product.recommend.dto.rating.RatingUpdateDto;

public interface RatingService {
	
	RatingResponseDto createRating(RatingCreateDto dto);
	
	RatingResponseDto getRatingById(Long ratingId);

	Page<RatingResponseDto> getRatingsByProduct(Long productId, Pageable pageable);
	
	Page<RatingResponseDto> getRatingsByUser(Long userId, Pageable pageable);
	
	Page<RatingResponseDto> getAllRatings(Pageable pageable);
	
	RatingResponseDto updateRating(Long productId, Long userId, RatingUpdateDto dto);
	
	void deleteRating(Long ratingId,Long userId, Long productId);
}
