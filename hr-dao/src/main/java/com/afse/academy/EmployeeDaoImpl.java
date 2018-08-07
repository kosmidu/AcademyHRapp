package com.afse.academy;

import com.afse.academy.entities.Employee;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext(unitName = "hrApp")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee create(Employee e) {
        em.persist(e);
        em.flush();

        return e;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Employee find(Long id) {
        Employee e = em.find(Employee.class, id);

        return e;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee update(Employee e) {
        em.find(Employee.class, e.getId());
        Employee employee = em.merge(e);

        return employee;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String delete(Long id) {
        Employee e = em.find(Employee.class, id);
        em.remove(e);

        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employee> getAll() {
        Query query = em.createQuery("Select e " + "from Employee e " + "ORDER BY e.lastName ASC");
        List<Employee> list = query.getResultList();

        return list;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employee> getAllByDepId(Long id) {
        Query query = em.createQuery("Select e " + "from Employee e " + "where e.department.id = :id " + "ORDER BY e.lastName ASC");
        query.setParameter("id", id);
        List<Employee> list = query.getResultList();

        return list;
    }
}