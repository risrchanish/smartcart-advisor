package risrchanish.product.recommend.dto.discountrule;

import java.time.LocalDate;

import risrchanish.product.recommend.enums.DiscountType;

public record DiscountRuleResponseDto(
		
		Long discountId,
		String category,
		DiscountType discountType,
		Double value,
		LocalDate validFrom,
		LocalDate validTo,
		String brand,
		String tag,
		Long productId
			
		) {

	
}
