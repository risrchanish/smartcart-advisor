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
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataCreateDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataUpdateDto;
import risrchanish.product.recommend.service.ProductMetadataService;

@RestController
@RequestMapping("/metadata/api")   // base url : http://localhost:8081/metadata/api
public class ProductMetadataController {

	private ProductMetadataService metadataService;
	
	public ProductMetadataController(ProductMetadataService metadataService)
	{
		this.metadataService = metadataService;
	}
	
	@PostMapping   		
	ResponseEntity<ProductMetadataResponseDto> createProductMetadata(@RequestBody @Valid ProductMetadataCreateDto dto)
	{
		ProductMetadataResponseDto createMetadata = metadataService.createProductMetadata(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createMetadata);
	}
	
	
	@GetMapping("/all")				// url : http://localhost:8081/metadata/api/all
	ResponseEntity<Page<ProductMetadataResponseDto>> getAllProductMetadata(Pageable pageable)
	{
		Page<ProductMetadataResponseDto> allMetadata = metadataService.getAllProductMetadata(pageable);
		
		return ResponseEntity.ok(allMetadata);
	}
	
	
	// Search by brand
	
	@GetMapping("/brand")			// http://localhost:8081/metadata/api/brand?brands={value}
	ResponseEntity<Page<ProductMetadataResponseDto>> searchProductMetadataByBrand(
														@RequestParam String brands, 
														Pageable pageable)
	{
		Page<ProductMetadataResponseDto> searchByBrand = metadataService.searchProductMetadataByBrand(brands, pageable);
		
		return ResponseEntity.ok(searchByBrand);
	}
	
	
	// Search by color
	
	@GetMapping("/color")		// // http://localhost:8081/metadata/api/brand?colors={value}
	ResponseEntity<Page<ProductMetadataResponseDto>> searchProductMetadataByColor(@RequestParam String colors, Pageable pageable)
	{
		Page<ProductMetadataResponseDto> searchByColor = metadataService.searchProductMetadataByColor(colors, pageable);
		
		return ResponseEntity.ok(searchByColor);
	}
	
	
	
	// Update API
	
	@PutMapping("/update") 		//http://localhost:8081/metadata/api/update
	ResponseEntity<ProductMetadataResponseDto> updateProductMetadata(@RequestBody @Valid ProductMetadataUpdateDto dto)
	{
		ProductMetadataResponseDto updateMetadata = metadataService.updateProductMetadata(dto);
		
		return ResponseEntity.ok(updateMetadata);
	}
	
	
	// Delete API
	
	@DeleteMapping("/delete/{metadataId}")    // localhost:8081/metadata/api/delete/{value}
	ResponseEntity<String> deleteProductMetadata(@PathVariable Long metadataId)
	{
		metadataService.deleteProductMetadata(metadataId);
		
		return ResponseEntity.ok("product Metadata with Id: "+metadataId+" has been deleted sucessfully!");
	}
	
}
