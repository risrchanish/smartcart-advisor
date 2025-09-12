package risrchanish.product.recommend.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_metadata")
public class ProductMetadata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long metadataId;
	
	private String brand;
	private String color;
	private String size;
	private String material;
	private Double minPrice;
	private Double maxPrice;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ElementCollection
	@CollectionTable(name = "metadata_tags", joinColumns = @JoinColumn(name = "metadata_id"))
	@Column(name = "tag")
	private List<String> tags = new ArrayList<>();
	

	@ElementCollection
	@CollectionTable(name = "metadata_attributes", joinColumns = @JoinColumn(name = "metadata_id"))
	@MapKeyColumn(name = "attribute_key")
	@Column(name = "attribute_value")
	private Map<String, String> additionalAttributes = new HashMap<>();
	
	
	public ProductMetadata()
	{}
	
	
	
	public ProductMetadata(Long metadataId, String brand, String color, String size, String material, Double minPrice,
			Double maxPrice, List<String> tags, Map<String, String> additionalAttributes) {
		this.metadataId = metadataId;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.material = material;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.tags = tags;
		this.additionalAttributes = additionalAttributes;
	}


	public ProductMetadata(String brand, String color, String size, String material, Double minPrice, Double maxPrice, List<String> tags, Map<String, String> additionalAttributes) {
		
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



	public void setMetadataId(Long metadataId) {
		this.metadataId = metadataId;
	}



	
	

	public String getBrand() {
		return brand;
	}


	public Map<String, String> getAdditionalAttributes() {
		return additionalAttributes;
	}


	public void setAdditionalAttributes(Map<String, String> additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
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


	public Double getMinPrice() {
		return minPrice;
	}



	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}



	public Double getMaxPrice() {
		return maxPrice;
	}



	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}


	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	@Override
	public String toString() {
		return "ProductMetadata [metadataId=" + metadataId + ", brand=" + brand + ", color=" + color + ", size=" + size
				+ ", material=" + material + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", tags=" + tags
				+ ", additionalAttributes=" + additionalAttributes + "]";
	}
	
	/*
	 * 	@ElementCollection
	@CollectionTable(name = "metadata_attributes", joinColumns = @JoinColumn(name = "metadata_id"))
	@MapKeyColumn(name = "attribute_key")
	@Column(name = "attribute_value")
	 */
	
	
}
