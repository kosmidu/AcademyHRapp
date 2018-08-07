package com.afse.academy.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="location", uniqueConstraints=
        @UniqueConstraint(columnNames = {"country_name", "city_name"}))
public class Location implements Serializable {

    private static final long serialVersionUID = 7946206087880906290L;

    public Location() {}

    public Location(Long id, String countryName, String cityName) {
        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
    }

    @Id
    @GeneratedValue(generator = "LOCATION_SEQ")
    @SequenceGenerator(name = "LOCATION_SEQ", sequenceName = "LOCATION_SEQ", allocationSize = 1)
    private Long id;

    @Column(name="country_name", insertable = false, nullable = false)
    private String countryName;

    @Column(name="city_name" , insertable = false, nullable = false)
    private String cityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
