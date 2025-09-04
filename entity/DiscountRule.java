package risrchanish.product.recommend.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import risrchanish.product.recommend.enums.DiscountType;

@Entity
@Table(name = "discount_rules")
public class DiscountRule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long discountId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private String category;
	
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	private Double value;
	private LocalDate validFrom;
	private LocalDate validTo;
	private String brand;
	private String tag;
	
	// No argument constructor
	public DiscountRule()
	{
		
	}

	public DiscountRule( String category, DiscountType discountType, Double value, LocalDate validFrom,
			LocalDate validTo, String brand, String tag) {

		this.category = category;
		this.discountType = discountType;
		this.value = value;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.brand = brand;
		this.tag = tag;
	}

	public Long getDiscountId() {
		return discountId;
	}

//	public void setDiscountId(Long discountId) {
//		this.discountId = discountId;
//	}

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
	
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "DiscountRule [discountId=" + discountId + ", category=" + category + ", discountType=" + discountType
				+ ", value=" + value + ", validFrom=" + validFrom + ", validTo=" + validTo + ", brand=" + brand
				+ ", tag=" + tag + "]";
	}
	
	
	
}
