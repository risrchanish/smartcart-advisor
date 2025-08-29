package risrchanish.product.recommend.entity;

import java.util.List;

public class ProductFeature {

	private Long featureId;
	private Product product;
	private List<Double> featureVector;
	
	 public ProductFeature()
	{
		
	}
	
	// This constructor is used to fetch the data
	public ProductFeature(List<Double> featureVector) {
		
		this.featureVector = featureVector;
		}

	// All argument constructor
	public ProductFeature(Long featureId, Product product , List<Double> featureVector) {
		
		this.featureId = featureId;
		this.product = product;
		this.featureVector = featureVector;
	}

	public Long getFeatureId() {
		return featureId;
	}

//	public void setProduct_id(Long product_id) {
//		this.product_id = product_id;
//	}
	
	

	public List<Double> getFeatureVector() {
		return featureVector;
	}
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setFeatureVector(List<Double> featureVector) {
		this.featureVector = featureVector;
	}

	@Override
	public String toString() {
		return "ProductFeature [featureId=" + featureId + 
				", productId=" + ( product != null ? product.getProductId() : null) + 
				", featureVector=" + featureVector
				
				+ "]";
	}
	
	

	
	
	
	
}
