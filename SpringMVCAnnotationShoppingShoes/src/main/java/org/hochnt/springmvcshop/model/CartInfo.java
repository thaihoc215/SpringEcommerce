package org.hochnt.springmvcshop.model;

import java.util.ArrayList;
import java.util.List;

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
	 * 
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
	 * 
	 * @param productInfo:
	 *            thông tin sản phẩm
	 * @param quantity:
	 *            số lượng
	 */
	public void addProduct(ProductInfo productInfo, int quantity) {

		CartLineInfo cartLineInfo = findLineByCode(productInfo.getCode());

		if (cartLineInfo == null) {
			cartLineInfo = new CartLineInfo();
			cartLineInfo.setQuantity(0);
			cartLineInfo.setProductInfo(productInfo);
			this.cartLines.add(cartLineInfo);
		}

		int newQuantity = cartLineInfo.getQuantity() + quantity;
		if (newQuantity <= 0) {
			this.cartLines.remove(cartLineInfo);
		} else {
			cartLineInfo.setQuantity(newQuantity);
		}
	}

	public void validate() {

	}

	/**
	 * Update so san pham trong gio hang
	 * 
	 * @param code
	 * @param quantity
	 */
	private void updateProduct(String code, int quantity) {
		CartLineInfo line = this.findLineByCode(code);

		if (line != null) {
			if (quantity <= 0) {
				this.cartLines.remove(line);
			} else {
				line.setQuantity(quantity);
			}
		}
	}

	/**
	 * Xoa san pham trong gio hang
	 * 
	 * @param productInfo
	 */
	public void removeProduct(ProductInfo productInfo) {
		CartLineInfo line = this.findLineByCode(productInfo.getCode());
		if (line != null) {
			this.cartLines.remove(line);
		}
	}

	/**
	 * Update so luong san pham trong gio hang
	 * 
	 * @param cartForm
	 */
	public void updateQuantity(CartInfo cartForm) {
		if (cartForm != null) {
			List<CartLineInfo> lst = cartForm.getCartLines();
			for (CartLineInfo line : lst) {
				this.updateProduct(line.getProductInfo().getCode(), line.getQuantity());
			}
		}
	}

	// kiem tra gio hang trong
	public boolean isEmpty() {
		return this.cartLines.isEmpty();
	}

	public boolean isValidCustomer() {
		return this.customerInfo != null && this.customerInfo.isValid();
	}

	//Lay tong so luong san pham trong gio hang
	public int getQuantityTotal() {
		int quantity = 0;
		for (CartLineInfo line : this.cartLines) {
			quantity += line.getQuantity();
		}
		return quantity;
	}

	
	//Lay tong so tien trong gio hang
	public double getAmountTotal() {
		double total = 0;
		for (CartLineInfo line : this.cartLines) {
			total += line.getAmount();
		}
		return total;
	}
}
