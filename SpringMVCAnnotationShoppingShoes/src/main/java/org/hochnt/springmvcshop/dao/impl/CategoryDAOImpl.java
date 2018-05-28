package org.hochnt.springmvcshop.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hochnt.springmvcshop.dao.CategoryDAO;
import org.hochnt.springmvcshop.entity.Category;
import org.hochnt.springmvcshop.entity.Product;
import org.hochnt.springmvcshop.model.PaginationResult;
import org.hochnt.springmvcshop.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//Transactional for Hibernate
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Category> listCategory() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Category> query = builder.createQuery(Category.class);
		Root<Category> root = query.from(Category.class);
		query.select(root);
		return (List<Category>) session.createQuery(query).getResultList();
	}

	@Override
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String cat) {
		String sql = "Select new " + ProductInfo.class.getName() + "(p.code,p.name,p.price,p.category)" + " from "
				+ Product.class.getName() + " p";
		sql += " Where p.category.name != :cat ";
		sql += " order by p.code asc, p.createDate desc ";
		System.out.println(sql);
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery(sql);
		if (cat != null && cat.length() > 0) {
			query.setParameter("cat", cat);
		}
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
	}

}
