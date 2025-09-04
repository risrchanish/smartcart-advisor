package risrchanish.product.recommend.service;


import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import risrchanish.product.recommend.dto.product.ProductCreateDto;
import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.dto.product.ProductUpdateDto;
import risrchanish.product.recommend.entity.Product;


public interface ProductService {

	ProductResponseDto createProduct(ProductCreateDto dto);
	
	ProductResponseDto updateProduct(Long productId, ProductUpdateDto dto);
	
	ProductResponseDto getProductById(Long productId);
	
	Page<ProductResponseDto> getAllProducts(Pageable pageable);
	
	Page<ProductResponseDto> getProductsByCategory(String category, Pageable pageable);
	
	Page<ProductResponseDto> searchProductsByName(String name, Pageable pageable);
	
	void deleteProduct(Long productId);
	
	Page<ProductResponseDto> filterProductsByMetadata(Map<String,String> filters, Pageable pageable);
	
	Double calculateDiscountedPrice(Product product);
	
	Page<ProductResponseDto> getAllProductsSortedByName(Pageable pageable);
	
	Page<ProductResponseDto> getProductByPriceRange(Double minPrice, Double maxPrice, Pageable pageable);
	
}
