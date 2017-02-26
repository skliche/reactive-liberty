package product.service.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import product.service.control.GetProductDataCommand;
import product.service.control.GetProductIndicesCommand;
import product.service.model.Product;

@Path("/blocking/products")
@Produces("application/json")	

/**
 * REST endpoint to retrieve products using 'usual' blocking patterns
 */
@Stateless
public class BlockingProductEndpoint  {
	@GET
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		
		List<String> indices = new GetProductIndicesCommand().execute();
		for (String id : indices) {
			Product product = new GetProductDataCommand(id).execute();
			products.add(product);
		}

		return products;
	}
	
	@GET
	@Path("{id}")
	public Product get(String id) {
		return new GetProductDataCommand(id).execute();
	}
}
