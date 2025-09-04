package risrchanish.product.recommend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import risrchanish.product.recommend.entity.DiscountRule;

public interface DiscountRuleRepository extends JpaRepository<DiscountRule, Long>{

	Page<DiscountRule> findByProduct_ProductId(Long productId, Pageable pageable);
	
	Page<DiscountRule> findByCategory(String category, Pageable pageable);
	
	Page<DiscountRule> findByCategoryAndBrand(String category, String brand, Pageable pageable);
	
	Page<DiscountRule> findByTag(String tag, Pageable pageable);
}
