package risrchanish.product.recommend.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.product.ProductCreateDto;
import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.dto.product.ProductUpdateDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductMetadata;
import risrchanish.product.recommend.entity.Rating;
import risrchanish.product.recommend.entity.User;
import risrchanish.product.recommend.exception.ResourceNotFoundException;
import risrchanish.product.recommend.mapper.ProductMapper;
import risrchanish.product.recommend.mapper.RatingMapper;
import risrchanish.product.recommend.repository.ProductRepository;
import risrchanish.product.recommend.repository.UserRepository;
import risrchanish.product.recommend.service.DiscountRuleService;
import risrchanish.product.recommend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;	
	private DiscountRuleService discountService;
	private UserRepository userRepository;
	
	public ProductServiceImpl(ProductRepository productRepository, DiscountRuleService discountService, UserRepository userRepository)
	{
		this.productRepository = productRepository;
		this.discountService = discountService;
		this.userRepository = userRepository;
	}
	
	
	// Calculating the discounted price
	@Override
	public Double calculateDiscountedPrice(Product product) 
	{
		return discountService.applyDiscountByProduct(product);
	}

	
	@Override
	public ProductResponseDto createProduct(ProductCreateDto dto) {

	    Product product = ProductMapper.toProduct(dto); // No ratings mapped here
	    productRepository.saveAndFlush(product); // immediate sync with db
	   
	   // getting rating
	    
	    List<Rating> ratings = Optional.ofNullable(dto.getRatings())
	    				.orElse(Collections.emptyList())
	    					.stream()
	    						.map(ratingDto -> {
	    								User user = userRepository.findById(ratingDto.getUserId())
	    											.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	    						return RatingMapper.toRating(ratingDto, product, user);
	    						}).toList();

	    product.setRatings(ratings);  
	    productRepository.save(product); // Save with ratings attached

	    Double discountedPrice = calculateDiscountedPrice(product);
	    return ProductMapper.toProductResponseDto(product, discountedPrice);
	}
	
	@Override
	public ProductResponseDto updateProduct(Long productId, ProductUpdateDto dto) {
		
		Product product = 	productRepository.findById(productId)
							.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
							
		
		Product updatedProduct = ProductMapper.toProduct(product, dto);
		
		Double discountedCost = discountService.applyDiscountByProduct(updatedProduct);
		
		return ProductMapper.toProductResponseDto(updatedProduct, discountedCost);
		
	}

	@Override
	public ProductResponseDto getProductById(Long productId) {
		
		Product product = productRepository.findById(productId)
							.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		
		Double discountedCost = discountService.applyDiscountByProduct(product);
		
		return ProductMapper.toProductResponseDto(product, discountedCost);
	}

	@Override
	public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
		
		Page<Product> products = productRepository.findAll(pageable);
		
		if(products.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		 
		Page<ProductResponseDto> dto = products.map(product -> {	
										Double discountedCost = discountService.applyDiscountByProduct(product);
										return ProductMapper.toProductResponseDto(product, discountedCost);
										});
		return dto;
	}

	@Override
	public Page<ProductResponseDto> getProductsByCategory(String category, Pageable pageable) {
		
		Page<Product> products = productRepository.findByCategory(category, pageable);
		
		if(products.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return products.map(product -> {
						Double discountedCost = discountService.applyDiscountByProduct(product);
						return ProductMapper.toProductResponseDto(product, discountedCost);
							});
	}

	@Override
	public Page<ProductResponseDto> searchProductsByName(String name, Pageable pageable) {
		
		Page<Product> products = productRepository.findByName(name, pageable);
		
		if(products.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return products.map(product -> {
							Double discountedCost = discountService.applyDiscountByProduct(product);
							return ProductMapper.toProductResponseDto(product, discountedCost);
						});
	}

	@Override
	public void deleteProduct(Long productId) {
		
		Product product = productRepository.findById(productId)
							.orElseThrow(() -> new ResourceNotFoundException("Product is not available"));
		
		productRepository.delete(product);
		
	}
	
	// filter by metadata

	@Override
	public Page<ProductResponseDto> filterProductsByMetadata(Map<String, String> filters, Pageable pageable) 
	{
		Page<Product> products = productRepository.findAll(pageable);
		
		if(products.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		List<Product> filteredProducts = products.stream().filter(product -> {
			
			ProductMetadata metadata = Optional.ofNullable(product.getMetadata()).orElse(null);
			
			if(metadata == null)
			{
				return false;
			}
			
			Map<String, String> attributes = Optional.ofNullable(metadata.getAdditionalAttributes())
												.orElse(Collections.emptyMap());
			
			return filters.entrySet().stream().allMatch(entry ->{
				String key = entry.getKey().toLowerCase();
				String value = entry.getValue();
				
				Boolean match = switch(key)
						{
							case "brand" -> value.equalsIgnoreCase(metadata.getBrand());
							
							case "color" -> value.equalsIgnoreCase(metadata.getColor());
							
							case "size" -> value.equalsIgnoreCase(metadata.getSize());
							
							case "material" -> value.equalsIgnoreCase(metadata.getMaterial());
							
							case "minprice" -> value != null && Double.parseDouble(value) >= metadata.getMinPrice();
							
							case "maxprice" -> value != null && Double.parseDouble(value) <= metadata.getMaxPrice();
							
							case "tags" -> metadata.getTags() != null && metadata.getTags().contains(value);
							
							default -> value.equalsIgnoreCase(attributes.get(key));
						};
						return match;
			});
			
			
		}).toList();
		
		List<ProductResponseDto> dtos = filteredProducts.stream().map(product -> {
			
			Double discountedCost = discountService.applyDiscountByProduct(product);
			
			return ProductMapper.toProductResponseDto(product, discountedCost);
		}).toList();
		
		return new PageImpl<>(dtos, pageable, dtos.size());
		
	} 


	@Override
	public Page<ProductResponseDto> getProductByPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
		
		Page<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
		
		if(products.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return products.map(product -> {
			
			Double discountedCost = discountService.applyDiscountByProduct(product);
			
			return ProductMapper.toProductResponseDto(product, discountedCost);
		});
	}


	@Override
	public Page<ProductResponseDto> getAllProductsSortedByName(Pageable pageable) {
				
		Page<Product> products = productRepository.findAll(pageable);
		
		if(products.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return products.map(product -> {
			
			Double discountedCost = discountService.applyDiscountByProduct(product);
			return ProductMapper.toProductResponseDto(product, discountedCost);
		});
	}

}
