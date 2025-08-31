package risrchanish.product.recommend.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.rating.RatingResponseDto;
import risrchanish.product.recommend.repository.RatingRepository;
import risrchanish.product.recommend.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	private RatingRepository ratingRepository;

	@Override
	public Page<RatingResponseDto> getRatingsByProduct(Long productId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RatingResponseDto> getRatingsByUser(Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
