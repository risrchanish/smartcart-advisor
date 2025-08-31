package risrchanish.product.recommend.dto.product;

public class ProductCreateDto {

//	@NotBlank(message = "Product name is required")
	private String name;
	
//	@NotNull(message = "Price must be provided")
//	@DecimalMin(value = "0.0", inclusive = true, message = "Price must not be negative")
	private Double price;
	
//	@NotBlank(message = "Category is required")
	private String category;
	
	private boolean inStock;
	
	public ProductCreateDto(String name, Double price, String category, boolean inStock) {
		super();
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
				+ "]";
	}

	
}
