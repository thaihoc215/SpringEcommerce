package org.hochnt.springmvcshop.dao;

import org.hochnt.springmvcshop.entity.Product;
import org.hochnt.springmvcshop.model.PaginationResult;
import org.hochnt.springmvcshop.model.ProductInfo;

public interface ProductDAO {
	/**
	 * Tim kiem mot san pham
	 * @param code ma san pham
	 * @return
	 */
	public Product findProduct(String code);
	
	/**
	 * Tim kiem thong tin mot san pha
	 * @param code ma san pham
	 * @return
	 */
	public ProductInfo findProductInfo(String code);
	
	/**
	 * Luu mot san pham
	 * @param productInfo
	 */
	public void save(ProductInfo productInfo);
	
	//Phân trang
	/**
	 * Phân trang trên toàn bộ sản phẩm
	 * @param page
	 * @param maxResult
	 * @param maxNavigationPage
	 * @return
	 */
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage);

	/**
	 * Phân trang trên danh sách sản phẩm theo likeName
	 * @param page
	 * @param maxResult
	 * @param maxNavigationPage
	 * @param likeName
	 * @return
	 */
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);
}
