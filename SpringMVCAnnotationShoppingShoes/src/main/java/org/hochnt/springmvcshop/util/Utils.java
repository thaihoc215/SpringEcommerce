package org.hochnt.springmvcshop.util;

import javax.servlet.http.HttpServletRequest;

import org.hochnt.springmvcshop.model.CartInfo;

public class Utils {
	// Thông tin các mặt hàng đã mua, được lưu trữ trong Session.
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
}
