package product.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	String id;
	String name;
	String category;
	
	public static class BUILDER {
		String id;
		String name;
		String category;
		
		public BUILDER withName(String name) {
			this.name = name;
			return this;
		}
		public BUILDER withId(String id) {
			this.id = id;
			return this;
		} 
		public BUILDER withCategory(String category) {
			this.category = category;
			return this;
		}
		public Product build() {
			Product p = new Product();
			p.id = id;
			p.name = name;
			p.category = category;
			return p;
		}
	}
}
