package org.hochnt.springmvcshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;

public class CartInfo {
	private int orderNum;
	private CustomerInfo customerInfo;
	// danh sach san pham trong cart: san pham + so luong
	private final List<CartLineInfo> cartLines = new ArrayList<>();

	public CartInfo() {
		// TODO Auto-generated constructor stub
	}
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<CartLineInfo> getCartLines() {
		return cartLines;
	}
	
	/**
	 * Tìm một sản phẩm trong giỏ hàng
	 * @param code
	 * @return
	 */
	private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProductInfo().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }
	
	/**
	 * Thêm một sản phẩm vào giỏ hàng
	 * @param productInfo: thông tin sản phẩm
	 * @param quantity: số lượng
	 */
	public void addProduct(ProductInfo productInfo, int quantity) {
		
		CartLineInfo cartLineInfo = findLineByCode(productInfo.getCode());
		
		if(cartLineInfo == null) {
			cartLineInfo = new CartLineInfo();
			cartLineInfo.setQuantity(quantity);
			cartLineInfo.setProductInfo(productInfo);
			this.cartLines.add(cartLineInfo);
		}
		
		 int newQuantity = cartLineInfo.getQuantity() + quantity;
		 if(newQuantity <= 0) {
			 this.cartLines.remove(cartLineInfo);
		 }else {
			 cartLineInfo.setQuantity(newQuantity);
		 }
	}
	
	public void validate() {
		 
    }
}
