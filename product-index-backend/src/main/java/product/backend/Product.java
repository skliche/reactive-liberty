package product.backend;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	String id;
	String name;
	BigDecimal limit;
	BigDecimal balance;
	
	public static class BUILDER {
		String id;
		String name;
		BigDecimal limit;
		BigDecimal balance;
		
		public BUILDER withName(String name) {
			this.name = name;
			return this;
		}
		public BUILDER withId(String id) {
			this.id = id;
			return this;
		} 
		public BUILDER withLimit(BigDecimal limit) {
			this.limit = limit;
			return this;
		}
		
		public BUILDER withBalance(BigDecimal balance) {
			this.balance = balance;
			return this;
		}
		public Product build() {
			Product p = new Product();
			p.id = id;
			p.name = name;
			p.limit = limit;
			p.balance = balance;
			return p;
		}
	}
}
