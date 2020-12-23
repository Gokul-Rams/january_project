package com.example.donationapp.entities;

public class User {
    String uid,name,phno,email;
    Boolean isdonor;

    public User() {
    }

    public User(String uid, String name, String phno, String email, Boolean isdonor) {
        this.uid = uid;
        this.name = name;
        this.phno = phno;
        this.email = email;
        this.isdonor = isdonor;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsdonor() {
        return isdonor;
    }

    public void setIsdonor(Boolean isdonor) {
        this.isdonor = isdonor;
    }
}
