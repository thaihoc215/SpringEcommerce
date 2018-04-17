package org.hochnt.springmvcshop.util;

import javax.servlet.http.HttpServletRequest;

import org.hochnt.springmvcshop.model.CartInfo;

public class Utils {
	/**
	 * Thông tin các mặt hàng đã mua, được lưu trữ trong Session.
	 * @param request
	 * @return
	 */
	public static CartInfo getCartInSession(HttpServletRequest request) {

		// Thông tin giỏ hàng có thể đã lưu vào trong Session trước đó.
		CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");

		// tao gio hang neu gio hang trong session = null
		if (cartInfo == null) {
			cartInfo = new CartInfo();

			// Và lưu vào trong session.
			request.getSession().setAttribute("myCart", cartInfo);
		}

		return cartInfo;
	}
	
	/**
	 * Xoa gio hang ra khoi session
	 * @param request
	 */
	public static void removeCartInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("myCart");
	}
	
	/**
	 * Lưu giỏ hàng cuối cùng đã order trong session hiện tại
	 * @param request
	 */
	public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
		request.getSession().setAttribute("lastOrderedCart", cartInfo);
	}
	
	/**
	 * Lấy giỏ hàng đã order cuối cùng trong session
	 * 
	 * @param request
	 * @return
	 */
	public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
		return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
	}
}
