package org.hochnt.springmvcshop.dao;

import org.hochnt.springmvcshop.entity.Account;
import org.hochnt.springmvcshop.model.AccountInfo;
import org.hochnt.springmvcshop.model.PaginationResult;

public interface AccountDAO {
	public Account findAccount(String userName);

	/**
	 * dang ki tai khoan cho khach hang
	 * @param accountForm
	 * @return
	 */
	public Account registerNewUserAccount(AccountInfo accountForm);
	
	/**
	 * Lay danh sach tai khoan
	 * @param page
	 * @param mAX_RESULT
	 * @param mAX_NAVIGATION_PAGE
	 * @return
	 */
	public PaginationResult<AccountInfo> listAccountInfo(int page, int maxResult, int maxNavigationPage);

	public void updateAccountStatus(Account account);
}
