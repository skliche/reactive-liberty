package product.service.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import product.service.control.GetProductDataCommand;
import product.service.control.GetProductIndicesCommand;
import product.service.model.Product;

@Path("/products")
@Produces("application/json")	

/**
 * REST endpoint to retrieve products using a reactive pattern
 *
 */
@Stateless
public class ProductEndpoint  {
	@GET
	public List<Product> getAll() {
		return 
			new GetProductIndicesCommand().observe()
				.flatMapIterable(id -> id)
				.flatMap(id -> new GetProductDataCommand(id).observe())
				.toList()
				.toBlocking()
				.single();
	}
	
	@GET
	@Path("{id}")
	public Product get(String id) {
		return 
			new GetProductDataCommand(id).observe()
				.toBlocking()
				.single();
	}
}
