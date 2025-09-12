package risrchanish.product.recommend.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import risrchanish.product.recommend.dto.productfeature.ProductFeatureCreateDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureUpdateDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductFeature;

public class ProductFeatureMapper {
	
	// ProductFeatureCreateDto ------> ProductFeature

	public static ProductFeature toProductFeature(ProductFeatureCreateDto dto, Product product)
	{
		ProductFeature feature = new ProductFeature();
		
		feature.setProduct(product);
		feature.setFeatureVector(dto.getFeatureVector());
		
		return feature;
	}
	
	
	// Entity ----------> ProductFeatureResponseDto
	
		public static ProductFeatureResponseDto toFeatureResponseDto(ProductFeature feature)
		{
			
			ProductFeatureResponseDto dto = new ProductFeatureResponseDto(
											feature.getFeatureId(),
											feature.getProduct().getProductId(),
											feature.getFeatureVector()
											
											);
			return dto;
		}
		
		
		// UpdateSto   -----> ProductFeature
		
		public static ProductFeature toUpdateProductFeature(ProductFeature feature, ProductFeatureUpdateDto dto, Product product)
		{
			
			List<Double> features = Optional.ofNullable(dto.getFeatureVector())
										.map(list -> new ArrayList<>(list)).orElse(new ArrayList<>());
			
			feature.setFeatureVector(features);
			feature.setProduct(product);
			
			
			return feature;
			
		}
	
}
