package risrchanish.product.recommend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import risrchanish.product.recommend.entity.ProductMetadata;

public interface ProductMetadataRepository extends JpaRepository<ProductMetadata, Long>{

	Page<ProductMetadata> findByBrandContainingIgnoreCase(String brand, Pageable pageable);
	
	Page<ProductMetadata> findByColorContainingIgnoreCase(String color, Pageable pageable);
	
	Page<ProductMetadata> findByMaterial(String material, Pageable pageable);
	
	Page<ProductMetadata> findAll(Pageable pageable);
	
	boolean existsByproduct_ProductId(Long productId); // Added this to avoid adding more than one metadata for a single product
	
	
	
}
