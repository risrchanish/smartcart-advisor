package risrchanish.product.recommend.service.Impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.entity.ProductSearchCriteria;
import risrchanish.product.recommend.service.ProductSearchCriteriaService;

@Service
public class ProductSearchCriteriaServiceImpl implements ProductSearchCriteriaService{

	@Override
	public Page<ProductResponseDto> searchByMetadataField(String fieldName, List<String> values, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getProductsByAvailability(boolean inStock, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getProductsByFeatureVectorSimilarity(double[] featureVector, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getProductsByCombinedCriteria(ProductSearchCriteria criteria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getSortedProducts(Sort sort, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getSimilarProductsForBatch(List<Long> productIds, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
