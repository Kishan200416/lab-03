package com.example.listycitylab3;

import java.io.Serializable;

public class City implements Serializable {
    private String city;
    private String province;

    // Create a new City object
    public City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    // Getter for the city name
    public String getCityName() {
        return this.city;
    }

    // Getter for the province name
    public String getProvinceName() {
        return this.province;
    }

    // Setter for the city name
    public void setCityName(String city) {
        this.city = city;
    }

    // Setter for the province name
    public void setProvinceName(String province) {
        this.province = province;
    }
}