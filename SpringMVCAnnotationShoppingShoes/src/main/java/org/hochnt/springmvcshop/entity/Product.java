package org.hochnt.springmvcshop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Products")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6879875942076966693L;

	private String code;
	private String name;
	private double price;
	private byte[] image;

	private Category category;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	// For sort.
	private Date createDate;

	private Date dateUpdated;

	@Id
	@Column(name = "Code", nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Price", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "IMAGE", length = 1111111, nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Create_Date", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "Date_Updated", nullable = false)
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY", nullable = false, //
			foreignKey = @ForeignKey(name = "PRODUCT_CAT_FK"))
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
