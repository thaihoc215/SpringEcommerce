package org.hochnt.springmvcshop.dao.impl;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.hochnt.springmvcshop.entity.Product;
import org.hochnt.springmvcshop.model.PaginationResult;
import org.hochnt.springmvcshop.model.ProductInfo;
import org.hochnt.springmvcshop.util.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//Transactional for Hibernate
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Product findProduct(String code) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root).where(builder.equal(root.get("code"), code));
		return (Product) session.createQuery(query).uniqueResult();
	}

	@Override
	public ProductInfo findProductInfo(String code) {
		Product product = this.findProduct(code);
		return product == null ? null : new ProductInfo(product);
	}

	@Override
	/**
	 * Luu hoac them moi mot san pham
	 * @param productInfo
	 */
	public void save(HttpServletRequest request, ProductInfo productInfo) {
		String code = productInfo.getCode();

		Product product = null;

		boolean isNew = false;
		if (code != null) {
			product = this.findProduct(code);
		}
		//neu khong tim thay san pham => them moi
		if (product == null) {
			isNew = true;
			product = new Product();
			product.setCreateDate(new Date());
		}

		// set thuoc tinh cho product
		product.setCode(code);
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());

		// upload anh cho product
		if (productInfo.getFileDatas()[0] != null) {
			byte[] image = productInfo.getFileDatas()[0].getBytes();
			if (image != null && image.length > 0) {
				product.setImage(image);
			}
		}
		Commons.doUploadImage(request, productInfo);

		if (isNew) {
			this.sessionFactory.getCurrentSession().persist(product);
		}
		// Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
		this.sessionFactory.getCurrentSession().flush();
	}

	// phân trang
	@Override
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
		return queryProducts(page, maxResult, maxNavigationPage, null);
	}

	@Override
	//dung cho viec tim kiem san pham voi like name
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		String sql = "Select new " + ProductInfo.class.getName() + "(p.code,p.name,p.price)" + "from "
				+ Product.class.getName() + " p";
		if (likeName != null && likeName.length() > 0) {
			sql += " Where lower(p.name) like :likeName ";
		}
		sql += " order by p.code asc, p.createDate desc ";
		System.out.println(sql);
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery(sql);
		if (likeName != null && likeName.length() > 0) {
			query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
		}
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
	}
}
