package com.dateformatchecker.model;

public class User {
    private String date;
    private String slo;

    public User(String date, String slo) {
        this.date = date;
        this.slo = slo;
    }

    public User() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlo() {
        return slo;
    }

    public void setSlo(String slo) {
        this.slo = slo;
    }
}
