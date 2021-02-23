package com.example.contact_simranpreet_c0794501_android.Model;

import android.inputmethodservice.Keyboard;

public class RowModel
{

    public RowModel(String fName, String lName, String email, String phone, String address, int id) {
        this.fName = fName;
        this.lName = lName;
        Email = email;
        Phone = phone;
        Address = address;
        this.id = id;
    }

    String fName , lName , Email , Phone ,Address ;
    int id;
    public RowModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


}
