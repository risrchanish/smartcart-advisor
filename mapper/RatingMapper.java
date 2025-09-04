package risrchanish.product.recommend.mapper;




import risrchanish.product.recommend.dto.rating.RatingCreateDto;
import risrchanish.product.recommend.dto.rating.RatingResponseDto;
import risrchanish.product.recommend.dto.rating.RatingUpdateDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.Rating;
import risrchanish.product.recommend.entity.User;

public class RatingMapper {
	
	// RatingCreateDto ------> Rating

	public static Rating toRating(RatingCreateDto dto, Product product, User user )
	{
		Rating rating = new Rating();
		rating.setRating(dto.getRating());
		rating.setReviewText(dto.getReviewText());
		rating.setTimestamp(dto.getTimestamp());
		rating.setVerified(dto.isVerified());
		rating.setProduct(product);
		rating.setUser(user);		
		return rating;
	}
	
	// Rating  -----> RatingResponseDto
	
	public static RatingResponseDto toRatingResponseDto(Rating rating)
	{
		RatingResponseDto dto = new RatingResponseDto(
									rating.getRatingId(),
									rating.getUser().getUserId(),
									rating.getProduct().getProductId(),
									rating.getRating(),
									rating.getReviewText(),
									rating.isVerified(),
									rating.getTimestamp()
									
								);
		
		return dto;
	}
	
	// ratingUpdateDto -----> Rating
	
	public static Rating updateRatingFromDto(Rating rating, RatingUpdateDto dto) {
	  
		rating.setRating(dto.getRating());
	    rating.setReviewText(dto.getReviewText());
	    rating.setVerified(dto.isVerified());
	    rating.setTimestamp(dto.getTimestamp());
	    
	    return rating;
	}
	
}
