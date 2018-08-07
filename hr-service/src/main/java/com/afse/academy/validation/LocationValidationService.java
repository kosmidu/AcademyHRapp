package com.afse.academy.validation;

import com.afse.academy.LocationDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LocationValidationService {
    @EJB
    private LocationDao locationDao;

    /**
     * This is a validation service for the location
     * @param country
     * @param city
     * @return true if the combination country-city is valid, else false
     */
    public boolean validateLocation(String country, String city){
        return locationDao.validateCountryCity(country, city);
    }
}