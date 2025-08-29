package risrchanish.product.recommend.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

public class ProductSearchCriteria {

	private List<String> categories;
	private Map<String, String> metadata;
	private Double minPrice;
	private Double maxPrice;
	private String nameKeyword;
	private List<String> brands;
	private List<String> colors;
	private List<String> materials;
	private List<String> tags;
	private Map<String, String> additionalAttributes;
	private Sort sort;
	
	
	// All argument constructor
	

	public ProductSearchCriteria(List<String> categories, Map<String, String> metadata, Double minPrice,
			Double maxPrice, String nameKeyword, List<String> brands, List<String> colors, List<String> materials,
			List<String> tags, Map<String, String> additionalAttributes, Sort sort) {

		this.categories = categories;
		this.metadata = metadata;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.nameKeyword = nameKeyword;
		this.brands = brands;
		this.colors = colors;
		this.materials = materials;
		this.tags = tags;
		this.additionalAttributes = additionalAttributes;
		this.sort = sort;
	}



	public List<String> getCategories() {
		return categories;
	}



	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	public Map<String, String> getMetadata() {
		return metadata;
	}


	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
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
	
	
	


	public String getNameKeyword() {
		return nameKeyword;
	}



	public void setNameKeyword(String nameKeyword) {
		this.nameKeyword = nameKeyword;
	}



	public List<String> getBrands() {
		return brands;
	}



	public void setBrands(List<String> brands) {
		this.brands = brands;
	}



	public List<String> getColors() {
		return colors;
	}



	public void setColors(List<String> colors) {
		this.colors = colors;
	}



	public List<String> getMaterials() {
		return materials;
	}



	public void setMaterials(List<String> materials) {
		this.materials = materials;
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



	public Sort getSort() {
		return sort;
	}



	public void setSort(Sort sort) {
		this.sort = sort;
	}



	@Override
	public String toString() {
		return "ProductSearchCriteria [categories=" + categories + ", metadata=" + metadata + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + ", nameKeyword=" + nameKeyword + ", brands=" + brands + ", colors="
				+ colors + ", materials=" + materials + ", tags=" + tags + ", additionalAttributes="
				+ additionalAttributes + ", sort=" + sort + "]";
	}

	
}
