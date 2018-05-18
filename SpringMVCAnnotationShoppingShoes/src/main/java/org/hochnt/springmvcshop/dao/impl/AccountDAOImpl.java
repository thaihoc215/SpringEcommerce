package org.hochnt.springmvcshop.dao.impl;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hochnt.springmvcshop.dao.AccountDAO;
import org.hochnt.springmvcshop.entity.Account;
import org.hochnt.springmvcshop.model.AccountInfo;
import org.hochnt.springmvcshop.model.PaginationResult;
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

	@Override
	public AccountInfo findAccountInfo(String userName) {
		Account account = this.findAccount(userName);
		if(account ==null)
			return null;
		return new AccountInfo(account);
	}

	@Override
	public Account registerNewUserAccount(AccountInfo accountForm) {
		Account account = new Account();
		account.setUserName(accountForm.getUserName());
		account.setActive(true);
		account.setAddress(accountForm.getAddress());
		account.setEmail(accountForm.getEmail());
		account.setName(accountForm.getName());
		account.setPassword(accountForm.getPassword());
		account.setPhoneNumber(accountForm.getPhoneNumber());
		account.setUserRole(accountForm.getUserRole());
		account.setDateCreate(new Date());
		account.setDateUpdated(new Date());
		this.sessionFactory.getCurrentSession().persist(account);
		// Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
		this.sessionFactory.getCurrentSession().flush();

		return findAccount(account.getUserName());
	}

	@Override
	/**
	 * Lay danh sach tai khoan
	 * 
	 * @param page
	 * @param mAX_RESULT
	 * @param mAX_NAVIGATION_PAGE
	 * @return
	 */
	public PaginationResult<AccountInfo> listAccountInfo(int page, int maxResult, int maxNavigationPage) {
		// map voi Account info constructor
		String sql = "Select new " + AccountInfo.class.getName()
				+ "(acc.userName, acc.active, acc.password, acc.userRole, "
				+ " acc.name, acc.email, acc.phoneNumber, acc.address,acc.dateCreate,acc.dateUpdated) " + " from "
				+ Account.class.getName() + " acc " + " order by acc.dateCreate desc, acc.userName asc";
		Session session = this.sessionFactory.getCurrentSession();

		Query<?> query = session.createQuery(sql);

		return new PaginationResult<AccountInfo>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public void updateAccountStatus(Account account) {
		if (account.isActive())
			account.setActive(false);
		else
			account.setActive(true);
		account.setDateUpdated(new Date());
		this.sessionFactory.getCurrentSession().saveOrUpdate(account);

	}

	
	@Override
	/**
	 * Thay doi thong tin nguoi dung
	 */
	public void saveAccountInfo(AccountInfo accountInfo) {
		String userName = accountInfo.getUserName();
		
		Account account = null;
		
		if(userName!=null && userName.length() > 0)
			account = this.findAccount(userName);
		
		account.setDateUpdated(new Date());
		account.setEmail(accountInfo.getEmail());
		account.setAddress(accountInfo.getAddress());
		account.setName(accountInfo.getName());
		account.setPassword(accountInfo.getPassword());
		account.setUserRole(accountInfo.getUserRole());
		account.setPhoneNumber(accountInfo.getPhoneNumber());
		
		this.sessionFactory.getCurrentSession().flush();
	}

}
