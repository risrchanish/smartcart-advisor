package risrchanish.product.recommend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import risrchanish.product.recommend.dto.productmetadata.ProductMetadataCreateDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataUpdateDto;

public interface ProductMetadataService {

	ProductMetadataResponseDto createProductMetadata(ProductMetadataCreateDto dto);
	
	ProductMetadataResponseDto updateProductMetadata(ProductMetadataUpdateDto dto);
	
	Page<ProductMetadataResponseDto> getAllProductMetadata(Pageable pageable);
	
	Page<ProductMetadataResponseDto> searchProductMetadataByBrand(String brand, Pageable pageable);
	
	Page<ProductMetadataResponseDto> searchProductMetadataByColor(String color, Pageable pageable);
	
	void deleteProductMetadata(Long metadataId);
	
	
}
