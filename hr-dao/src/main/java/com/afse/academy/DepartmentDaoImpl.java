package com.afse.academy;

import com.afse.academy.entities.Department;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext(unitName = "hrApp")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department create(Department d) {
        em.persist(d);
        em.flush();

        return d;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Department find(Long id) {
        Department d = em.find(Department.class, id);

        return d;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department update(Department d) {
        em.find(Department.class, d.getId());
        Department department = em.merge(d);

        return department;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String delete(Long id) {
        Department d = em.find(Department.class, id);
        em.remove(d);

       return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Department> getAll() {
        Query query = em.createQuery("Select d " + "from Department d " + "ORDER BY d.name ASC");
        List<Department> list = query.getResultList();

        return list;
    }
}
