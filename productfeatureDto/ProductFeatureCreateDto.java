package risrchanish.product.recommend.dto.productfeature;

import java.util.List;

import risrchanish.product.recommend.entity.Product;

public class ProductFeatureCreateDto {

//	@NotNull(message = "ProductId is required")
	private Long productId;
	
//	@NotEmpty(message = "feature vector must not be empty")
	private List<Double> featureVector;
	
	
	public ProductFeatureCreateDto()
	{}
	
	public ProductFeatureCreateDto(Long productId, List<Double> featureVector) {
		super();
		this.productId = productId;
		this.featureVector = featureVector;
	}

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
	    return "ProductFeatureCreateDto{" +
	           "productId=" + productId +
	           ", featureVector=" + featureVector +
	           '}';
	}
	
}
