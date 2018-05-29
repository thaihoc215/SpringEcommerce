package org.hochnt.springmvcshop.model;

import org.hochnt.springmvcshop.entity.Product;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProductInfo {

	private String code;
	private String name;
	private double price;

	private CategoryInfo category;

	private boolean newProduct = false;
	// Upload image file.
	private CommonsMultipartFile[] fileDatas;

	public ProductInfo() {
	}

	public ProductInfo(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();
	}

	// Không thay đổi Constructor này,
	// nó được sử dụng trong Hibernate query.
	public ProductInfo(String code, String name, double price, String catId, String catName ) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = new CategoryInfo(catId, catName);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public CategoryInfo getCategory() {
		return category;
	}

	public void setCategory(CategoryInfo category) {
		this.category = category;
	}

	public CommonsMultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(CommonsMultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}
}
