package risrchanish.product.recommend.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.discountrule.DiscountRuleCreateDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleResponseDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleUpdateDto;
import risrchanish.product.recommend.entity.Product;

public interface DiscountRuleService {

	DiscountRuleResponseDto createDiscountRule(DiscountRuleCreateDto dto);
	
	DiscountRuleResponseDto updateDiscountRule(DiscountRuleUpdateDto dto);
	
	DiscountRuleResponseDto getDiscountRuleById(Long discountId);
	
	Page<DiscountRuleResponseDto> getAllDiscountRules(Pageable pageable);
	
	Page<DiscountRuleResponseDto> getDiscountRulesByCategory(String category, Pageable pageable);
	
	Page<DiscountRuleResponseDto> getDiscountRulesByTag(String tag, Pageable pageable);
	
	Page<DiscountRuleResponseDto> getDiscountRulesByProductId(Long productId, Pageable pageable);
	
	Double applyDiscountByProduct(Product product);
	
	
}
