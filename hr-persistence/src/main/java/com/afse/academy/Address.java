package com.afse.academy;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class Address implements Serializable {

    private static final long serialVersionUID = 207001709466610055L;

    public Address() {}

    public Address(String city, String country, String street, String streetNum, String zipCode) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.streetNumber = streetNum;
        this.zipCode = zipCode;
    }

    private String city;
    private String country;
    private String street;

    @Column(name="str_num")
    private String streetNumber;

    @Column(name="zip_code")
    private String zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}