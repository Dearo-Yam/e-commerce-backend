package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
@Entity
@Table
public class Orders {

    @Id
    int orderId;
    int userId;
    int addressId;
    float price;
    int creditCardId;
    Date dateOrdered;
    Date dateShipped;
    Date dateDelivered;
    String orderStatus;

    public Orders(){}
    public Orders(int orderId, int userId, int addressId, float price, int creditCardId, Date dateOrdered, Date dateShipped, Date dateDelivered, String orderStatus) {
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
        orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        addressId = addressId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        price = price;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        creditCardId = creditCardId;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        dateOrdered = dateOrdered;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        dateShipped = dateShipped;
    }

    public Date getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        dateDelivered = dateDelivered;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderId=" + orderId +
                ", UserId=" + userId +
                ", AddressId=" + addressId +
                ", Price=" + price +
                ", CreditCardId=" + creditCardId +
                ", DateOrdered=" + dateOrdered +
                ", DateShipped=" + dateShipped +
                ", DateDelivered=" + dateDelivered +
                ", OrderStatus='" + orderStatus + '\'' +
                '}';
    }
}
