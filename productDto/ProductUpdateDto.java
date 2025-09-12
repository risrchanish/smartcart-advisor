package risrchanish.product.recommend.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductUpdateDto {

	@NotNull(message = "Product id is required")
	private Long productId;
	
	@NotBlank(message = "Product name is required")
	private String name;
	
	@NotNull(message = "Price must be provided")
	@DecimalMin(value = "0.0", inclusive = true, message = "Price must not be negative")
	private Double price;
	
	@NotBlank(message = "Category is required")
	private String category;
	
	private boolean inStock;
	
	
	
	public ProductUpdateDto(String name, Double price, String category, boolean inStock) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.inStock = inStock;
	}


	public ProductUpdateDto(Long productId,String name, Double price, String category, boolean inStock) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.category = category;
		this.inStock = inStock;
	}

	
	public Long getId() {
		return productId;
	}


//	public void setId(Long id) {
//		this.id = id;
//	}


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
		return "ProductUpdateDto [productId=" + productId + ", name=" + name + ", price=" + price + ", category="
				+ category + ", inStock=" + inStock + "]";
	}
	
	
}
