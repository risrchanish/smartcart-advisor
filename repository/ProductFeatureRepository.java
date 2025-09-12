package risrchanish.product.recommend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import risrchanish.product.recommend.entity.ProductFeature;

public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long>{

	
	//Get all features paginated
	Page<ProductFeature> findAll(Pageable pageable);
	
	ProductFeature findByProduct_ProductId(Long productId);
	
	//Get features by product Id
	Page<ProductFeature> findByProduct_ProductId(Long productId, Pageable pageable);
	
	//Search by category or metadata if needed
	@Query("select pf from ProductFeature pf where pf.product.category = :category")
	Page<ProductFeature> findByProductCategory(String category, Pageable pageable);
	
	boolean existsByProduct_ProductId(Long productId);
}
