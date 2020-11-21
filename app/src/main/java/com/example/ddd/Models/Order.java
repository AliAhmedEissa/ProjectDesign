package com.example.ddd.Models;

import java.util.List;

public class Order {
    int Id ;
    double latitude ;
    double longitude ;
    String Address ;
    User user ;
    List<Medicine> medicineList ;
    int status ;
    double Total ;
    int Quantity ;

    public Order() {
    }

    public Order(int id, double latitude, double longitude, String address, User user, List<Medicine> medicineList, int status, double total, int quantity) {

        Id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        Address = address;
        this.user = user;
        this.medicineList = medicineList;
        this.status = status;
        Total = total;
        Quantity = quantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
