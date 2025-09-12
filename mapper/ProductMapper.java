package risrchanish.product.recommend.mapper;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import risrchanish.product.recommend.dto.product.ProductCreateDto;
import risrchanish.product.recommend.dto.product.ProductResponseDto;
import risrchanish.product.recommend.dto.product.ProductUpdateDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.dto.rating.RatingResponseDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductFeature;
import risrchanish.product.recommend.entity.ProductMetadata;

public class ProductMapper {

	
	// CreateDto  ---> Product
	public static Product toProduct(ProductCreateDto dto)
	{
		
		Product product = new Product();
		
	
		// List<ProductFeatureCreateDto>  to   List<ProductFeature>
		
		List<ProductFeature> featuresList = Optional.ofNullable(dto.getFeatures()).orElse(Collections.emptyList())
											.stream()
											.map(featureCreateDto -> 
											ProductFeatureMapper.toProductFeature(featureCreateDto, product))
											.collect(Collectors.toList());
												
		
		List<ProductMetadata> metadataList = Optional.ofNullable(dto.getMetadataList()).orElse(Collections.emptyList())
												.stream()
												.map(metadataCreateDto -> 
												ProductMetadataMapper.toProductMetadata(metadataCreateDto, product))
												.collect(Collectors.toList());
		
		product.setName(dto.getName());
		product.setCategory(dto.getCategory());
		product.setPrice(dto.getPrice());
		product.setInStock(dto.isInStock());
		product.setFeatures(featuresList); 
		product.setMetadataList(metadataList);
		// We are not setting here because it has Product to set and we will set it in service layer
		return product;		
		
	}
	
	
	// Entity ----> ResponseDto
	
	public static ProductResponseDto toProductResponseDto(Product product, Double discountedPrice)
	{
		// List<ProductFeature> to List<ProductFeatureResponseDto>
		List<ProductFeatureResponseDto>	featureDto = Optional.ofNullable(product.getFeatures()).orElse(Collections.emptyList())
														.stream().map(feature -> ProductFeatureMapper.toFeatureResponseDto(feature))
														.collect(Collectors.toList());
		
		 
		List<RatingResponseDto> ratingsList = Optional.ofNullable(product.getRatings()).orElse(Collections.emptyList())
												.stream().map(rating -> RatingMapper.toRatingResponseDto(rating))
												.collect(Collectors.toList());
		
		
		List<ProductMetadataResponseDto> metadataList = Optional.ofNullable(product.getMetadataList())
											.orElse(Collections.emptyList())
											.stream().map(metadata -> 
											ProductMetadataMapper.toMetadataResponseDto(metadata))
											.collect(Collectors.toList());	
		
		
				ProductResponseDto dto = new ProductResponseDto(
				
					product.getProductId(),
					product.getName(),
					product.getPrice(),
					product.getCategory(),
					discountedPrice,
					metadataList,
					product.isInStock(),
					ratingsList,
					featureDto							
				);
		return dto;
	}
	
	// UpdateDto -------> Product
	
	public static Product toProduct(Product product, ProductUpdateDto dto)
	{
		product.setCategory(dto.getCategory());
		product.setInStock(dto.isInStock());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());	
		return product;
		
	}
	
	
}
