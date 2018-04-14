package org.hochnt.springmvcshop.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hochnt.springmvcshop.dao.AccountDAO;
import org.hochnt.springmvcshop.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//Transactional for Hibernate
@Transactional
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	// nhan tu applicationConfig
	private SessionFactory sessionFactory;

	@Override
	public Account findAccount(String userName) {
		Session sess = sessionFactory.getCurrentSession();
		// Criteria crit = sess.createCriteria(Account.class);
		// crit.add(Restrictions.eq("userName", userName));
		// return (Account) crit.uniqueResult();
		// Create CriteriaBuilder
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root);
		query.where(builder.equal(root.get("userName"), userName));
		Object tmp = sess.createQuery(query).uniqueResult();
		return (Account) tmp;

	}

}
