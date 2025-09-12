package risrchanish.product.recommend.dto.featurevector;

import java.util.Arrays;

import jakarta.validation.constraints.NotNull;

public class FeatureVectorRequestDto {

	@NotNull(message = "Feature vector is required")
	private double[] featureVector;

	public double[] getFeatureVector() {
		return featureVector;
	}

	public void setFeatureVector(double[] featureVector) {
		this.featureVector = featureVector;
	}

	@Override
	public String toString() {
		return "FeatureVectorRequestDto [featureVector=" + Arrays.toString(featureVector) + "]";
	}
	
	
}
