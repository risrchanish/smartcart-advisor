package risrchanish.product.recommend.mapper;

import risrchanish.product.recommend.dto.productfeature.ProductFeatureCreateDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductFeature;

public class ProductFeatureMapper {
	
	// ProductFeatureCreateDto ------> ProductFeature

	public static ProductFeature toDto(ProductFeatureCreateDto dto, Product product)
	{
		ProductFeature feature = new ProductFeature();
		
		feature.setProduct(product);
		feature.setFeatureVector(dto.getFeatureVector());
		
		return feature;
	}
	
	
	
	// ProductFeatureResponseDto  --------->  ProductFeature
	
	public static ProductFeature toProductFeature(ProductFeatureResponseDto dto, Product product)
	{
		ProductFeature feature = new ProductFeature();
		
		feature.setProduct(product);
		feature.setFeatureVector(dto.featureVector());
		
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
	
}
