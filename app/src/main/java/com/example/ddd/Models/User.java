package com.example.ddd.Models;

public class User {
    String Name ;
    String Phone ;
    String Email ;
    String AuthKey;
    String Image ;

    public User(String name, String phone, String email, String authKey, String image) {
        Name = name;
        Phone = phone;
        Email = email;
        AuthKey = authKey;
        Image = image;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAuthKey() {
        return AuthKey;
    }

    public void setAuthKey(String authKey) {
        AuthKey = authKey;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
