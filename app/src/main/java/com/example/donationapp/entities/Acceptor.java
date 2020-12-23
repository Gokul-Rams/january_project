package com.example.donationapp.entities;

import android.content.Intent;

public class Acceptor {
    String description,type;
    Integer minqty,maxqty;
    String companyname;
    Boolean isindividual;

    public Acceptor() {
    }


    public Acceptor(String description, String type, Integer minqty, Integer maxqty, String companyname, Boolean isindividual) {
        this.description = description;
        this.type = type;
        this.minqty = minqty;
        this.maxqty = maxqty;
        this.companyname = companyname;
        this.isindividual = isindividual;
    }

    public Boolean getIsindividual() {
        return isindividual;
    }

    public void setIsindividual(Boolean isindividual) {
        this.isindividual = isindividual;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMinqty() {
        return minqty;
    }

    public void setMinqty(Integer minqty) {
        this.minqty = minqty;
    }

    public Integer getMaxqty() {
        return maxqty;
    }

    public void setMaxqty(Integer maxqty) {
        this.maxqty = maxqty;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
