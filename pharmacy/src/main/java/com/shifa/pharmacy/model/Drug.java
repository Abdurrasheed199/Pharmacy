package com.shifa.pharmacy.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @ManyToOne
    private DrugManufacturer drugManufacturer;
    private String name;
    private double price;
    private String url;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "drug_category",
            joinColumns = @JoinColumn(name = "drug_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories;

    private String description;


    public Drug(){

    }

    public Drug(DrugManufacturer drugManufacturer, String name, double price, String url, List<Category> categories, String description) {
        this.drugManufacturer = drugManufacturer;
        this.name = name;
        this.price = price;
        this.url = url;
        this.categories = categories;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DrugManufacturer getDrugManufacturer() {
        return drugManufacturer;
    }

    public void setDrugManufacturer(DrugManufacturer drugManufacturer) {
        this.drugManufacturer = drugManufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
