package com.nagarro.remotelearning.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    @JsonProperty("city")
    private String city;
    @JsonProperty("zipcode")
    private String zipcode;
    @JsonProperty("addressLine")
    private String addressLine;

    public Address(String city, String zipcode, String addressLine) {
        this.city = city;
        this.zipcode = zipcode;
        this.addressLine = addressLine;
    }

    public Address() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

}
