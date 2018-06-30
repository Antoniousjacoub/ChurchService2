package com.example.antonio.marinaApp.models;

/**
 * Created by antonio on 6/30/18.
 */

public class User {
    private String first_name;
    private String midle_name;
    private String last_name;
    private String gender;
    private String bithdate;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMidle_name() {
        return midle_name;
    }

    public void setMidle_name(String midle_name) {
        this.midle_name = midle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBithdate() {
        return bithdate;
    }

    public void setBithdate(String bithdate) {
        this.bithdate = bithdate;
    }

}
