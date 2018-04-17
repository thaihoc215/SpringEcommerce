package org.hochnt.springmvcshop.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hochnt.springmvcshop.dao.OrderDAO;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.hochnt.springmvcshop.entity.Order;
import org.hochnt.springmvcshop.entity.OrderDetail;
import org.hochnt.springmvcshop.entity.Product;
import org.hochnt.springmvcshop.model.CartInfo;
import org.hochnt.springmvcshop.model.CartLineInfo;
import org.hochnt.springmvcshop.model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//Transactional for Hibernate
@Transactional
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductDAO productDAO;
	/**
	 * Lay so thu tu order lon nhat hien hanh
	 */
	private int getMaxOrderNum() {
		String sql = "Select max(o.orderNum) from " + Order.class.getName() + " o ";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Number> query = session.createQuery(sql);
		Integer value = (Integer) query.uniqueResult();
		return value == null ? 0 : value;
	}

	@Override
	public void saveOrder(CartInfo cartInfo) {
		Session session = sessionFactory.getCurrentSession();
		int orderNum = getMaxOrderNum();
		Order order = new Order();
		
		order.setId(UUID.randomUUID().toString());
		order.setAmount(cartInfo.getAmountTotal());
		order.setOrderDate(new Date());
		order.setOrderNum(orderNum);
		//thong tin customer cua don hang
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		order.setCustomerAddress(customerInfo.getAddress());
		order.setCustomerEmail(customerInfo.getEmail());
		order.setCustomerName(customerInfo.getName());
		order.setCustomerPhone(customerInfo.getPhone());
		
		session.persist(order);
		
		//Luu chi tiet don hang (order details)
		List<CartLineInfo> lines = cartInfo.getCartLines();
		for (CartLineInfo cartLineInfo : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setId(UUID.randomUUID().toString());
			detail.setOrder(order);
			detail.setAmmount(cartLineInfo.getAmount());
			detail.setPrice(cartLineInfo.getProductInfo().getPrice());
			detail.setQuanity(cartLineInfo.getQuantity());
			
			//set product of details
			Product product = productDAO.findProduct(cartLineInfo.getProductInfo().getCode());
			detail.setProduct(product);
			
			//luu vao db sau khi ket thuc method do co dinh nghia transaction
			session.persist(detail);
		}
		
		// Set OrderNum for report.
        // Set OrderNum để thông báo cho người dùng.
        cartInfo.setOrderNum(orderNum);
	}
}
