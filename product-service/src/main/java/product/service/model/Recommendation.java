package product.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Recommendation {
	String productId;
	String description;
	
	public Recommendation() {};
	
	public Recommendation(String productId, String description) {
		this.productId = productId;
		this.description = description;
	}
}
