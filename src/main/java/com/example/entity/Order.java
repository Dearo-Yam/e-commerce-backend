package com.example.entity;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "orders")
public class Order {

    @Id
    int orderid;
    int userid;
    int addressid;
    float price;
    int creditcardid;
    Date dateordered;
    Date dateshipped;
    Date datedelivered;
    String orderstatus;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "orderitmes",
    joinColumns =
            {@JoinColumn(name = "orderid", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "orderid", referencedColumnName = "id")})
    private OrderItems os;*/


    public Order(){}
    public Order(int orderId, int userId, int addressId, float price, int creditCardId, Date dateOrdered, Date dateShipped, Date dateDelivered, String orderStatus) {
        this.orderid = orderId;
        this.userid = userId;
        this.addressid = addressId;
        this.price = price;
        this.creditcardid = creditCardId;
        this.dateordered = dateOrdered;
        this.dateshipped = dateShipped;
        this.datedelivered = dateDelivered;
        this.orderstatus = orderStatus;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        orderid = orderid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        userid = userid;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        addressid = addressid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        price = price;
    }

    public int getCreditcardid() {
        return creditcardid;
    }

    public void setCreditcardid(int creditcardid) {
        creditcardid = creditcardid;
    }

    public Date getDateordered() {
        return dateordered;
    }

    public void setDateordered(Date dateordered) {
        dateordered = dateordered;
    }

    public Date getDateshipped() {
        return dateshipped;
    }

    public void setDateshipped(Date dateshipped) {
        dateshipped = dateshipped;
    }

    public Date getDatedelivered() {
        return datedelivered;
    }

    public void setDatedelivered(Date datedelivered) {
        datedelivered = datedelivered;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        orderstatus = orderstatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderId=" + orderid +
                ", UserId=" + userid +
                ", AddressId=" + addressid +
                ", Price=" + price +
                ", CreditCardId=" + creditcardid +
                ", DateOrdered=" + dateordered +
                ", DateShipped=" + dateshipped +
                ", DateDelivered=" + datedelivered +
                ", OrderStatus='" + orderstatus + '\'' +
                '}';
    }
}
