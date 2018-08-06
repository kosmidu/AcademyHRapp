package com.afse.academy;

import java.util.List;

public interface LocationDao {
    List<String> getCountries();
    List<String> getCities(String country);
    boolean validateCountryCity(String country, String city);
}