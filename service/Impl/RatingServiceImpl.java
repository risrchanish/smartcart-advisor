package risrchanish.product.recommend.service.Impl;


import java.util.List;
import java.util.Optional;

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
	
	// Get rating by Id

	@Override
	public RatingResponseDto getRatingById(Long ratingId) {
		
		if(ratingId == null)
		{
			throw new IllegalArgumentException("Rating Id must not be null");
		}
		
		Rating rating = ratingRepository.findById(ratingId)
							.orElseThrow(() -> new ResourceNotFoundException("rating not found"));
		
		return RatingMapper.toRatingResponseDto(rating);
	}

	// get rating by product Id

	@Override
	public Page<RatingResponseDto> getRatingsByProduct(Long productId, Pageable pageable) {
		
		if(productId == null)
		{
			throw new IllegalArgumentException("Product is not available");
		}
		
		Page<Rating> ratings = ratingRepository.findByProduct_ProductId(productId, pageable);
		
		if(ratings.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return ratings.map(rating -> RatingMapper.toRatingResponseDto(rating));
	}
	
	
	// get rating by User 

	@Override
	public Page<RatingResponseDto> getRatingsByUser(Long userId, Pageable pageable) {
		
		if(userId == null)
		{
			throw new IllegalArgumentException("User is not available");
		}
		
		Page<Rating> ratings = ratingRepository.findByUser_UserId(userId, pageable);
		
		if(ratings.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return ratings.map(rating -> RatingMapper.toRatingResponseDto(rating));
	}

	
	// Updating the rating 

	@Override
	public RatingResponseDto updateRating(Long productId, Long userId, RatingUpdateDto dto) {
		
		if(userId == null || productId == null)
		{
			throw new IllegalArgumentException("User and Product Ids must not be null to update a Rating");
		}
		
		Rating rating = ratingRepository.findById(dto.getRatingId())
							.orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
		
		if(!rating.getUser().getUserId().equals(userId)  || !rating.getProduct().getProductId().equals(productId))
		{
			throw new IllegalArgumentException("User Id or Product Id does not belongs to same rating");
		}
		
		
		rating.setRating(dto.getRating());
		rating.setReviewText(dto.getReviewText());
		rating.setTimestamp(dto.getTimestamp());
		rating.setVerified(dto.isVerified());
		
		ratingRepository.save(rating);
		
		return RatingMapper.toRatingResponseDto(rating);
	}

	// Deleting a rating

	@Override
	public void deleteRating(Long ratingId, Long userId, Long productId) {
		
		if(ratingId == null || userId == null || productId == null)
		{
			throw new IllegalArgumentException("For deleting a rating User, Rating and Product Ids must not be null");
		}
		
		Rating rating = ratingRepository.findById(ratingId)
							.orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
		
		if(!rating.getUser().getUserId().equals(userId) || !rating.getProduct().getProductId().equals(productId))
		{
			throw new IllegalArgumentException("User Id and Product Id does not belong to same rating");
		}
		
		ratingRepository.delete(rating);
		
	}

	// Find all ratings

	@Override
	public Page<RatingResponseDto> getAllRatings(Pageable pageable) {
		
		Page<Rating> ratings = ratingRepository.findAll(pageable);
		
		if(ratings.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return ratings.map(rating -> RatingMapper.toRatingResponseDto(rating));
	}

}
