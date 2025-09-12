package risrchanish.product.recommend.service.Impl;


import java.awt.event.FocusEvent.Cause;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.productmetadata.ProductMetadataCreateDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataResponseDto;
import risrchanish.product.recommend.dto.productmetadata.ProductMetadataUpdateDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductMetadata;
import risrchanish.product.recommend.exception.DuplicateMetadataException;
import risrchanish.product.recommend.exception.ResourceNotFoundException;
import risrchanish.product.recommend.mapper.ProductMetadataMapper;
import risrchanish.product.recommend.repository.ProductMetadataRepository;
import risrchanish.product.recommend.repository.ProductRepository;
import risrchanish.product.recommend.service.ProductMetadataService;

@Service
public class ProductMetadataServiceImpl implements ProductMetadataService{
	
	private ProductMetadataRepository productMetadataRepository;
	private ProductRepository productRepository;
	
	public ProductMetadataServiceImpl(ProductMetadataRepository productMetadataRepository,ProductRepository productRepository)
	{
		this.productMetadataRepository = productMetadataRepository;
		this.productRepository = productRepository;
	}
	
	
	// create metadata 

	@Override
	public ProductMetadataResponseDto createProductMetadata(ProductMetadataCreateDto dto) 
	{
		
		if(dto.getProductId() == null)
		{
			throw new IllegalArgumentException("Product must not be null");
		}
		
		if(productMetadataRepository.existsByproduct_ProductId(dto.getProductId()))
		{
			throw new DuplicateMetadataException
					("Product Metadata already exists for productId: "+dto.getProductId());
			
		}
		
		/*
		 * Written logic below for the above db check.
		
		List<ProductMetadata> metadataList = productMetadataRepository.findAll(); // this is O(n)

		
		 // O(n) fetch and scan — acceptable for small data sets, but we have to consider DB-level check for scale
		
		boolean exists = metadataList.stream()
				.anyMatch(metadata -> metadata.getProduct().getProductId().equals(dto.getProductId())); 

		if(exists)
		{
			throw new IllegalArgumentException("Product Metadata already exists for productId: "+dto.getProductId());
		}	
		
	*/
		Product product = productRepository.findById(dto.getProductId())
								.orElseThrow(() -> new ResourceNotFoundException("Product is not available"));
		
		ProductMetadata metadata = ProductMetadataMapper.toProductMetadata(dto, product);
		
		productMetadataRepository.save(metadata);
		
		return ProductMetadataMapper.toMetadataResponseDto(metadata);	


	}
	
	// Update Product Metadata 

	@Override
	public ProductMetadataResponseDto updateProductMetadata(ProductMetadataUpdateDto dto) {
		
		if(dto.getMetadataId() == null)
		{
			throw new IllegalArgumentException("Product Metadata Id must not be null");
		}
		
		ProductMetadata oldMetadata = productMetadataRepository.findById(dto.getMetadataId())
									.orElseThrow(() -> new ResourceNotFoundException("Metadata not found"));
		
		Product product = Optional.ofNullable(oldMetadata.getProduct())
									.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		
		ProductMetadata updatedMetadata = ProductMetadataMapper.toUpdateProductMetadata(oldMetadata, dto, product);
		
		productMetadataRepository.save(updatedMetadata);
		
		return ProductMetadataMapper.toMetadataResponseDto(updatedMetadata);
				
	}
	
	// Get all products metadata

	@Override
	public Page<ProductMetadataResponseDto> getAllProductMetadata(Pageable pageable) 
	{
		
		Page<ProductMetadata> metadatas = productMetadataRepository.findAll(pageable);
		
		if(metadatas.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return metadatas.map(metadata -> ProductMetadataMapper.toMetadataResponseDto(metadata));
	}
	
	// Search metadata by brand

	@Override
	public Page<ProductMetadataResponseDto> searchProductMetadataByBrand(String brand, Pageable pageable) {
		
		if(brand == null|| brand.isEmpty())
		{
			throw new IllegalArgumentException("Brand is required");
		}
		
		Page<ProductMetadata> metadatas = productMetadataRepository.findByBrandContainingIgnoreCase(brand, pageable);
		
		
		if(metadatas.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return metadatas.map(metadata -> ProductMetadataMapper.toMetadataResponseDto(metadata));
	}
	
	// Search metadata by color

	@Override
	public Page<ProductMetadataResponseDto> searchProductMetadataByColor(String color, Pageable pageable) {
		
		if(color == null || color.isEmpty())
		{
			throw new IllegalArgumentException("Color is required");
		}
		
		Page<ProductMetadata> metadatas = productMetadataRepository.findByColorContainingIgnoreCase(color, pageable);
		
		if(metadatas.isEmpty())
		{
			return Page.empty(pageable);
		}
		
		return metadatas.map(metadata -> ProductMetadataMapper.toMetadataResponseDto(metadata));
	}

	@Override
	public void deleteProductMetadata(Long metadataId) {
		
		if(metadataId == null)
		{
			throw new IllegalArgumentException("Product Metadata Id is required");
		}
		
		ProductMetadata metadata = productMetadataRepository.findById(metadataId)
										.orElseThrow(() -> new ResourceNotFoundException("Product Metadata not found"));
		
		productMetadataRepository.delete(metadata);
		
		
	}

}
