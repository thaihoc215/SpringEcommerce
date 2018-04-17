package org.hochnt.springmvcshop.dao;

import org.hochnt.springmvcshop.model.CartInfo;

public interface OrderDAO {

	public void saveOrder(CartInfo cartInfo);
}
