package risrchanish.product.recommend.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String name;
	private String category;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "metadata_id" , referencedColumnName = "metadataId")
	private ProductMetadata metadata;
	
	
	private double price;
	private boolean inStock;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> ratings = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductFeature> features = new ArrayList<>(); 


	public Product()
	{}
	
	public Long getProductId() {
		return productId;
	}
	
	
	// Product Id excluded
	
	public Product(String name, String category, double price,
			boolean inStock) {

		this.name = name;
		this.category = category;
		this.price = price;
		this.inStock = inStock;
		
	}


	// All arg constructor
	public Product(Long productId, String name, String category, double price,
			boolean inStock) {

		this.productId = productId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.inStock = inStock;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ProductMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(ProductMetadata metadata) {
		this.metadata = metadata;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<ProductFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<ProductFeature> features) {
		this.features = features;
	}
	
	public void setProductId(Long productId) {
	this.productId = productId;
}


	@Override
	public String toString() {
		
		List<Long> ratingsIds = ratings != null ?
								ratings.stream().map(rating -> rating.getRatingId())
								.collect(Collectors.toList())
								: Collections.emptyList();
		
		List<Long> featuresIds = features != null ?
								features.stream().map(feature -> feature.getFeatureId())
								.collect(Collectors.toList()) :
									Collections.emptyList();

		
		return "Product [productId=" + productId + ", name=" + name + ", category=" + category + 
				", metadata=" + (metadata != null ? metadata.getMetadataId() : null) + 
				", price=" + price + ", inStock=" + inStock + 
				", ratingsIds="  + ratingsIds + 
				", featuresIds=" + featuresIds + "]";
	}
	
	
}
