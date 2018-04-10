package org.hochnt.springmvcshop.model;

public class OrderDetailInfo {
	private String productCode;
	private String poductName;
	
	private int quanity;
    private double price;
    private double amount;
	
	public OrderDetailInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPoductName() {
		return poductName;
	}

	public void setPoductName(String poductName) {
		this.poductName = poductName;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
