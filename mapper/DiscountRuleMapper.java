package risrchanish.product.recommend.mapper;

import risrchanish.product.recommend.dto.discountrule.DiscountRuleCreateDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleResponseDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleUpdateDto;
import risrchanish.product.recommend.entity.DiscountRule;
import risrchanish.product.recommend.entity.Product;

public class DiscountRuleMapper {

	// DiscountCreateDto  ----> DiscounteRuleEntity
	
	public static DiscountRule toDiscountRule(DiscountRuleCreateDto dto, Product product)
	{
		DiscountRule rule = new DiscountRule();
		
		rule.setBrand(dto.getBrand());
		rule.setCategory(dto.getCategory());
		rule.setDiscountType(dto.getDiscountType());
		rule.setProduct(product);
		rule.setTag(dto.getTag());
		rule.setValidFrom(dto.getValidFrom());
		rule.setValidTo(dto.getValidTo());
		rule.setValue(dto.getValue());
		
		return rule;
	}
	
	
	// DiscountRuleEntity   ----> ResponseDto
	
	public static DiscountRuleResponseDto toResponseDto(DiscountRule rule)
	{
		return new DiscountRuleResponseDto(
					rule.getDiscountId(),
					rule.getCategory(),
					rule.getDiscountType(),
					rule.getValue(),
					rule.getValidFrom(),
					rule.getValidTo(),
					rule.getBrand(),
					rule.getTag(),
					rule.getProduct() != null ? rule.getProduct().getProductId() : null
				);
	}
	
//	DiscountRuleUpdateDto   ----> toDiscountRule
	
	public static DiscountRule updateDiscountRuleFromDto(DiscountRuleUpdateDto dto, DiscountRule rule, Product product) 
	{
		rule.setBrand(dto.getBrand());
		rule.setCategory(dto.getCategory());
		rule.setDiscountType(dto.getDiscountType());
		rule.setProduct(product);
		rule.setTag(dto.getTag());
		rule.setValidFrom(dto.getValidFrom());
		rule.setValidTo(dto.getValidTo());
		rule.setValue(dto.getValue());
		
		return rule;
	}
	
}
