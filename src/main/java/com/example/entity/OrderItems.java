/*package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderitems")
public class OrderItems {
    @Id
    int orderitemid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderid", referencedColumnName = "orderid")
    private Order orderid;
    int quantity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "upc", referencedColumnName = "upc")
    private Product upc;

    public OrderItems(){};
    public OrderItems(int orderitemid, Order orderid, int quantity, Product upc) {
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

    public Order getOrderid() {
        return orderid;
    }

    public void setOrderid(Order orderid) {
        this.orderid = orderid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getUpc() {
        return upc;
    }

    public void setUpc(Product upc) {
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
*/