package product.service.control;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GetProductIndicesCommand extends HystrixCommand<List<String>>{
	private static final Logger LOG = LoggerFactory.getLogger(GetProductIndicesCommand.class);
	private static final String BACKEND_BASE_URL = "http://localhost:9080/product.backend/backend/api/products";
	
	private Client client = ClientBuilder.newClient();
	
	public GetProductIndicesCommand() {
		super(HystrixCommandGroupKey.Factory.asKey("getProductIndices"));
	}

	@Override
	protected List<String> run() throws Exception {
		List<String> ids = client.target(BACKEND_BASE_URL).request().get(List.class);
		
		LOG.info("got the product ids: "  + ids);
		return ids;
	}

}
