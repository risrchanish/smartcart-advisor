package risrchanish.product.recommend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import risrchanish.product.recommend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findByName(String name, Pageable pageable);
	
	Page<Product> findByCategory(String category, Pageable pageable);
	
	Page<Product> findByPrice(Double price, Pageable pageable);
	
	Page<Product> findByPriceBetween(Double minimum, Double maximum, Pageable pageable);
	
	Page<Product> findByInStock(boolean inStock, Pageable pageable);
	
}
