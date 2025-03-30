package com.example.contactapplication.model;

public class Unit {
    private String uId;
    private String uName;
    private String uPhoneNumber;
    private String uAddress;
    private String position;
    private String email;

    public Unit(String uId, String uName, String uPhoneNumber, String uAddress, String position, String email) {
        this.uId = uId;
        this.uName = uName;
        this.uPhoneNumber = uPhoneNumber;
        this.uAddress = uAddress;
        this.position = position;
        this.email = email;
    }
    public Unit( String uName, String uPhoneNumber, String uAddress, String position, String email) {
        this.uName = uName;
        this.uPhoneNumber = uPhoneNumber;
        this.uAddress = uAddress;
        this.position = position;
        this.email = email;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPhoneNumber() {
        return uPhoneNumber;
    }

    public void setuPhoneNumber(String uPhoneNumber) {
        this.uPhoneNumber = uPhoneNumber;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
