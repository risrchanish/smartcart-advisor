package risrchanish.product.recommend.service.Impl;

import java.util.Collections;
import java.util.Comparator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductFeature;
import risrchanish.product.recommend.entity.ProductMetadata;
import risrchanish.product.recommend.entity.ProductSearchCriteria;
import risrchanish.product.recommend.mapper.ProductMapper;
import risrchanish.product.recommend.repository.ProductFeatureRepository;
import risrchanish.product.recommend.repository.ProductMetadataRepository;
import risrchanish.product.recommend.repository.ProductRepository;
import risrchanish.product.recommend.service.DiscountRuleService;
import risrchanish.product.recommend.service.ProductSearchCriteriaService;
import risrchanish.product.recommend.util.Util;

@Service
public class ProductSearchCriteriaServiceImpl implements ProductSearchCriteriaService{
	
	private ProductMetadataRepository productMetadataRepository;
	private ProductRepository productRepository;
	private DiscountRuleService discountService;
	private ProductFeatureRepository featureRepository;
	
	
	public ProductSearchCriteriaServiceImpl(ProductMetadataRepository productMetadataRepository,
												ProductRepository productRepository,
												DiscountRuleService discountService	)
	{
		this.productMetadataRepository = productMetadataRepository;
		this.productRepository = productRepository;
		this.discountService = discountService;
	}

	
	// Search Product by metadata field
	
	@Override
	public Page<ProductResponseDto> searchByMetadataField(String fieldName, Set<String> values, Pageable pageable) 
	{
		
		if(fieldName == null || fieldName.isEmpty())
		{
			throw new IllegalArgumentException("Field name must not be null or blank");
		}
		
		if(values == null || values.isEmpty())
		{
			throw new IllegalArgumentException("Search by list Values must not be null or empty");
		}
		
		Page<ProductMetadata> metadatas = productMetadataRepository.findAll(pageable);									
		
		String lowerCaseField = fieldName.toLowerCase();
		
		
		// common parsing for min and max. matching the filedValues and converting the values to double. 
		// In case of non numeric strings wrapping in try catch block
		
		Set<Double> doubleValues = lowerCaseField.equals("minprice") 
				|| lowerCaseField.equals("maxprice") 
				? values.stream().map(value -> {
					try {
						return Double.parseDouble(value); 
					}
					catch(NumberFormatException e)
					{
						return null;
					}
				})
						.filter(val -> Objects.nonNull(val)) // filtering to avoid null
						.collect(Collectors.toSet()) 
				: Collections.emptySet();

		
		List<Product> productList = metadatas.stream().filter(metadata ->{
				
				
			return switch(lowerCaseField)
					{
						case "brand" -> values.contains(metadata.getBrand());
				
						case "color" -> values.contains(metadata.getColor());
				
						case "material" -> values.contains(metadata.getMaterial());
				
						case "size" -> values.contains(metadata.getSize());
				
						case "tags" -> metadata.getTags() != null && metadata.getTags().stream()
										.anyMatch(tag -> values.contains(tag));								
						
						case "minprice" -> doubleValues.contains(metadata.getMinPrice());
						
						case "maxprice" -> doubleValues.contains(metadata.getMaxPrice());
						
						case "attributes" -> metadata.getAdditionalAttributes() != null &&
							    metadata.getAdditionalAttributes().entrySet().stream()
							        .anyMatch(entry -> values.contains(entry.getKey()) 
							        		|| values.contains(entry.getValue()));
				
						default -> false;
					};
		}).map(metadata -> metadata.getProduct()).collect(Collectors.toList());
		
		int start = (int)pageable.getOffset();
		
		int end = Math.min(start + pageable.getPageSize(), productList.size());
		
		List<Product> pagedProducts = productList.subList(start, end);
		
		List<ProductResponseDto> pagedDtos = pagedProducts.stream()
												.map(product -> {
								double discountedCost = discountService.applyDiscountByProduct(product.getProductId());
								return ProductMapper.toProductResponseDto(product, discountedCost);
												}).toList();
		return new PageImpl<ProductResponseDto>(pagedDtos, pageable, productList.size());
	
	}
	
