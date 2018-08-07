package com.afse.academy;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LocationDaoImpl implements LocationDao {

    @PersistenceContext(unitName = "hrApp")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> getCountries(){
        Query query = em.createQuery( "Select DISTINCT(l.countryName) " + "from Location l "+ "ORDER BY l.countryName ASC");
        List list = query.getResultList();

        return list;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> getCities(String country) {
        Query query = em.createQuery( "Select l.cityName " + "from Location l " + "where l.countryName = :country " + "ORDER BY l.cityName ASC");
        query.setParameter("country", country);
        List list = query.getResultList();

        return list;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean validateCountryCity(String country, String city) {
        Query query = em.createQuery( "Select 1 " + "from Location l " + "where l.countryName = :country and l.cityName = :city" );
        query.setParameter("country", country);
        query.setParameter("city", city);

        return !query.getResultList().isEmpty(); // if the resultList is empty returns false, else true
    }
}