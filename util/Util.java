package risrchanish.product.recommend.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import risrchanish.product.recommend.entity.Product;
import risrchanish.product.recommend.entity.ProductMetadata;

public class Util {

	// Utility method for getProductsByFeatureVectorSimilarity()
	
		public static double cosineSimilarity(double[] firstVector, double[] secondVector)
		{
			double dot = 0.0, normA = 0.0, normB = 0.0;
			
			for(int i = 0; i < firstVector.length; i++)
			{
				dot += firstVector[i] * secondVector[i];
				
				normA += firstVector[i] * firstVector[i];
				
				normB += secondVector[i] * secondVector[i];
			}
			
			return dot / (Math.sqrt(normA) * Math.sqrt(normB));
		}
		
		
		// Utility method for getProductsByCombinedCriteria() for similar Map types
		
		public static <K,V> Map<K,V> safeMap(Map<K,V> inputMap)
		{
			return inputMap == null ? Collections.emptyMap() : inputMap;
		}
		
		// Utility method for similar double prices for getProductsByCombinedCriteria()
		
		public static Double sanetizePrice(Double price)
		{
			return price != null && price >= 0.0 ? price : null;
		}
		
		// Utility methods for converting into Set for getProductsByCombinedCriteria()
		
		public static <T> Set<T> safeSet(List<T> input)
		{
			return input == null ? Collections.emptySet() : new HashSet<>(input);
		}
		
		
		// utility methods for getSimilarProductsForBatch() method
	    
	    private static double categoryScore(String category) 
	    {
	        return category == null ? 0.0 : category.hashCode() % 1000 / 1000.0;
	    }

	    private static double brandScore(String brand) {
	        return brand == null ? 0.0 : brand.hashCode() % 1000 / 1000.0;
	    }

	    private static double colorScore(String color) {
	        return color == null ? 0.0 : color.hashCode() % 1000 / 1000.0;
	    }

	    private static double materialScore(String material) {
	        return material == null ? 0.0 : material.hashCode() % 1000 / 1000.0;
	    }
	    
	    
	    private static double tagDensity(List<String> tags) {
	        return tags == null ? 0.0 : Math.min(tags.size() / 10.0, 1.0);
	    }
	    
	    private static double normalizedPrice(Double price, Double min, Double max) {
	        if (price == null || min == null || max == null || max.equals(min)) return 0.0;
	        return Math.min(Math.max((price - min) / (max - min), 0.0), 1.0);
	    }
		
		
	    public static double[] encode(Product product) 
	    {
	         ProductMetadata metadata = product.getMetadata();

	         return new double[] 
	        		 {
	        				 categoryScore(product.getCategory()),
	        				 brandScore(metadata != null ? metadata.getBrand() : null),
	        				 colorScore(metadata != null ? metadata.getColor() : null),
	        				 materialScore(metadata != null ? metadata.getMaterial() : null),
	        				 tagDensity(metadata != null ? metadata.getTags() : null),
	        				 normalizedPrice(product.getPrice(), metadata != null ? 
	                			metadata.getMinPrice() : null, metadata != null ? metadata.getMaxPrice() : null)
	            };
	        }
	    

		
		// Utility method to be used in get similar products for batch
		
		public static List<Product> findSimilarProducts(List<Product> inputProducts, List<Product> allProducts) 
		{
		    
			if (inputProducts == null || inputProducts.isEmpty()) 
		    {
		    	return Collections.emptyList();
		    }
		    	

		    Set<Long> inputIds = inputProducts.stream()
		    						.map(product -> product.getProductId())
		    							.collect(Collectors.toSet());
		    
		    
		    // Exclude input products from comparison
		    List<Product> ramainingProducts = allProducts.stream()
		    					.filter(p -> !inputIds.contains(p.getProductId()))
		    							.collect(Collectors.toList());

		    Map<Product, Double> similarityScores = new HashMap<>();

		    for (Product product : ramainingProducts) 
		    {
		        double scoreSum = 0.0;
		        
		        for (Product input : inputProducts) {
		        	
		        	double[] inputVector = encode(input);
		        	double[] remainingProductVector = encode(product);
		            scoreSum += cosineSimilarity(inputVector, remainingProductVector);
		        }
		        similarityScores.put(product, scoreSum/inputProducts.size()); // average similarity
		    }

		    // Sort by descending similarity score
		    return similarityScores.entrySet().stream()
		        .sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
		        .map(Map.Entry::getKey)
		        .collect(Collectors.toList());
		}


		
		
}
