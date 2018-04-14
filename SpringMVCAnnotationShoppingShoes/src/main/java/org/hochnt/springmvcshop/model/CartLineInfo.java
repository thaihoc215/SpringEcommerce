package org.hochnt.springmvcshop.model;

public class CartLineInfo {
	ProductInfo productInfo;
	int quantity;
	
	public CartLineInfo() {
		// TODO Auto-generated constructor stub
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo product) {
		this.productInfo = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
