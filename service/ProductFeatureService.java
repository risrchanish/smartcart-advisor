package risrchanish.product.recommend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.productfeature.ProductFeatureCreateDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureUpdateDto;

public interface ProductFeatureService {

	ProductFeatureResponseDto createProductFeature(ProductFeatureCreateDto dto);
	
	ProductFeatureResponseDto updateProductFeature(Long featureId, ProductFeatureUpdateDto dto);
	
	ProductFeatureResponseDto getProductFeaturesById(Long featureId);
	
	ProductFeatureResponseDto getProductFeaturesByProductId(Long productId);
	
	Page<ProductFeatureResponseDto> getAllProductFeatures(Pageable pageable);
	
	void deleteProductFeatureById(Long featureId);
	
}
