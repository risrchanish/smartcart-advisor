package risrchanish.product.recommend.dto.rating;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RatingCreateDto {

	@DecimalMin(value = "0.0", message = "rating must be at least 0")
	@DecimalMax(value = "5.0", message = "Rating must be at most 5")
	private double rating;
	
	@Size(max = 1000, message = "Review texts must be under 1000 characters")
	private String reviewText;
	
	@JsonProperty("verified")
	private boolean verified;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")  // "timestamp format": "2025-09-10T14:30:00"
	@NotNull(message = "Time stamp is required")
	private LocalDateTime timestamp;
	
	@NotNull(message = "User id is required")
	private Long userId;

	@NotNull(message = "Product id is required")	
	private Long productId;
	
	public RatingCreateDto()
	{}


	public RatingCreateDto(double rating, String reviewText, boolean verified, LocalDateTime timestamp) {
		super();
		this.rating = rating;
		this.reviewText = reviewText;
		this.verified = verified;
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
		return "RatingCreateDto [rating=" + rating + ", reviewText=" + reviewText + ", verified=" + verified
				+ ", timestamp=" + timestamp + "]";
	}
	
	
	
}
