package com.example.entity;

import javax.persistence.Id;
import java.sql.Date;

public class Orders {

    @Id
    int OrderId;
    int UserId;
    int AddressId;
    float Price;
    int CreditCardId;
    Date DateOrdered;
    Date DateShipped;
    Date DateDelivered;
    String OrderStatus;

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getCreditCardId() {
        return CreditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        CreditCardId = creditCardId;
    }

    public Date getDateOrdered() {
        return DateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        DateOrdered = dateOrdered;
    }

    public Date getDateShipped() {
        return DateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        DateShipped = dateShipped;
    }

    public Date getDateDelivered() {
        return DateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        DateDelivered = dateDelivered;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderId=" + OrderId +
                ", UserId=" + UserId +
                ", AddressId=" + AddressId +
                ", Price=" + Price +
                ", CreditCardId=" + CreditCardId +
                ", DateOrdered=" + DateOrdered +
                ", DateShipped=" + DateShipped +
                ", DateDelivered=" + DateDelivered +
                ", OrderStatus='" + OrderStatus + '\'' +
                '}';
    }
}
