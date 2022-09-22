package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "orderitems")
public class OrderItems {
    @Id
    int orderitemid;

    int orderid;
    int quantity;
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "upc", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    //private Product upc;
    String upc;

    public OrderItems(){};
    public OrderItems(int orderitemid, int orderid, int quantity, String upc) {
        this.orderitemid = orderitemid;
        this.orderid = orderid;
        this.quantity = quantity;
        this.upc = upc;
    }

    public int getOrderitemid() {
        return orderitemid;
    }

    public void setOrderitemid(int orderitemid) {
        this.orderitemid = orderitemid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    @Override
    public String toString() {
        return "OrerItems{" +
                "orderitemid=" + orderitemid +
                ", orderid=" + orderid +
                ", quantity=" + quantity +
                ", upc='" + upc + '\'' +
                '}';
    }
}