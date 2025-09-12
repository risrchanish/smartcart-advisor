package risrchanish.product.recommend.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import risrchanish.product.recommend.entity.DiscountRule;

public interface DiscountRuleRepository extends JpaRepository<DiscountRule, Long>{

	Page<DiscountRule> findByProduct_ProductId(Long productId, Pageable pageable);
	
	Page<DiscountRule> findByCategory(String category, Pageable pageable);
	
	
	@Query("""
		    SELECT DISTINCT dr FROM DiscountRule dr
		    JOIN dr.product p
		    JOIN p.metadataList m
		    WHERE dr.category = :category AND m.brand IN :brands
		""")
		Page<DiscountRule> findByCategoryAndMetadataBrandIn(
		    @Param("category") String category,
		    @Param("brands") List<String> brands,
		    Pageable pageable
		);
	
	Page<DiscountRule> findByCategoryAndBrand(String category, String brand, Pageable pageable);
	
	Page<DiscountRule> findByTag(String tag, Pageable pageable);
}
