package com.example.ddd.Models;

public class Medicine {
    int Id ;
    String Name ;
    int Quantity;
    double Price ;

    public Medicine() {
    }

    public Medicine(int id, String name, int quantity, double price) {
        Id = id;
        Name = name;
        Quantity = quantity;
        Price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
