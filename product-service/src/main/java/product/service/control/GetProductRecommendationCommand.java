package product.service.control;

import javax.ws.rs.client.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import product.service.application.ProductApplication;
import product.service.model.Recommendation;

public class GetProductRecommendationCommand extends HystrixCommand<Recommendation>{
	private static final Logger LOG = LoggerFactory.getLogger(GetProductIndicesCommand.class);
	
	private String productId;
	
	public GetProductRecommendationCommand(String productId) {
		super(HystrixCommandGroupKey.Factory.asKey("getProductRecommendation"));
		this.productId = productId;
	}
	
	@Override
	protected Recommendation run() throws Exception {
		Recommendation recommendation = ClientBuilder.newClient()
				.target(String.format(ProductApplication.PRODUCT_RECOMMENDATION_BASE_URL,productId))
				.request()
				.get(Recommendation.class);
		
		LOG.info("got the recommondation for product id " + productId + ": "  + recommendation);
		return recommendation;
	}

	@Override
	protected Recommendation getFallback() {
		LOG.warn("service returned error. returning fallback");
		return new Recommendation(productId, "This is a fallback recommendation");
	}
}
