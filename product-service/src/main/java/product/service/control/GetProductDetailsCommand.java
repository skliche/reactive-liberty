package product.service.control;

import javax.ws.rs.client.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import product.service.application.ProductApplication;
import product.service.model.Product;

public class GetProductDetailsCommand extends HystrixCommand<Product> {
	private static final Logger LOG = LoggerFactory.getLogger(GetProductDetailsCommand.class);
	
	private String productId;

	public GetProductDetailsCommand(String productId) {
		super(HystrixCommandGroupKey.Factory.asKey("getProductData"));
		this.productId = productId;
	}

	@Override
	protected Product run() throws Exception {
		LOG.info("getting product with id: "  + productId);
		Product p = ClientBuilder.newClient()
						.target(String.format(ProductApplication.PRODUCT_DETAILS_BASE_URL, productId))
						.request()
						.get(Product.class);
		
		LOG.info("got product for id "+ productId + ": "  + p);
		return p;
	}
}
