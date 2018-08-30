package com.fixstore.firebaseexample;

public class User {

   private String Name;
   private String Address;
   private Phone phone;

    public User(String name, String address) {
        Name = name;
        Address = address;
    }

    public User(String name, String address, Phone phone) {
        Name = name;
        Address = address;
        this.phone=phone;
    }

    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
