package risrchanish.product.recommend.service.Impl;

import java.util.Collections;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import risrchanish.product.recommend.dto.productfeature.ProductFeatureCreateDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureResponseDto;
import risrchanish.product.recommend.dto.productfeature.ProductFeatureUpdateDto;
import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductFeature;
import risrchanish.product.recommend.exception.DuplicateFeatureVectorException;
import risrchanish.product.recommend.exception.ResourceNotFoundException;
import risrchanish.product.recommend.mapper.ProductFeatureMapper;
import risrchanish.product.recommend.repository.ProductFeatureRepository;
import risrchanish.product.recommend.repository.ProductRepository;
import risrchanish.product.recommend.service.ProductFeatureService;

@Service
public class ProductFeatureServiceImpl implements ProductFeatureService{
	
	private ProductFeatureRepository productFeatureRepository;
	private ProductRepository productRepository; 
	
	public ProductFeatureServiceImpl(ProductFeatureRepository productFeatureRepository, ProductRepository productRepository)
	{
		this.productFeatureRepository = productFeatureRepository;
		this.productRepository = productRepository;
	}
	
	// Creating Product Feature

	@Override
	public ProductFeatureResponseDto createProductFeature(ProductFeatureCreateDto dto) {
		
		Product product = productRepository.findById(dto.getProductId())
							.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		
		// checking for existing feature vector. There must be only one feature vector list for single product.
		
		boolean exists = productFeatureRepository.existsByProduct_ProductId(dto.getProductId());
		if (exists) {
		    throw new DuplicateFeatureVectorException("Feature vector already exists for productId: " + dto.getProductId());
		}
		
		ProductFeature feature = ProductFeatureMapper.toProductFeature(dto, product);
		
		productFeatureRepository.save(feature);
		
		return ProductFeatureMapper.toFeatureResponseDto(feature);
	}
	
	
	// Updating product feature

	@Override
	public ProductFeatureResponseDto updateProductFeature(Long featureId, ProductFeatureUpdateDto dto) {
		
		if(featureId == null)
		{
			throw new IllegalArgumentException("Feature Id must not be null");
		}
		
		ProductFeature feature = productFeatureRepository.findById(featureId)
									.orElseThrow(() -> new ResourceNotFoundException("Product feature not found"));
		
		if(dto.getProductId() == null)
		{
			throw new ResourceNotFoundException("Id must not be null");
		}
		
		Product product = productRepository.findById(dto.getProductId())
							.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		
		ProductFeature updatedFeature = ProductFeatureMapper.toUpdateProductFeature(feature, dto, product); 
		
		productFeatureRepository.save(updatedFeature); // saving the updated feature
		
		return ProductFeatureMapper.toFeatureResponseDto(updatedFeature);
	}

	// find product feature with Id
	
	@Override
	public ProductFeatureResponseDto getProductFeaturesById(Long featureId) {
		
		if(featureId == null)
		{
			throw new IllegalArgumentException("Feature Id must not be null");
		}
		ProductFeature feature = productFeatureRepository.findById(featureId)
									.orElseThrow(() -> new ResourceNotFoundException("Product feature not found"));
		return ProductFeatureMapper.toFeatureResponseDto(feature);
	}
	
	// Find the product feature

	@Override
	public ProductFeatureResponseDto getProductFeaturesByProductId(Long productId) {
		
		if(productId == null)
		{
			throw new IllegalArgumentException("Product Id must not be null");
		}
		
		ProductFeature feature = Optional.ofNullable(productFeatureRepository.findByProduct_ProductId(productId))
									.orElseThrow(() -> new ResourceNotFoundException("Feature not found"));
		
		return ProductFeatureMapper.toFeatureResponseDto(feature);
		
	}



	// getting all product features
	
	@Override
	public Page<ProductFeatureResponseDto> getAllProductFeatures(Pageable pageable) {
		
		Page<ProductFeature> features = productFeatureRepository.findAll(pageable);
		
		if(features.isEmpty())
		{
			return Page.empty(pageable);
		}
		

		    return features.map(feature -> new ProductFeatureResponseDto(
		        feature.getFeatureId(),
		        feature.getProduct().getProductId(),
		        Optional.ofNullable(feature.getFeatureVector()).orElse(Collections.emptyList())));
		}	
	
	// Delete feature by Id

	@Override
	public void deleteProductFeatureById(Long featureId) {
		
		if(featureId == null)
		{
			throw new IllegalArgumentException("Feature Id not found");
		}
		
		ProductFeature feature = productFeatureRepository.findById(featureId)
									.orElseThrow(() -> new ResourceNotFoundException("Feature not found"));
		
		productFeatureRepository.delete(feature);
	}

}
