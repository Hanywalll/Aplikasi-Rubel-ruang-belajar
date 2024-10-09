package com.example.rubel.model;

public class User {
    private String email,kritik,id;

    public User(){

    }
    public User(String kritik, String email){
        this.kritik = kritik;
        this.email = email;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKritik() {
        return kritik;
    }

    public void setKritik(String kritik) {
        this.kritik = kritik;
    }
}
