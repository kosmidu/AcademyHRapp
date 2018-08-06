package com.afse.academy.boundary;

import com.afse.academy.service.LocationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class LocationBoundary {
    @EJB
    LocationService locationService;

    public List<String> getCountries() {
        return locationService.getLocation();
    }

    public List<String> getCities(String country) { //TODO validation
        return locationService.getCities(country);
    }
}