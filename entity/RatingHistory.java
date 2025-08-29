package risrchanish.product.recommend.entity;

import java.time.LocalDateTime;

public record RatingHistory(
		
		Long ratingId,
		Long userId,
		Long productId,
		double rating,
		String reviewText,
		LocalDateTime timestamp
		
		) 
{}
