package risrchanish.product.recommend.dto.productfeature;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class ProductFeatureUpdateDto {

	@NotNull(message = "Product feature id is required")
	private Long featureId;
	
	@NotNull(message = "ProductId is required")
	private Long productId;
	
	@NotEmpty(message = "feature vector must not be empty")
	private List<Double> featureVector;
	
	
	public ProductFeatureUpdateDto()
	{}


	public ProductFeatureUpdateDto(Long featureId, Long productId, List<Double> featureVector) {
		this.featureId = featureId;
		this.productId = productId;
		this.featureVector = featureVector;
	}


	public Long getFeatureId() {
		return featureId;
	}


//	public void setFeatureId(Long featureId) {
//		this.featureId = featureId;
//	}


	public Long getProductId() {
		return productId;
	}


//	public void setProductId(Long productId) {
//		this.productId = productId;
//	}


	public List<Double> getFeatureVector() {
		return featureVector;
	}


	public void setFeatureVector(List<Double> featureVector) {
		this.featureVector = featureVector;
	}
	
	
	@Override
	public String toString() {
	    return "ProductFeatureUpdateDto{" +
	           "featureId=" + featureId +
	           ", productId=" + productId +
	           ", featureVector=" + featureVector +
	           '}';
	}
}
