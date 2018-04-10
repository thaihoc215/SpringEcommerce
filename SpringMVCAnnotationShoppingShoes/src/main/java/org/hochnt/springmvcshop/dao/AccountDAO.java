package org.hochnt.springmvcshop.dao;

import org.hochnt.springmvcshop.entity.Account;

public interface AccountDAO {
	public Account findAccount(String userName);
}
