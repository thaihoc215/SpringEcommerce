package org.hochnt.springmvcshop.dao;

import java.util.List;

import org.hochnt.springmvcshop.entity.Category;
import org.hochnt.springmvcshop.model.PaginationResult;
import org.hochnt.springmvcshop.model.ProductInfo;

public interface CategoryDAO {
	/**
	 * Lay danh sach loai giay
	 * @return
	 */
	public List<Category> listCategory();
	
	/**
	 * Lay danh sach giay phan trang theo loai giay
	 * @param page
	 * @param maxResult
	 * @param maxNavigationPage
	 * @param likeName
	 * @return
	 */
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
			String cat);
}
