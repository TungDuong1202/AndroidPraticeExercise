package com.example.contactapplication.model;

public class Staff {
    private String sId;
    private String sName;
    private String sPhoneNumber;
    private String sAddress;
    private String position;
    private String email;
    private String role = "staff";


    public Staff( String sName, String sPhoneNumber, String sAddress, String position, String email) {
        this.sName = sName;
        this.sPhoneNumber = sPhoneNumber;
        this.sAddress = sAddress;
        this.position = position;
        this.email = email;
    }

    public Staff(String sId, String sName, String sPhoneNumber, String sAddress, String position, String email) {
        this.sId = sId;
        this.sName = sName;
        this.sPhoneNumber = sPhoneNumber;
        this.sAddress = sAddress;
        this.position = position;
        this.email = email;
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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPhoneNumber() {
        return sPhoneNumber;
    }

    public void setsPhoneNumber(String sPhoneNumber) {
        this.sPhoneNumber = sPhoneNumber;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getRole() {
        return role;
    }
}
