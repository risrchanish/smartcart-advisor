package risrchanish.product.recommend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureCreateDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureUpdateDto;
import risrchanish.product.recommend.service.ProductFeatureService;

@RestController
@RequestMapping("feature/api")			// base url: http://localhost:8081/feature/api
public class ProductFeatureController {

	private ProductFeatureService featureService;
	
	public ProductFeatureController(ProductFeatureService featureService)
	{
		this.featureService = featureService;
	}
	
	
	@PostMapping
	ResponseEntity<ProductFeatureResponseDto> createProductFeature(@RequestBody @Valid ProductFeatureCreateDto dto)
	{
		ProductFeatureResponseDto createFeature = featureService.createProductFeature(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createFeature);
	}
	
	
	// GET API by ID
	@GetMapping("/{featureId}") 	// url: http://localhost:8081/feature/api/{value}
	ResponseEntity<ProductFeatureResponseDto> getProductFeaturesById(@PathVariable Long featureId)
	{
		ProductFeatureResponseDto getById = featureService.getProductFeaturesById(featureId);
		
		return ResponseEntity.ok(getById);
	}
	
	
	// Get all features
	
	
	@GetMapping("/all")		// url: http://localhost:8081/feature/api/all
	ResponseEntity<Page<ProductFeatureResponseDto>> getAllProductFeatures(Pageable pageable)
	{
		Page<ProductFeatureResponseDto> allFeatures = featureService.getAllProductFeatures(pageable);
		
		return ResponseEntity.ok(allFeatures);
	}
	
	// Get feature by product Id
	
	@GetMapping("/product") 	//  url: http://localhost:8081/feature/api/product?productId={value}
	ResponseEntity<ProductFeatureResponseDto> getProductFeaturesByProductId(@RequestParam Long productId)
	{
		ProductFeatureResponseDto getFeatureByProductId = featureService.getProductFeaturesByProductId(productId);
		
		return ResponseEntity.ok(getFeatureByProductId);
	}
	
	// Update feature
	
	@PutMapping("/update/{featureId}")		// url: http://localhost:8081/feature/api/update/{value}
	ResponseEntity<ProductFeatureResponseDto> updateProductFeature(@PathVariable Long featureId, 
																@RequestBody @Valid	ProductFeatureUpdateDto dto)
	{
		ProductFeatureResponseDto updateFeature = featureService.updateProductFeature(featureId, dto);
		
		return ResponseEntity.ok(updateFeature);
	}
	
	
	@DeleteMapping("/delete/{featureId}")   	// url: http://localhost:8081/feature/api/delete/{1}
	ResponseEntity<String> deleteProductFeatureById(@PathVariable  Long featureId)
	{
		featureService.deleteProductFeatureById(featureId);
		
		return ResponseEntity.ok("Product feature with Id: "+featureId+" has been deleted sucessfully!");
	}
	
}
