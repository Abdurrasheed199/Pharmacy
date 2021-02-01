package com.shifa.pharmacy.model;

import javax.persistence.*;

@Entity
public class OrderDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int quantity;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Drug drug;
    private double price;

    public OrderDrug(){

    }

    public OrderDrug(int quantity, Order order, Drug drug, double price) {
        this.quantity = quantity;
        this.order = order;
        this.drug = drug;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
