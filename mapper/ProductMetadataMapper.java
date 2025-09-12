package risrchanish.product.recommend.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import risrchanish.product.recommend.dto.productmetadata.ProductMetadataCreateDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataUpdateDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductMetadata;

public class ProductMetadataMapper {

//	MetadataCreateDto  ----> ProductMetadata
	public static ProductMetadata toProductMetadata(ProductMetadataCreateDto dto, Product product)
	{
		
		if(dto == null)
		{
			return null;
		}
		
		ProductMetadata metadata = new ProductMetadata();
		
		
		metadata.setAdditionalAttributes(
			    Optional.ofNullable(dto.getAdditionalAttributes())
			            .map(map -> new HashMap<>(map))
			            .orElse(new HashMap<>())
			);

			metadata.setTags(
			    Optional.ofNullable(dto.getTags())
			            .map(list -> new ArrayList<>(list))
			            .orElse(new ArrayList<>())
			);	
			
			
			
		metadata.setBrand(dto.getBrand());
		metadata.setColor(dto.getColor());
		metadata.setMaterial(dto.getMaterial());
		metadata.setMaxPrice(dto.getMaxPrice());
		metadata.setMinPrice(dto.getMinPrice());
		metadata.setProduct(product);
		metadata.setSize(dto.getSize());
		
		
		return metadata;
	}
	
	
	// List type mapper overloaded
	
	public static List<ProductMetadata> toProductMetadata(List<ProductMetadataCreateDto> dtoList, Product product) 
	{
	    return Optional.ofNullable(dtoList)
	        .orElse(Collections.emptyList())
	        .stream()
	        .map(dto -> toProductMetadata(dto, product)) 
	        .collect(Collectors.toList());
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
												metadata.getMaxPrice(),
												metadata.getMinPrice(),
												Optional.ofNullable(metadata.getTags())
												.map(list -> new ArrayList<>(list)).orElse(new ArrayList<>()),
												Optional.ofNullable(metadata.getAdditionalAttributes())
												.map(map -> new HashMap<>(map)).orElse(new HashMap<>()));
											
		return dto;
	}
	
	// UpdateDto  -----> toProductMetadata
	public static ProductMetadata toUpdateProductMetadata(ProductMetadata metadata, ProductMetadataUpdateDto dto, Product product)
	{
		metadata.setBrand(dto.getBrand());
		metadata.setColor(dto.getColor());
		metadata.setMaterial(dto.getMaterial());
		metadata.setMaxPrice(dto.getMaxPrice());
		metadata.setMinPrice(dto.getMinPrice());
		metadata.setSize(dto.getSize());
		metadata.setProduct(product);
		metadata.setAdditionalAttributes(Optional.ofNullable(dto.getAdditionalAttributes()).orElse(Collections.emptyMap()));
		metadata.setTags(Optional.ofNullable(dto.getTags()).orElse(Collections.emptyList()));
		
		return metadata;
	}
}
