package risrchanish.product.recommend.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import risrchanish.product.recommend.dto.product.ProductCreateDto;
import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.dto.product.ProductUpdateDto;


public interface ProductService {

	ProductResponseDto createProduct(ProductCreateDto dto);
	
	ProductResponseDto updateProduct(ProductUpdateDto dto);
	
	ProductResponseDto getProductById(Long productId);
	
	Page<ProductResponseDto> getAllProducts(Pageable pageable);
	
	Page<ProductResponseDto> getProductsByCategory(String category, Pageable pageable);
	
	Page<ProductResponseDto> searchProductsByName(String name, Pageable pageable);
	
	boolean deleteProduct(Long productId);
	
	Page<ProductResponseDto> filterProductsByMetadata(Map<String,String> filters, Pageable pageable);
	
	Double calculateDiscountedPrice(Long productId);
	
	List<ProductResponseDto> getAllProductsSorted(Sort sort);
	
	Page<ProductResponseDto> getProductByPriceRange(Double minPrice, Double maxPrice, Pageable pageable);
	
}
