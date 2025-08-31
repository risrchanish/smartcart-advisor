package risrchanish.product.recommend.dto.productmetadata;

import java.util.List;
import java.util.Map;

public class ProductMetadataUpdateDto {
	
//	@NotNull(message = "Metadata id is required")
	private Long metadataId;
	
//	@NotBlank(message = "Brand is required")
	private String brand;
	
//	@NotBlank(message = "Color is required")
	private String color;
	
//	@NotBlank(message = "Size is required")
	private String size;
	
//	@NotBlank(message = "Material is required")
	private String material;
	
	
	private List<String> tags;
	private Map<String, String> additionalAttributes;
	
	
	public ProductMetadataUpdateDto()
	{}


	public ProductMetadataUpdateDto(Long metadataId, String brand, String color, String size, String material,
			List<String> tags, Map<String, String> additionalAttributes) {
		super();
		this.metadataId = metadataId;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.material = material;
		this.tags = tags;
		this.additionalAttributes = additionalAttributes;
	}


	public Long getMetadataId() {
		return metadataId;
	}


//	public void setMetadataId(Long metadataId) {
//		this.metadataId = metadataId;
//	}


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
		return "ProductMetadataUpdateDto [metadataId=" + metadataId + ", brand=" + brand + ", color=" + color
				+ ", size=" + size + ", material=" + material + ", tags=" + tags + ", additionalAttributes="
				+ additionalAttributes + "]";
	}
	
	

}