	// Find products by availability

	@Override
	public Page<ProductResponseDto> getProductsByAvailability(boolean inStock, Pageable pageable) {
		
		Page<Product> products = productRepository.findByInStock(inStock, pageable);
		
		if(products.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return products.map(product -> {
			Double discountedCost = discountService.applyDiscountByProduct(product.getProductId());
			return ProductMapper.toProductResponseDto(product, discountedCost);
		});
	}
	
	
	// get Product features by similarity

	@Override
	public Page<ProductResponseDto> getProductsByFeatureVectorSimilarity(double[] featureVector, Pageable pageable) {
		
		// validating input vector must not be null or empty
		
		if(featureVector == null || featureVector.length == 0)
		{
			throw new IllegalArgumentException("Feature vector must not be null or empty");
		}
		
		// fetch list of all product features from repository.
		
		List<ProductFeature> features = featureRepository.findAll();
		
		// Filtering with null vectors any mismatch
		// Sort remaining entries by cosine similarity to the input vector
		// Mapping to associated products
		
		List<Product> sortedProductList = features.stream().filter(feature ->
			
		feature.getFeatureVector() != null && feature.getFeatureVector().size() == featureVector.length)
				.sorted(Comparator.comparingDouble(feature -> {
					
					double[] vector = feature.getFeatureVector().stream()
										.mapToDouble(dValue -> dValue.doubleValue()).toArray();
					
					return Util.cosineSimilarity(featureVector,vector); // calculating similarity score
				})).map(productFeature -> productFeature.getProduct()) // extracting associated products
					.filter(value -> Objects.nonNull(value)) // removing null 
					.collect(Collectors.toList());
		
		// Calculating pagination bounds
		int start = (int) pageable.getOffset();
		int end = Math.min(start + pageable.getPageSize(), sortedProductList.size());
		
		// Getting the DTO on the sorted list with discount applied
		List<ProductResponseDto> pagedDtos = sortedProductList.subList(start, end).stream()
											.map(product -> {
											double discountedCost = discountService.applyDiscountByProduct(product.getProductId());
											return ProductMapper.toProductResponseDto(product, discountedCost);
											}).collect(Collectors.toList());
		
		// Returning PageImpl by passing the values
		return new PageImpl<>(pagedDtos, pageable, sortedProductList.size());
		
	}
	
	// get products by combined search criteria

	@Override
	public Page<ProductResponseDto> getProductsByCombinedCriteria(ProductSearchCriteria criteria, Pageable pageable) {
		
		// Null safety for criteria
		
		if(criteria == null)
		{
			throw new IllegalArgumentException("Search criteria must not be null");
		}
		
		// Checking the null safety for all criteria and
		// Converting List to Set for fast lookup and maintain uniqueness. 
		
		Set<String> categoriesSet = Util.safeSet(criteria.getCategories());
		
		Set<String> brandsSet = Util.safeSet(criteria.getBrands());
		
		Set<String> colorsSet = Util.safeSet(criteria.getColors());
		
		Set<String> materialsSet = Util.safeSet(criteria.getMaterials());
		
		Set<String> tagsSet = Util.safeSet(criteria.getTags());
		
		Double minPrice = Util.sanetizePrice(criteria.getMinPrice());
		
		Double maxPrice =  Util.sanetizePrice(criteria.getMaxPrice());
		
		String nameKeyword = criteria.getNameKeyword();
		
		Map<String, String> attributesMap = Util.safeMap(criteria.getAdditionalAttributes());
		
		
		// Fetching all the products
		
		List<Product> productsList = productRepository.findAll();
		
		// Filtering the products based on criteria
		
		List<Product> filteredProduct = productsList.stream()
			    .filter(product -> categoriesSet.isEmpty() || categoriesSet.contains(product.getCategory()))
			    .filter(product -> {
			        List<ProductMetadata> metadataList = Optional.ofNullable(product.getMetadataList())
			            .orElse(Collections.emptyList());

			        return metadataList.stream().anyMatch(metadata -> {
			            boolean matches = true;

			            if (!brandsSet.isEmpty()) {
			                matches &= brandsSet.contains(metadata.getBrand());
			            }
			            if (!colorsSet.isEmpty()) {
			                matches &= colorsSet.contains(metadata.getColor());
			            }
			            if (!materialsSet.isEmpty()) {
			                matches &= materialsSet.contains(metadata.getMaterial());
			            }
			            if (!tagsSet.isEmpty()) {
			                matches &= Optional.ofNullable(metadata.getTags())
			                    .orElse(Collections.emptyList())
			                    .stream()
			                    .anyMatch(tagsSet::contains);
			            }
			            if (minPrice != null) {
			                matches &= metadata.getMinPrice() != null && metadata.getMinPrice() >= minPrice;
			            }
			            if (maxPrice != null) {
			                matches &= metadata.getMaxPrice() != null && metadata.getMaxPrice() <= maxPrice;
			            }
			            if (!attributesMap.isEmpty()) {
			                Map<String, String> attrs = Optional.ofNullable(metadata.getAdditionalAttributes())
			                    .orElse(Collections.emptyMap());
			                matches &= attributesMap.entrySet().stream()
			                    .allMatch(entry -> entry.getValue().equals(attrs.get(entry.getKey())));
			            }

			            return matches;
			        });
			    })
			    .filter(product -> nameKeyword == null || product.getName().toLowerCase().contains(nameKeyword.toLowerCase()))
			    .collect(Collectors.toList());
		
		// applying pagination manually
		
		int start = (int)pageable.getOffset();
		
		int end = Math.min(start + pageable.getPageSize(), filteredProduct.size());
		
		List<Product> pagedProducts = filteredProduct.subList(start, end);
		
		
		// Mapping with DTO and discount logic
		
		List<ProductResponseDto> pagedDtos = pagedProducts.stream()
				.map(product -> {
					double discountedCost = discountService.applyDiscountByProduct(product.getProductId());
					
					return ProductMapper.toProductResponseDto(product, discountedCost);
				}).collect(Collectors.toList());
		
		return new PageImpl<>(pagedDtos, pageable, filteredProduct.size());
		
	}
	
	// get sorted product

	@Override
	public Page<ProductResponseDto> getSortedProducts(Pageable pageable) {
		
		// null check for sort
		Sort sort = pageable.getSort();
		
		Sort safeSort = sort.isSorted() ? sort : sort.unsorted();
		
		Pageable sortedpageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), safeSort);
		
