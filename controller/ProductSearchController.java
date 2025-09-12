package risrchanish.product.recommend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import risrchanish.product.recommend.dto.batchsimilarity.ProductBatchSimilarityRequestDto;
import risrchanish.product.recommend.dto.featurevector.FeatureVectorRequestDto;
import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.entity.ProductSearchCriteria;
import risrchanish.product.recommend.service.ProductSearchCriteriaService;

@RestController
@RequestMapping("/search/api")			// base url : http://localhost:8081/search/api
public class ProductSearchController {
	
	private ProductSearchCriteriaService searchCriteriaService;
	
	public ProductSearchController(ProductSearchCriteriaService searchCriteriaService)
	{
		this.searchCriteriaService = searchCriteriaService;
	}

	// Search by metadata
	
	@GetMapping("/metadata")		// url : http://localhost:8081/search/api/metadata?fieldName={}&values={}
	ResponseEntity<Page<ProductResponseDto>> searchByMetadataField(@RequestParam String fieldName, 
																@RequestParam	Set<String> values, 
																Pageable pageable)
	{
		Page<ProductResponseDto> searchByMetadata = searchCriteriaService
								.searchByMetadataField(fieldName, values, pageable);
		
		return ResponseEntity.ok(searchByMetadata);
	}
	
	// Products by availability
	
	@GetMapping("/product/available")		// url : http://localhost:8081/search/api/product/available?inStock={}
	ResponseEntity<Page<ProductResponseDto>> getProductsByAvailability(@RequestParam boolean inStock, Pageable pageable)
	{
		Page<ProductResponseDto> getProducts = searchCriteriaService
										.getProductsByAvailability(inStock, pageable);
		
		return ResponseEntity.ok(getProducts);
	}
	
	
	// Search by cosine similarity. 
	// It is a search API but written as POST because of complexity
	
	@PostMapping("/similarity")	// http://localhost:8081/search/api/similarity
	ResponseEntity<Page<ProductResponseDto>> getProductsByFeatureVectorSimilarity(
												@RequestBody @Valid FeatureVectorRequestDto dto, 
												Pageable pageable)
	{
		Page<ProductResponseDto> cosineSimilarity = searchCriteriaService
								.getProductsByFeatureVectorSimilarity(dto.getFeatureVector(), pageable);
		
		return ResponseEntity.ok(cosineSimilarity);
	}
	
	
	// Search by combined criteria
	// This is also because of complexity, written as POST method.
	
	@PostMapping("/combined/seach") 	// http://localhost:8081/search/api/combined/seach
	ResponseEntity<Page<ProductResponseDto>> getProductsByCombinedCriteria(
									@RequestBody @Valid	ProductSearchCriteria criteria, 
										Pageable pageable)
	{
		Page<ProductResponseDto> getProducts = searchCriteriaService
										.getProductsByCombinedCriteria(criteria, pageable);
		
		return ResponseEntity.ok(getProducts);
	}
	
	// Search similar batched products
	
	@PostMapping("/batched/products")  // http://localhost:8081/search/api/batched/products
	ResponseEntity<Page<ProductResponseDto>> getSimilarProductsForBatch(
											@RequestBody @Valid	ProductBatchSimilarityRequestDto dto, 
												Pageable pageable)
	{
		Page<ProductResponseDto> batchedProducts = searchCriteriaService
							.getSimilarProductsForBatch(dto.getProductIds(), pageable);
		
		return ResponseEntity.ok(batchedProducts);
	}
	
	
	// sorted products based on parameters passed
													// more example of sort is sort=price,desc&sort=rating,asc
	@GetMapping("/sort/products")  	// http://localhost:8081/search/api/sort/products?page=0&size=10sort=price,desc
	public ResponseEntity<Page<ProductResponseDto>> getSortedProducts(Pageable pageable) 
	{
		Page<ProductResponseDto> sortedProducts = searchCriteriaService.getSortedProducts(pageable);
		return ResponseEntity.ok(sortedProducts);
	}
	 
	
}
