package risrchanish.product.recommend.dto.rating;

import java.time.LocalDateTime;

public class RatingUpdateDto {

//	@NotNull(message = "Rating id is required")
	private Long ratingId;
	
//	@NotNull(message = "User id is required")
	private Long userId;
	
//	@NotNull(message = "Product id is required")
	private Long productId;
	
//	@DecimalMin(value = "0.0", message = "Rating must be atleast 0")
//	@DecimalMax(value = "5.0", message = "Rating must be outmost 5")
	private double rating;
	
//	@Size(max = 1000, message = "Review texts must be under 1000 characters")
	private String reviewText;
	private boolean verified;
	
//	@NotNull(message = "Timestamp is required")
	private LocalDateTime timestamp;
	
	
	
	public RatingUpdateDto()
	{}
	
	
	public RatingUpdateDto(double rating, String reviewText,
			boolean verified, LocalDateTime timestamp) {
		this.rating = rating;
		this.reviewText = reviewText;
		this.verified = verified;
		this.timestamp = timestamp;
	}

	public RatingUpdateDto(Long ratingId, Long userId, Long productId, double rating, String reviewText,
			boolean verified, LocalDateTime timestamp) {
		
		this.ratingId = ratingId;
		this.userId = userId;
		this.productId = productId;
		this.rating = rating;
		this.reviewText = reviewText;
		this.verified = verified;
		this.timestamp = timestamp;
	}


	public Long getRatingId() {
		return ratingId;
	}


//	public void setRatingId(Long ratingId) {
//		this.ratingId = ratingId;
//	}


	public Long getUserId() {
		return userId;
	}


//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}


	public Long getProductId() {
		return productId;
	}


//	public void setProductId(Long productId) {
//		this.productId = productId;
//	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getReviewText() {
		return reviewText;
	}


	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}


	public boolean isVerified() {
		return verified;
	}


	public void setVerified(boolean verified) {
		this.verified = verified;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "RatingUpdateDto [ratingId=" + ratingId + ", userId=" + userId + ", productId=" + productId + ", rating="
				+ rating + ", reviewText=" + reviewText + ", verified=" + verified + ", timestamp=" + timestamp
				+ "]";
	}
	
	
	
}
