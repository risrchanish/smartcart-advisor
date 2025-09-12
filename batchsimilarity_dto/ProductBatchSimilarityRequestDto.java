package risrchanish.product.recommend.dto.batchsimilarity;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class ProductBatchSimilarityRequestDto {

	@NotNull(message= "Product Id is required")
	private List<Long> productIds;

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

	@Override
	public String toString() {
		return "ProductBatchSimilarityRequestDto [productIds=" + productIds + "]";
	}
	
	
	
}
