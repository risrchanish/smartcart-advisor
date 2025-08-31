package risrchanish.product.recommend.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.productfeature.ProductFeatureCreateDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureUpdateDto;
import risrchanish.product.recommend.repository.ProductFeatureRepository;
import risrchanish.product.recommend.service.ProductFeatureService;

@Service
public class ProductFeatureServiceImpl implements ProductFeatureService{
	
	private ProductFeatureRepository productFeatureRepository;

	@Override
	public ProductFeatureResponseDto createProductFeature(ProductFeatureCreateDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductFeatureResponseDto updateProductFeature(Long featureId, ProductFeatureUpdateDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductFeatureResponseDto getProductFeaturesById(Long featureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductFeatureResponseDto getProductFeaturesByProductId(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductFeatureResponseDto> getAllProductFeaturesByProductId(Long productId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductFeatureResponseDto> getAllProductFeatures(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductFeatureById(Long featureId) {
		// TODO Auto-generated method stub
		
	}

}
