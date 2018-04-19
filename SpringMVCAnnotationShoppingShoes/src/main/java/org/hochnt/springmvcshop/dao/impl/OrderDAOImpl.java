package org.hochnt.springmvcshop.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hochnt.springmvcshop.dao.OrderDAO;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.hochnt.springmvcshop.entity.Account;
import org.hochnt.springmvcshop.entity.Order;
import org.hochnt.springmvcshop.entity.OrderDetail;
import org.hochnt.springmvcshop.entity.Product;
import org.hochnt.springmvcshop.model.CartInfo;
import org.hochnt.springmvcshop.model.CartLineInfo;
import org.hochnt.springmvcshop.model.CustomerInfo;
import org.hochnt.springmvcshop.model.OrderDetailInfo;
import org.hochnt.springmvcshop.model.OrderInfo;
import org.hochnt.springmvcshop.model.PaginationResult;
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
		// thong tin customer cua don hang
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		order.setCustomerAddress(customerInfo.getAddress());
		order.setCustomerEmail(customerInfo.getEmail());
		order.setCustomerName(customerInfo.getName());
		order.setCustomerPhone(customerInfo.getPhone());

		session.persist(order);

		// Luu chi tiet don hang (order details)
		List<CartLineInfo> lines = cartInfo.getCartLines();
		for (CartLineInfo cartLineInfo : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setId(UUID.randomUUID().toString());
			detail.setOrder(order);
			detail.setAmount(cartLineInfo.getAmount());
			detail.setPrice(cartLineInfo.getProductInfo().getPrice());
			detail.setQuanity(cartLineInfo.getQuantity());

			// set product of details
			Product product = productDAO.findProduct(cartLineInfo.getProductInfo().getCode());
			detail.setProduct(product);

			// luu vao db sau khi ket thuc method do co dinh nghia transaction
			session.persist(detail);
		}

		// Set OrderNum for report.
		// Set OrderNum để thông báo cho người dùng.
		cartInfo.setOrderNum(orderNum);
	}

	@Override
	/**
	 * Lay danh sach don hang co phan trang
	 * 
	 * @param page
	 * @param mAX_RESULT
	 * @param mAX_NAVIGATION_PAGE
	 * @return
	 */
	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
		String sql = "Select new " + OrderInfo.class.getName() + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
				+ " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
				+ Order.class.getName() + " ord " + " order by ord.orderNum desc";
		Session session = this.sessionFactory.getCurrentSession();

		Query<?> query = session.createQuery(sql);

		return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	/**
	 * Lay thong tin don hang
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderInfo getOrderInfo(String orderId) {
		Order order = this.findOrder(orderId);
		if(order == null)
			return null;
		return new OrderInfo(order);
	}

	private Order findOrder(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Order> query = builder.createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		query.select(root);
		query.where(builder.equal(root.get("id"), orderId));
        return (Order)  session.createQuery(query).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Lay chi tiet don hang cua don hang
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderDetailInfo> listOrderDetailInfo(String orderId) {
		String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId ";
 
        Session session = this.sessionFactory.getCurrentSession();
 
        Query<?> query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
 
        return (List<OrderDetailInfo>) query.list();
	}
}
