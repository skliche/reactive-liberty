package product.backend;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/products")
@Produces("application/json")
public class ProductBackendEndpoint  {
	@GET
	public List<String> getProductIndices() {
		return Arrays.asList(new String[]{"2","3","5","12","45", "12","23","45","212","4425"});
	}
	
	@GET
	@Path("{id}")
	public Product get(@PathParam("id") String id) {
		try { Thread.sleep(100); } catch (InterruptedException e) { }
		return 
			new Product.BUILDER()
				.withCategory("Aval").
				withName("Test Product").withId(id).build();
	}
	
	@GET
	@Path("{id}/recommendations")
	public Recommendation getRecommendations(@PathParam("id") String id) {
		return null;
	}
	
}
