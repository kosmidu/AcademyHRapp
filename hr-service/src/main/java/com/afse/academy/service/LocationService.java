package com.afse.academy.service;

import com.afse.academy.LocationDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class LocationService {
    @EJB
    private LocationDao locationDao;

    public List<String> getLocation() {
        return locationDao.getCountries();
    }

    public List<String> getCities(String country){
        return locationDao.getCities(country);
    }
}