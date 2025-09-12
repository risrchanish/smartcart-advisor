package risrchanish.product.recommend.dto.productmetadata;

import java.util.List;
import java.util.Map;

public record ProductMetadataResponseDto(
		Long metadataId,
		String brand,
		String color,
		String size,
		String material,
		Double maxPrice,
		Double minPrice,
		List<String> tags,
		Map<String, String> additionalAttributes
		
		) {

}
