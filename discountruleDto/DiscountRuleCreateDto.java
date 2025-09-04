package risrchanish.product.recommend.dto.discountrule;

import java.time.LocalDate;


import risrchanish.product.recommend.enums.DiscountType;

public class DiscountRuleCreateDto {

	private Long productId;
	private String category;
	private DiscountType discountType;
	private Double value;
	private LocalDate validFrom;
	private LocalDate validTo;
	private String brand;
	private String tag;
	
	public DiscountRuleCreateDto()
	{}
	
	public DiscountRuleCreateDto(Long productId, String category, DiscountType discountType, Double value,
			LocalDate validFrom, LocalDate validTo, String brand, String tag) {
		super();
		this.productId = productId;
		this.category = category;
		this.discountType = discountType;
		this.value = value;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.brand = brand;
		this.tag = tag;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public DiscountType getDiscountType() {
		return discountType;
	}


	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public LocalDate getValidFrom() {
		return validFrom;
	}


	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}


	public LocalDate getValidTo() {
		return validTo;
	}


	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	@Override
	public String toString() {
		return "DiscountRuleCreateDto [productId=" + productId + ", category=" + category + ", discountType="
				+ discountType + ", value=" + value + ", validFrom=" + validFrom + ", validTo=" + validTo + ", brand="
				+ brand + ", tag=" + tag + "]";
	}
	
	
	
}
