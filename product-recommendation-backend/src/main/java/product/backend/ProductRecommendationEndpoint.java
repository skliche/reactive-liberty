package product.backend;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/products")
@Produces("application/json")
public class ProductRecommendationEndpoint  {
	Map<String, Product> products;
	{
		products = new HashMap<String, Product>();
		products.put("2", new Product.BUILDER().
				withId("2").withName("Girokonto 101241").
				withBalance(BigDecimal.valueOf(3723)).
				withLimit(BigDecimal.valueOf(2000)).build());
		products.put("3", new Product.BUILDER().
				withId("3").withName("Girokonto 232").
				withBalance(BigDecimal.valueOf(-103)).
				withLimit(BigDecimal.valueOf(100)).build());
		products.put("5", new Product.BUILDER().
				withId("5").withName("Depot").
				withBalance(BigDecimal.valueOf(20000)).build());
		products.put("12", new Product.BUILDER().
				withId("12").withName("Visakarte 12121").
				withBalance(BigDecimal.valueOf(0)).
				withLimit(BigDecimal.valueOf(1500)).build());
		products.put("23", new Product.BUILDER().
				withId("23").withName("Tagesgeld Konto 101241").
				withBalance(BigDecimal.valueOf(30000)).build());
	}
	
	
	@GET
	@Path("{id}/recommendation")
	public Recommendation get(@PathParam("id") String id) {
		try { Thread.sleep(150); } catch (InterruptedException e) { }
	//	if(id.equals("23")) throw new IllegalStateException("something bad happened");
		Recommendation r = new Recommendation(id, "this is a recommendation");
		return r;
	}	
}
