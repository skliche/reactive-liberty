package product.service.control;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import product.service.application.ProductApplication;

public class GetProductIndicesCommand extends HystrixCommand<List<String>>{
	private static final Logger LOG = LoggerFactory.getLogger(GetProductIndicesCommand.class);
	
	public GetProductIndicesCommand() {
		super(HystrixCommandGroupKey.Factory.asKey("getProductIndices"));
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<String> run() throws Exception {
		List<String> ids = ClientBuilder.newClient()
				.target(ProductApplication.PRODUCT_INDEX_BASE_URL)
				.request()
				.get(List.class);
		
		LOG.info("got the product ids: "  + ids);
		return ids;
	}

}
