package risrchanish.product.recommend.dto.product;

import java.util.Map;

public record ProductResponseDto(
		
		Long productId,
		String name,
		Double price,
		String category,
		Double discountedPrice, // computed in service
		Map<String, String> metadata,  // For ML filtering 
		boolean inStock
		
		) {}
