package risrchanish.product.recommend.dto.productmetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductMetadataCreateDto {

	@NotBlank(message = "Brand is required")
	private String brand;
	
	@NotBlank(message = "Color is required")
	private String color;
	
	@NotBlank(message = "Size is required")
	private String size;
	
	@NotBlank(message = "Material is required")
	private String material;
	
	@NotNull(message = "Product Id is required")
	private Long productId;
	
	private Double maxPrice;
	
	private Double minPrice;
	
	private List<String> tags = new ArrayList<>();
	private Map<String, String> additionalAttributes = new HashMap<>();

	
	public ProductMetadataCreateDto()
	{}


	public ProductMetadataCreateDto(String brand, String color, String size, String material, List<String> tags,
			Map<String, String> additionalAttributes) {
		super();
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.material = material;
		this.tags = tags;
		this.additionalAttributes = additionalAttributes;
	}


	
	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Double getMaxPrice() {
		return maxPrice;
	}


	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}


	public Double getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public Map<String, String> getAdditionalAttributes() {
		return additionalAttributes;
	}


	public void setAdditionalAttributes(Map<String, String> additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
	}


	@Override
	public String toString() {
		return "ProductMetadataCreateDto [brand=" + brand + ", color=" + color + ", size=" + size + ", material="
				+ material + ", tags=" + tags + ", additionalAttributes=" + additionalAttributes + "]";
	}
	
	
	
	
}
