package com.afse.academy;

import com.afse.academy.entities.Email;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmailDaoImpl implements EmailDao {

    @PersistenceContext(unitName = "hrApp")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createEmail(Email email) {
        em.persist(email);
        em.flush();
    }
}
