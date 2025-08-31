package risrchanish.product.recommend.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.productmetadata.ProductMetadataCreateDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataUpdateDto;
import risrchanish.product.recommend.repository.ProductMetadataRepository;
import risrchanish.product.recommend.service.ProductMetadataService;

@Service
public class ProductMetadataServiceImpl implements ProductMetadataService{
	
	private ProductMetadataRepository productMetadataRepository;

	@Override
	public ProductMetadataResponseDto createProductMetadata(ProductMetadataCreateDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductMetadataResponseDto updateProductMetadata(ProductMetadataUpdateDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductMetadataResponseDto> getAllProductMetadata(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductMetadataResponseDto> searchProductMetadataByBrand(String brand, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductMetadataResponseDto> searchProductMetadataByColor(String color, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductMetadata(Long metadataId) {
		// TODO Auto-generated method stub
		
	}

}
