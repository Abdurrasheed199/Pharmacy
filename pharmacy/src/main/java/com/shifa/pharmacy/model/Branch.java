package com.shifa.pharmacy.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    /*private String registrationNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pharmacy_drug",
            joinColumns = @JoinColumn(name = "pharmacy_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id"))
    List<Drug> drug;
*/
    public Branch(){

    }

    public Branch(String name, String address, String phoneNumber/*, String registrationNumber, List<Drug> drug*/) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        /*this.registrationNumber = registrationNumber;
        this.drug = drug;*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

   /* public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<Drug> getDrug() {
        return drug;
    }

    public void setDrug(List<Drug> drug) {
        this.drug = drug;
    }*/
}
