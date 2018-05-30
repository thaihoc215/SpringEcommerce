package org.hochnt.springmvcshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Shoessize")
public class ShoesSize implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2948640166898882129L;
	private String shoesCode;
	private double shoesSize;
	private int numberLeft;
	
	public ShoesSize() {
	}
	
	@Id
	@Column(name = "Shoes_Code", nullable = false)
	public String getShoesCode() {
		return shoesCode;
	}
	public void setShoesCode(String shoesCode) {
		this.shoesCode = shoesCode;
	}
	
	@Column(name = "Shoe_Size", nullable = false)
	public double getShoesSize() {
		return shoesSize;
	}
	public void setShoesSize(double shoesSize) {
		this.shoesSize = shoesSize;
	}
	
	@Column(name = "Number_Left", nullable = false)
	public int getNumberLeft() {
		return numberLeft;
	}
	public void setNumberLeft(int numberLeft) {
		this.numberLeft = numberLeft;
	}
}
