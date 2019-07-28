package com.example.doctorsapp;

public class Communicate {

    private String address;
    private String message;

    public Communicate() {
    }

    public Communicate(String address, String message) {
        this.address = address;
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

