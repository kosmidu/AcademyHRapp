package com.afse.academy;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class DepartmentDaoImpl implements DepartmentDao {
    /* Create EntityManager */
    @PersistenceContext(unitName = "test")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department create(Department d) {
        if (d != null) {
            em.persist(d);
            em.flush();
            return d;
        }
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department find(Long id) {
        Department d = em.find(Department.class, id);
        if (d != null) {
            return d;
        }
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department update(Department d) {
        Department dep = em.find(Department.class, d.getId());
        if (dep != null) {
            Department department = em.merge(d);
            return department;
        }
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String delete(Long id) {
        Department d = em.find(Department.class, id);
        if (d != null) {
            em.remove(d);
            return "Success delete";
        }
        return "No Success delete";
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Department> getAll() {
        Query query = em.createQuery("Select d " + "from Department d " + "ORDER BY d.name ASC");

        List<Department> list = (List<Department>) query.getResultList();

        return list;
    }
}
