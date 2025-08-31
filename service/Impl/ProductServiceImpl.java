package risrchanish.product.recommend.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.product.ProductCreateDto;
import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.dto.product.ProductUpdateDto;
import risrchanish.product.recommend.repository.ProductRepository;
import risrchanish.product.recommend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;

	@Override
	public ProductResponseDto createProduct(ProductCreateDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductResponseDto updateProduct(ProductUpdateDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductResponseDto getProductById(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getProductsByCategory(String category, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> searchProductsByName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page<ProductResponseDto> filterProductsByMetadata(Map<String, String> filters, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calculateDiscountedPrice(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductResponseDto> getAllProductsSorted(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductResponseDto> getProductByPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
