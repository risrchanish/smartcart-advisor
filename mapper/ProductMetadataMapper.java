package risrchanish.product.recommend.mapper;

import risrchanish.product.recommend.dto.productmetadata.ProductMetadataCreateDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductMetadata;

public class ProductMetadataMapper {

//	MetadataCreateDto  ----> ProductMetadata
	public static ProductMetadata toProductMetadata(ProductMetadataCreateDto dto, Product product)
	{
		ProductMetadata metadata = new ProductMetadata();
		
		metadata.setAdditionalAttributes(dto.getAdditionalAttributes());
		metadata.setBrand(dto.getBrand());
		metadata.setColor(dto.getColor());
		metadata.setMaterial(dto.getMaterial());
		metadata.setMaxPrice(dto.getMaxPrice());
		metadata.setMinPrice(dto.getMinPrice());
		metadata.setProduct(product);
		metadata.setSize(dto.getSize());
		metadata.setTags(dto.getTags());
		
		return metadata;
	}
	
//	ProductMetadata ----------> ProductMetadataResponseDto
	
	public static ProductMetadataResponseDto toMetadataResponseDto(ProductMetadata metadata)
	{
		ProductMetadataResponseDto dto = new ProductMetadataResponseDto(
												
												metadata.getMetadataId(),
												metadata.getBrand(),
												metadata.getColor(),
												metadata.getSize(),
												metadata.getMaterial(),
												metadata.getTags(),
												metadata.getAdditionalAttributes()
											);
		return dto;
	}
}
