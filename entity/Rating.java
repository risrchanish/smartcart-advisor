package risrchanish.product.recommend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private double rating;
	private String reviewText;
	private boolean isVerified;
	private LocalDateTime timestamp;
	
	
	public Rating()
	{}
	
	public Rating( double rating, String reviewText, boolean isVerified, LocalDateTime timestamp) {
		this.rating = rating;
		this.reviewText = reviewText;
		this.timestamp = timestamp;
		this.isVerified = isVerified;
	}

	public Rating(Long ratingId, User user, Product product, double rating, String reviewText, boolean isVerified, LocalDateTime timestamp) {
		super();
		this.ratingId = ratingId;
		this.user = user;
		this.product = product;
		this.rating = rating;
		this.reviewText = reviewText;
		this.timestamp = timestamp;
		this.isVerified = isVerified;
	}


	public Long getRatingId() {
		return ratingId;
	}


//	public void setRatingId(Long ratingId) {
//		this.ratingId = ratingId;
//	}


	public User getUser() {
		return user;
	}


//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}


	public Product getProduct() {
		return product;
	}


//	public void setProductId(Long productId) {
//		this.productId = productId;
//	}
//

	public boolean isVerified() {
		return isVerified;
	}


	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
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
	
	


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}



	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + 
				", user=" + (user != null ? user.getUserId() : null) + 
				", product=" + (product != null ? product.getProductId() : null) + ", rating=" + rating
				+ ", reviewText=" + reviewText + ", isVerified=" + isVerified + ", timestamp=" + timestamp + "]";
	}
	
	
}
