package risrchanish.product.recommend.dto.productfeature;

import java.util.List;


public record ProductFeatureResponseDto(
		Long featureId,
		Long productId,
		List<Double> featureVector
		
		) {

}
