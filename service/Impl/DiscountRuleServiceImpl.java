package risrchanish.product.recommend.service.Impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.discountrule.DiscountRuleCreateDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleResponseDto;
import risrchanish.product.recommend.dto.discountrule.DiscountRuleUpdateDto;
import risrchanish.product.recommend.entity.DiscountRule;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.enums.DiscountType;
import risrchanish.product.recommend.exception.ResourceNotFoundException;
import risrchanish.product.recommend.mapper.DiscountRuleMapper;
import risrchanish.product.recommend.repository.DiscountRuleRepository;
import risrchanish.product.recommend.repository.ProductRepository;
import risrchanish.product.recommend.service.DiscountRuleService;


@Service
public class DiscountRuleServiceImpl implements DiscountRuleService{

	private DiscountRuleRepository ruleRepository;
	
	private ProductRepository productRepository;
	
	DiscountRuleServiceImpl(DiscountRuleRepository ruleRepository, ProductRepository productRepository)
	{
		this.ruleRepository = ruleRepository;
		this.productRepository = productRepository;
	}
	
	@Override
	public DiscountRuleResponseDto createDiscountRule(DiscountRuleCreateDto dto) {
		
		Product product = null;
		
		if(dto.getProductId() != null)
		{
			product = productRepository.findById(dto.getProductId())
					.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		}
		
		if(product == null && dto.getCategory() == null && dto.getTag() == null && dto.getBrand() == null)
		{
			throw new IllegalArgumentException("At least one of the scope (product, category, brand and tag) must be provided ");
		}
				
		DiscountRule rule = DiscountRuleMapper.toDiscountRule(dto, product);
		
		ruleRepository.save(rule);
		
		return DiscountRuleMapper.toResponseDto(rule);
		
	}

	@Override
	public DiscountRuleResponseDto updateDiscountRule(DiscountRuleUpdateDto dto) {
		
		Product product = null;
		
		if(dto.getProductId() != null)
		{
			product = productRepository.findById(dto.getProductId())
					.orElseThrow(() -> new ResourceNotFoundException("Product is not available"));
		}
		
		DiscountRule rule = ruleRepository.findById(dto.getDiscountId())
							.orElseThrow(() -> new ResourceNotFoundException("Discount rule not available"));
		
		rule = DiscountRuleMapper.updateDiscountRuleFromDto(dto, rule, product);
		
		ruleRepository.save(rule);
		
		return DiscountRuleMapper.toResponseDto(rule);
		 
	}

	@Override
	public DiscountRuleResponseDto getDiscountRuleById(Long discountId) {
		
		DiscountRule rule = ruleRepository.findById(discountId)
							.orElseThrow(() -> new ResourceNotFoundException("Discount rule not found for the Id: "+discountId));
		
		return DiscountRuleMapper.toResponseDto(rule);
	}

	@Override
	public Page<DiscountRuleResponseDto> getAllDiscountRules(Pageable pageable) {
		
		Page<DiscountRuleResponseDto> allRules = ruleRepository.findAll(pageable)
										
										.map(discountRule -> DiscountRuleMapper.toResponseDto(discountRule));
		
		return allRules;
											
	}
	

	@Override
	public Page<DiscountRuleResponseDto> getDiscountRulesByCategory(String category, Pageable pageable) {
		
		Page<DiscountRule> rulesByCategory = ruleRepository.findByCategory(category, pageable);
		
		return rulesByCategory.map(rule -> DiscountRuleMapper.toResponseDto(rule));
				
	}

	@Override
	public Page<DiscountRuleResponseDto> getDiscountRulesByTag(String tag, Pageable pageable) {
		
		Page <DiscountRule> allRulesByTag = ruleRepository.findByTag(tag, pageable);
		
		return allRulesByTag.map(tagRules -> DiscountRuleMapper.toResponseDto(tagRules));
	}

	@Override
	public Page<DiscountRuleResponseDto> getDiscountRulesByProductId(Long productId, Pageable pageable) {
		
		Page<DiscountRule> rulesByProduct = ruleRepository.findByProduct_ProductId(productId, pageable);
		
		return rulesByProduct.map(rules -> DiscountRuleMapper.toResponseDto(rules));
	}

	@Override
	public Double applyDiscountByProduct(Product product) {
		
		LocalDate today = LocalDate.now();
		
		// Product Specific discount
		
		Page<DiscountRule> productRules = ruleRepository
										.findByProduct_ProductId(product.getProductId(), Pageable.unpaged());
		
		Optional<DiscountRule> validProductRule = productRules.stream()
													.filter(rule -> isValid(rule,today)).findFirst();
		
		if(validProductRule.isPresent())
		{
			return applyRule(validProductRule.get(),product.getPrice());
		}
		
		
		// Category based discount
		
		Page<DiscountRule> categoryRules = ruleRepository
											.findByCategory(product.getCategory(), Pageable.unpaged());
		
		Optional<DiscountRule> validCategoryRules = categoryRules.stream()
													.filter(rule -> isValid(rule,today)).findFirst();
		
		if(validCategoryRules.isPresent())
		{
			return applyRule(validCategoryRules.get(),product.getPrice());
		}
		
		
		// Tag based discount (finding the best discount)
		
		DiscountRule bestRule = null;
		
		List<String> tags = product.getMetadata().getTags();
		
		if(tags == null || tags.isEmpty())
		{
			throw new ResourceNotFoundException("Tags are not available for discount. Create Tag from ProductMetadata");
		}
		
		for(String tag : tags)
		{
			Page<DiscountRule> tagRules = ruleRepository.findByTag(tag, Pageable.unpaged());
			
			Optional<DiscountRule> validTagRules = tagRules.stream()
													.filter(rule -> isValid(rule,today)).findFirst();
			if(validTagRules.isPresent())
			{
				if(bestRule == null || validTagRules.get().getValue() > bestRule.getValue())
				{
					bestRule = validTagRules.get();
				}
			}
		}
		
		if(bestRule != null)
		{
			return applyRule(bestRule,product.getPrice());
		}
		
		
		// Brand based discount
		
		Page<DiscountRule> brandRules = ruleRepository
									.findByCategoryAndBrand(product.getCategory(), 
															product.getMetadata().getBrand(), 
															Pageable.unpaged());
		
		Optional<DiscountRule> validBrandRules = brandRules.stream()
													.filter(rule -> isValid(rule,today))
													.findFirst();
		
		if(validBrandRules.isPresent())
		{
			return applyRule(validBrandRules.get() , product.getPrice());
		}
		
		return product.getPrice();
		
	}
	
	
	// Static helper methods below
	
	private static boolean isValid(DiscountRule rule, LocalDate today) {
	    return (rule.getValidFrom() == null || !today.isBefore(rule.getValidFrom())) &&
	           (rule.getValidTo() == null || !today.isAfter(rule.getValidTo()));
	}

	private static Double applyRule(DiscountRule rule, Double originalPrice) {
	    if (rule.getDiscountType() == DiscountType.PERCENTAGE) {
	        return originalPrice * (1 - rule.getValue() / 100);
	    } else if (rule.getDiscountType() == DiscountType.FIXED) {
	        return Math.max(0, originalPrice - rule.getValue());
	    }
	    return originalPrice;
	}
	
	

}
