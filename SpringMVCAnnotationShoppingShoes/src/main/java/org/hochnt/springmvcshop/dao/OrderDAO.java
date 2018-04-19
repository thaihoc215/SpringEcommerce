package org.hochnt.springmvcshop.dao;

import java.util.List;

import org.hochnt.springmvcshop.model.CartInfo;
import org.hochnt.springmvcshop.model.OrderDetailInfo;
import org.hochnt.springmvcshop.model.OrderInfo;
import org.hochnt.springmvcshop.model.PaginationResult;

public interface OrderDAO {

	/**
	 * Luu don dat hang
	 * @param cartInfo
	 */
	public void saveOrder(CartInfo cartInfo);

	/**
	 * Lay danh sach don hang co phan trang
	 * @param page
	 * @param mAX_RESULT
	 * @param mAX_NAVIGATION_PAGE
	 * @return
	 */
	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);

	/**
	 * Lay thong tin don hang
	 * @param orderId
	 * @return
	 */
	public OrderInfo getOrderInfo(String orderId);

	/**
	 * Lay chi tiet don hang cua don hang
	 * @param orderId
	 * @return
	 */
	public List<OrderDetailInfo> listOrderDetailInfo(String orderId);
	
}
