package com.revathi.model;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
public class OrderHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private int userId;
	
	private String Date;
	
	@NotNull
	private int count ;
	@NotNull
	private double netAmouunt;
	
	@NotNull
	private int productCnt;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
   	private ArrayList<WishList> cartProduct;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getNetAmouunt() {
		return netAmouunt;
	}

	public void setNetAmouunt(double netAmouunt) {
		this.netAmouunt = netAmouunt;
	}

	public int getProductCnt() {
		return productCnt;
	}

	public void setProductCnt(int productCnt) {
		this.productCnt = productCnt;
	}

	public ArrayList<WishList> getCartProduct() {
		return cartProduct;
	}

	public void setCartProduct(ArrayList<WishList> cartProduct) {
		this.cartProduct = cartProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Date == null) ? 0 : Date.hashCode());
		result = prime * result + ((cartProduct == null) ? 0 : cartProduct.hashCode());
		result = prime * result + count;
		long temp;
		temp = Double.doubleToLongBits(netAmouunt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productCnt;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderHistory other = (OrderHistory) obj;
		if (Date == null) {
			if (other.Date != null)
				return false;
		} else if (!Date.equals(other.Date))
			return false;
		if (cartProduct == null) {
			if (other.cartProduct != null)
				return false;
		} else if (!cartProduct.equals(other.cartProduct))
			return false;
		if (count != other.count)
			return false;
		if (Double.doubleToLongBits(netAmouunt) != Double.doubleToLongBits(other.netAmouunt))
			return false;
		if (productCnt != other.productCnt)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
