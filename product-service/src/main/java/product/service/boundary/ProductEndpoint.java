package product.service.boundary;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import product.service.control.GetProductDetailsCommand;
import product.service.control.GetProductIndicesCommand;
import product.service.control.GetProductRecommendationCommand;
import product.service.model.Product;
import rx.Observable;

@Path("/products")
@Produces("application/json")	

/**
 * REST endpoint to retrieve products using a reactive pattern
 *
 */
public class ProductEndpoint  {
	@GET
	public List<Product> getAll() {
		return 
			new GetProductIndicesCommand().observe()
				.flatMapIterable(id -> id)
				.flatMap(id -> Observable.zip(
						new GetProductDetailsCommand(id).observe(), 
						new GetProductRecommendationCommand(id).observe(),
						(product, recommendation) -> product.addRecommendation(recommendation)
					)
				)
				.toList()
				.toBlocking()
				.single();
	}
}
