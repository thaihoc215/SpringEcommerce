package org.hochnt.springmvcshop.dao;

import org.hochnt.springmvcshop.entity.Account;
import org.hochnt.springmvcshop.model.AccountInfo;

public interface AccountDAO {
	public Account findAccount(String userName);

	/**
	 * 
	 * @param accountForm
	 * @return
	 */
	public Account registerNewUserAccount(AccountInfo accountForm);
}
