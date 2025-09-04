package risrchanish.product.recommend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product_features")
public class ProductFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long featureId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ElementCollection
	@CollectionTable(name = "feature_vectors", joinColumns = @JoinColumn(name = "feature_id"))
	@Column(name = "value")
	private List<Double> featureVector = new ArrayList<>();
	
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

//	public void setid(Long product_id) {
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
