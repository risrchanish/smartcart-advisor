package risrchanish.product.recommend.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import risrchanish.product.recommend.dto.product.ProductResponseDto;

import risrchanish.product.recommend.entity.ProductSearchCriteria;

public interface ProductSearchCriteriaService {
	   
	    // Metadata field-specific filters
	    Page<ProductResponseDto> searchByMetadataField(String fieldName, Set<String> values, Pageable pageable);

	    // Availability (if stock tracking is added)
	    Page<ProductResponseDto> getProductsByAvailability(boolean inStock, Pageable pageable);

	    // Feature vector similarity (for ML-based recommendations)
	    Page<ProductResponseDto> getProductsByFeatureVectorSimilarity(double[] featureVector, Pageable pageable);

	    // Unified search using full criteria object
	    Page<ProductResponseDto> getProductsByCombinedCriteria(ProductSearchCriteria criteria, Pageable pageable);
	    
	    Page<ProductResponseDto> getSortedProducts(Pageable pageable);
	    
	    Page<ProductResponseDto> getSimilarProductsForBatch(List<Long> productIds, Pageable pageable);
		
	
}
