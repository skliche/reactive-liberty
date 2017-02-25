package product.service.control;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import product.service.model.Product;

public class GetProductDataCommand extends HystrixCommand<Product> {
	private static final Logger LOG = LoggerFactory.getLogger(GetProductDataCommand.class);

	private static final String BACKEND_BASE_URL = "http://localhost:9080/product.backend/backend/api/products/";

	private Client client = ClientBuilder.newClient();
	private String productId;

	public GetProductDataCommand(String productId) {
		super(HystrixCommandGroupKey.Factory.asKey("getProductData"));
		this.productId = productId;
	}

	@Override
	protected Product run() throws Exception {
		LOG.info("getting product with id: "  + productId);
		Product p = client.target(BACKEND_BASE_URL + productId).request().get(Product.class);
		
		LOG.info("got product for id "+ productId + ": "  + p);
		return p;
	}
}
