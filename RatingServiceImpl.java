package risrchanish.product.recommend.service.Impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.rating.RatingCreateDto;
import risrchanish.product.recommend.dto.rating.RatingResponseDto;
import risrchanish.product.recommend.dto.rating.RatingUpdateDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.Rating;
import risrchanish.product.recommend.entity.User;
import risrchanish.product.recommend.exception.ResourceNotFoundException;
import risrchanish.product.recommend.mapper.RatingMapper;
import risrchanish.product.recommend.repository.ProductRepository;
import risrchanish.product.recommend.repository.RatingRepository;
import risrchanish.product.recommend.repository.UserRepository;
import risrchanish.product.recommend.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	private RatingRepository ratingRepository;
	private ProductRepository productRepository;	
	private UserRepository userRepository;
	
	public RatingServiceImpl(RatingRepository ratingRepository, ProductRepository productRepository, UserRepository userRepository)
	{
		this.ratingRepository = ratingRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}
	
	
	@Override
	public RatingResponseDto createRating(RatingCreateDto dto) {
		
		Product product = productRepository.findById(dto.getProductId()).orElse(null);
		
		User user = userRepository.findById(dto.getUserId()).orElse(null);
		
		if(product == null || user == null)
		{
			throw new ResourceNotFoundException("Resource not found");
		}
		
		Rating rating = RatingMapper.toRating(dto, product, user);
		
		ratingRepository.save(rating);
		
		return RatingMapper.toRatingResponseDto(rating);
	}

	@Override
	public Page<RatingResponseDto> getRatingsByProduct(Long productId, Pageable pageable) {
		
		if(productId == null)
		{
			throw new IllegalArgumentException("Product is not available");
		}
		
		Page<Rating> ratings = ratingRepository.findByProductId(productId, pageable);
		
		if(ratings.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return ratings.map(rating -> RatingMapper.toRatingResponseDto(rating));
	}

	@Override
	public Page<RatingResponseDto> getRatingsByUser(Long userId, Pageable pageable) {
		
		if(userId == null)
		{
			throw new IllegalArgumentException("User is not available");
		}
		
		Page<Rating> ratings = ratingRepository.findByUserId(userId, pageable);
		
		if(ratings.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return ratings.map(rating -> RatingMapper.toRatingResponseDto(rating));
	}


	@Override
	public RatingResponseDto updateRatingByProductId(Long productId, RatingUpdateDto dto) {
		
		Rating rating = ratingRepository.findById(productId).orElse(null);
		
		if(rating == null)
		{
			throw new ResourceNotFoundException("Rating not available");
		}
		
		Rating updatedRating = RatingMapper.updateRatingFromDto(rating, dto);
		
		ratingRepository.save(updatedRating);
		
		return RatingMapper.toRatingResponseDto(updatedRating);
	}

	@Override
	public void deleteRating(Long ratingId) {
		
		Rating rating = ratingRepository.findById(ratingId).orElse(null);
		
		if(rating == null)
		{
			throw new ResourceNotFoundException("rating is not available");
		}
		
		ratingRepository.delete(rating);
		
	}

}
