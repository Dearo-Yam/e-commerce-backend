package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    String upc;
    String prodname;
    String brand;
    String proddescription;
    String category;
    float priceperunit;
    String imageurl;
    int availablestock;
    int reservedstock;
    int shippedstock;

    public Product(){};
    public Product(String upc, String prodname, String brand, String proddescription, String category, float priceperunit, String imageurl, int availabelstock, int reservedstock, int shippedstock) {
        this.upc = upc;
        this.prodname = prodname;
        this.brand = brand;
        this.proddescription = proddescription;
        this.category = category;
        this.priceperunit = priceperunit;
        this.imageurl = imageurl;
        this.availablestock = availabelstock;
        this.reservedstock = reservedstock;
        this.shippedstock = shippedstock;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProddescription() {
        return proddescription;
    }

    public void setProddescription(String proddescription) {
        this.proddescription = proddescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(float priceperunit) {
        this.priceperunit = priceperunit;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getAvailablestock() {
        return availablestock;
    }

    public void setAvailablestock(int availablestock) {
        this.availablestock = availablestock;
    }

    public int getReservedstock() {
        return reservedstock;
    }

    public void setReservedstock(int reservedstock) {
        this.reservedstock = reservedstock;
    }

    public int getShippedstock() {
        return shippedstock;
    }

    public void setShippedstock(int shippedstock) {
        this.shippedstock = shippedstock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "upc='" + upc + '\'' +
                ", prodname='" + prodname + '\'' +
                ", brand='" + brand + '\'' +
                ", proddescription='" + proddescription + '\'' +
                ", category='" + category + '\'' +
                ", priceperunit=" + priceperunit +
                ", imageurl='" + imageurl + '\'' +
                ", availabelstock=" + availablestock +
                ", reservedstock=" + reservedstock +
                ", shippedstock=" + shippedstock +
                '}';
    }
}
