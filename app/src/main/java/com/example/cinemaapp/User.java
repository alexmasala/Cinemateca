package com.example.cinemaapp;

import java.io.Serializable;

public class User implements Serializable {

    private String nume;
    private String prenume;
    private String facultate;
    private String specializare;
    private String email;
    private String password;

    public User(String nume, String prenume, String facultate, String specializare, String email, String password) {
        this.nume = nume;
        this.prenume = prenume;
        this.facultate = facultate;
        this.specializare = specializare;
        this.email = email;
        this.password = password;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", facultate='" + facultate + '\'' +
                ", specializare='" + specializare + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
