package risrchanish.product.recommend.dto.rating;

import java.time.LocalDateTime;

public class RatingCreateDto {

//	@DecimalMin(value = "0.0", message = "rating must be atleast 0")
//	@DecimalMax(value = "5.0", message = "Rating must be at most 5")
	private double rating;
	
//	@Size(max = 1000, message = "Review texts must be under 1000 characters")
	private String reviewText;
	
	private boolean isVerified;
	
//	@NotNull(message = "Timestamp is required")
	private LocalDateTime timestamp;
	
//	@NotNull(message = "User id is required")
	private Long userId;

//	@NotNull(message = "Product id is required")	
	private Long productId;
	
	public RatingCreateDto()
	{}


	public RatingCreateDto(double rating, String reviewText, boolean isVerified, LocalDateTime timestamp) {
		super();
		this.rating = rating;
		this.reviewText = reviewText;
		this.isVerified = isVerified;
		this.timestamp = timestamp;
	}


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
		return isVerified;
	}


	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	
	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	@Override
	public String toString() {
		return "RatingCreateDto [rating=" + rating + ", reviewText=" + reviewText + ", isVerified=" + isVerified
				+ ", timestamp=" + timestamp + "]";
	}
	
	
	
}
