package product.service.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ProductApplication extends Application {
	public static final String PRODUCT_DETAILS_BASE_URL = "http://localhost:9080/product-details-backend/backend/api/products/%s";
	public static final String PRODUCT_INDEX_BASE_URL = "http://localhost:9080/product-index-backend/backend/api/products";
	public static final String PRODUCT_RECOMMENDATION_BASE_URL = "http://localhost:9080/product-recommendation-backend/backend/api/products/%s/recommendation";
}
