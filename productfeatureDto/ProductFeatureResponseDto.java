package risrchanish.product.recommend.dto.productfeature;

import java.util.List;

import risrchanish.product.recommend.entity.Product;

public record ProductFeatureResponseDto(
		Long featureId,
		Long productId,
		List<Double> featureVector
		
		) {

}
