package com.afse.academy;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmailDaoImpl implements EmailDao {

    /* Create EntityManager */
    @PersistenceContext(unitName = "test")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void createEmail(Email email) {
        em.persist(email);
        em.flush();
    }
}