		Page<Product> productPage = productRepository.findAll(sortedpageable);
		
		return productPage.map(product -> {
			
			double discountedCost = discountService.applyDiscountByProduct(product.getProductId());
			
			return ProductMapper.toProductResponseDto(product, discountedCost);
		});
		
	}

	@Override
	public Page<ProductResponseDto> getSimilarProductsForBatch(List<Long> productIds, Pageable pageable) {
		
		if(productIds == null || productIds.isEmpty())
		{
			throw new IllegalArgumentException("Product Id list must not be null or empty");
		}
		
		// fetch input products
		List<Product> inputProducts = productRepository.findAllById(productIds);
		
		
		// Fetch all products for comparison
		List<Product> allProducts = productRepository.findAll();
		
		// Compute similarity and get ranked list
		List<Product> similarProducts = Util.findSimilarProducts(inputProducts, allProducts);
		
		
		// Map to Dto with discount logic
		List<ProductResponseDto> dtoList = similarProducts.stream()
									.map(product ->{
												
									double discountedCost = discountService.applyDiscountByProduct(product.getProductId());
									
									return ProductMapper.toProductResponseDto(product, discountedCost);
								}).collect(Collectors.toList());
		
		
		// Applying pagination manually
		
		int start = (int)pageable.getOffset();
		int end = Math.min(start+pageable.getPageSize(), dtoList.size());
		
		List<ProductResponseDto> pagedList = dtoList.subList(start, end);
		
		return new PageImpl<>(pagedList,pageable,dtoList.size());
		
	}
	
	
	
}
