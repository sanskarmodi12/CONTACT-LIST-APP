package com.example.contactlistapp;

public class ContactsModel {
    String name;
    String phoneno;

    public String getName() {
        return name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    String email;
    String address;


    public ContactsModel(String name,String phoneno,String email,String address )
    {
        this.name=name;
        this.address=address;
        this.email=email;
        this.phoneno=phoneno;
    }



}
