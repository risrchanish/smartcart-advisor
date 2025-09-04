package risrchanish.product.recommend.dto.product;


import java.util.List;

import risrchanish.product.recommend.dto.productfeature.ProductFeatureCreateDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataCreateDto;
import risrchanish.product.recommend.dto.rating.RatingCreateDto;


public class ProductCreateDto {

//	@NotBlank(message = "Product name is required")
	private String name;
	
//	@NotNull(message = "Price must be provided")
//	@DecimalMin(value = "0.0", inclusive = true, message = "Price must not be negative")
	private Double price;
	
//	@NotBlank(message = "Category is required")
	private String category;
	
	private boolean inStock;
	
	private ProductMetadataCreateDto metadata;
	
	private List<RatingCreateDto> ratings;
	
	private List<ProductFeatureCreateDto> featureVector;
	
	
	public List<RatingCreateDto> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingCreateDto> ratings) {
		this.ratings = ratings;
	}

	public List<ProductFeatureCreateDto> getFeatures() {
		return featureVector;
	}

	public void setFeatures(List<ProductFeatureCreateDto> featureVector) {
		this.featureVector = featureVector;
	}

	public ProductMetadataCreateDto getMetadata() {
		return metadata;
	}

	public void setMetadata(ProductMetadataCreateDto metadata) {
		this.metadata = metadata;
	}


	public ProductCreateDto(String name, Double price, String category, boolean inStock) {
		
		this.name = name;
		this.price = price;
		this.category = category;
		this.inStock = inStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	@Override
	public String toString() {
		return "ProductCreateDto [name=" + name + ", price=" + price + ", category=" + category + ", inStock=" + inStock
				+ ", ratings=" + ratings + ", features=" + featureVector + ", metadata=" + metadata + "]";
	}


	
}
