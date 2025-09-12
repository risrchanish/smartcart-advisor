package risrchanish.product.recommend.controller;

import java.util.Map;

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
import risrchanish.product.recommend.dto.product.ProductCreateDto;
import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.dto.product.ProductUpdateDto;
import risrchanish.product.recommend.service.ProductService;

@RestController
@RequestMapping("/api/product")   // Base Url: http://localhost:8081/api/product
public class ProductController {

	
	private ProductService productService;
	
	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}
	
	// Create product
	
	@PostMapping 
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductCreateDto dto)
	{
		ProductResponseDto createProduct = productService.createProduct(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
	}
	
	// Get product by Id
	
	@GetMapping("/{productId}")    // http://localhost:8081/api/product/{value}
	public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId)
	{
		ProductResponseDto getById = productService.getProductById(productId);
		
		return ResponseEntity.status(HttpStatus.OK).body(getById);
	}
	
	
	// get all products
	
	@GetMapping("/all")  // http://localhost:8081/api/product/all
	public ResponseEntity<Page<ProductResponseDto>> getAllProducts(Pageable apgeable)
	{
		Page<ProductResponseDto> getProducts = productService.getAllProducts(apgeable);
		
		return ResponseEntity.status(HttpStatus.OK).body(getProducts);
	}
	
	
	//Find product by category
	
	@GetMapping("/category")      // http://localhost:8081/api/product/category?category={value}
	public ResponseEntity<Page<ProductResponseDto>> getProductsByCategory(@RequestParam String category, Pageable pageable)
	{
		Page<ProductResponseDto> getByCategory = productService.getProductsByCategory(category, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(getByCategory);
	}
	
	
	// Search product by name
	
	@GetMapping("/name")  // http://localhost:8081/api/product/name?name={value}
	public ResponseEntity<Page<ProductResponseDto>> searchProductsByName(@RequestParam String name, Pageable pageable)
	{
		Page<ProductResponseDto> searchByName = productService.searchProductsByName(name, pageable);
		
		return ResponseEntity.ok(searchByName);
	}
	
	
	
	// Filter products by metadata
	
	@GetMapping("/search")     // http://localhost:8081/api/product/search?name={value}&name2={value}
	public ResponseEntity<Page<ProductResponseDto>> filterProductsByMetadata( @RequestParam Map<String,String> filters,
																		Pageable pageable)
	{
		Page<ProductResponseDto> filterByMetadata = productService.filterProductsByMetadata(filters, pageable);
		
		return ResponseEntity.ok(filterByMetadata);
	}
	
	
	// Get all products sorted by name
	@GetMapping("/sort/name")      // http://localhost:8081/api/product/sort/name
	public ResponseEntity<Page<ProductResponseDto>> getAllProductsSortedByName(Pageable pageable)
	{
		Page<ProductResponseDto> sortedByName = productService.getAllProductsSortedByName(pageable);
		
		return ResponseEntity.ok(sortedByName);
	}
	
	
	// find between price range
	
	@GetMapping("/pricerange")				//  http://localhost:8081/api/product/pricerange
	public ResponseEntity<Page<ProductResponseDto>> getProductByPriceRange(@RequestParam Double minPrice, 
																			@RequestParam Double maxPrice, Pageable pageable)
	{
		Page<ProductResponseDto> productsByPriceRange = productService
											.getProductByPriceRange(minPrice, maxPrice, pageable);
		
		return ResponseEntity.ok(productsByPriceRange);
	}
	
	
	// Update product
	
	@PutMapping("/update/{productId}")      // http://localhost:8081/api/product/update/{value}
	ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductUpdateDto dto)
	{
		ProductResponseDto update = productService.updateProduct(productId, dto);
		
		return ResponseEntity.ok(update);
	}
	
	
	// Delete mapping
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
	{
		productService.deleteProduct(productId);
		
		return ResponseEntity.ok("Product has been deleted for the ID: "+productId+" sucessfully!");
	}
	
}
