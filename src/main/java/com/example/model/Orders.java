package com.example.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Table
@Entity
public class Orders {
    @Id
    @Column(name = "OrderID")
    int orderId;

    @Column(name = "UserID")
    int userId;

    @Column(name = "AddressID")
    int addressId;

    @Column(name = "Price")
    double price;

    @Column(name = "CreditCardID")
    int creditCardId;

    // To change to different data type if it doesn't support the Time part?
    @Column(name = "DateOrdered")
    Date dateOrdered;

    @Column(name = "DateShipped")
    Date dateShipped;

    @Column(name = "DateDelivered")
    Date dateDelivered;

    @Column(name = "OrderStatus")
    String orderStatus;

    public Orders() {
        super();
    }

    public Orders(int orderId, int userId, int addressId, double price, int creditCardId,
                  Date dateOrdered, Date dateShipped, Date dateDelivered, String orderStatus) {
        super();
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.price = price;
        this.creditCardId = creditCardId;
        this.dateOrdered = dateOrdered;
        this.dateShipped = dateShipped;
        this.dateDelivered = dateDelivered;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return dateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", addressId=" + addressId +
                ", price=" + price +
                ", creditCardId=" + creditCardId +
                ", dateOrdered=" + dateOrdered +
                ", dateShipped=" + dateShipped +
                ", dateDelivered=" + dateDelivered +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
