package product.service.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;

import product.service.application.ProductApplication;
import product.service.model.Product;
import product.service.model.Recommendation;

@Path("/blocking/products")
@Produces("application/json")	

/**
 * REST endpoint to retrieve products using 'usual' blocking patterns
 */
public class BlockingProductEndpoint  {
	@GET
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		
		List<String> indices = getProductIndices();
		for (String id : indices) {
			Product product = getProduct(id);
			Recommendation recommendation = getRecommendationByProduct(id);
			product.addRecommendation(recommendation);
			products.add(product);
		}

		return products;
	}

	private Recommendation getRecommendationByProduct(String productId) {
		return ClientBuilder.newClient()
			.target(String.format(ProductApplication.PRODUCT_RECOMMENDATION_BASE_URL,productId))
			.request()
			.get(Recommendation.class);
	}

	private Product getProduct(String id) {
		return ClientBuilder.newClient()
			.target(String.format(ProductApplication.PRODUCT_DETAILS_BASE_URL, id))
			.request()
			.get(Product.class);
	}

	@SuppressWarnings("unchecked")
	private List<String> getProductIndices() {
		return ClientBuilder.newClient()
			.target(ProductApplication.PRODUCT_INDEX_BASE_URL)
			.request()
			.get(List.class);
	}
}
