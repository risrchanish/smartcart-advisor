package risrchanish.product.recommend.dto.rating;

import java.time.LocalDateTime;

public record RatingResponseDto(
		
		 Long ratingId,
		 Long userId,
		 Long productId,
		 double rating,
		 String reviewText,
		 boolean isVerified,
		 LocalDateTime timestamp
		
		
		) {}
