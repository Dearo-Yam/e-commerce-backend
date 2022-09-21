package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Orders {
	@Id
	int orderId;
	int addressId;
	double price;
	int creditCardId;
	Date dateOrdered;
	Date dateShipped;
	Date DateDelivered;
	String orderStatus;
	
	public Orders() {
		super();
	}

	public Orders(int orderId, int addressId, double price, int creditCardId, Date dateOrdered, Date dateShipped,
			Date dateDelivered, String orderStatus) {
		super();
		this.orderId = orderId;
		this.addressId = addressId;
		this.price = price;
		this.creditCardId = creditCardId;
		this.dateOrdered = dateOrdered;
		this.dateShipped = dateShipped;
		DateDelivered = dateDelivered;
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(int creditCardId) {
		this.creditCardId = creditCardId;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public Date getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}

	public Date getDateDelivered() {
		return DateDelivered;
	}

	public void setDateDelivered(Date dateDelivered) {
		DateDelivered = dateDelivered;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", addressId=" + addressId + ", price=" + price + ", creditCardId="
				+ creditCardId + ", dateOrdered=" + dateOrdered + ", dateShipped=" + dateShipped + ", DateDelivered="
				+ DateDelivered + ", orderStatus=" + orderStatus + "]";
	}
}
