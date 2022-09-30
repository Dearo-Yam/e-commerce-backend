package com.example.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @Column(name = "order_item_id")
    int order_item_id;


    @Column(name = "quantity")
    int quantity;

    @Column(name = "upc")
    String upc;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="order_id")
    Orders order;
    
    public Orders getOrder() {
        return order;
    }
    public void setOrder(Orders order) {
        this.order = order;
    }
    public OrderItems(){
        super();
    };
    public OrderItems(int order_item_id, int quantity, String upc) {
        this.order_item_id = order_item_id;
        this.quantity = quantity;
        this.upc = upc;
    }
    

    public int getOrderitemid() {
        return order_item_id;
    }

    public void setOrderitemid(int order_item_id) {
        this.order_item_id = order_item_id;
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
        return "OrderItems{" +
                "order_item_id=" + order_item_id +
                ", order_id=" + this.order.order_id +
                ", quantity=" + quantity +
                ", upc='" + upc + '\'' +
                '}';
    }
}