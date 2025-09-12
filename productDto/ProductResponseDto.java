package risrchanish.product.recommend.dto.product;

import java.util.List;

	
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.dto.rating.RatingResponseDto;

public record ProductResponseDto(
		
		Long productId,
		String name,
		Double price,
		String category,
		Double discountedPrice, // computed in service
		List<ProductMetadataResponseDto> metadataList,  // For ML filtering or can be written as 
		boolean inStock,
		List<RatingResponseDto> ratings,	
		List<ProductFeatureResponseDto> featureVector
		
		) {}
