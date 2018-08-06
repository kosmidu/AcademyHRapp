package com.afse.academy;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EmployeeDaoImpl implements EmployeeDao {

//    Create EntityManager
    @PersistenceContext(unitName = "test")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee create(Employee e) {
        if (e != null) {
            em.persist(e);
            em.flush();
            return e;
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee find(Long id) {
        Employee e = em.find(Employee.class, id);
        if (e != null) {
            return e;
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee update(Employee e) {
        Employee emp = em.find(Employee.class, e.getId());
        if (emp != null) {
            Employee employee = em.merge(e);
            return employee;
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String delete(Long id) {
        Employee e = em.find(Employee.class, id);
        if (e != null) {
            em.remove(e);
            return "Success delete";
        }
        return "No Success delete";
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Employee> getAll() {
        Query query = em.createQuery("Select e " + "from Employee e " + "ORDER BY e.lastName ASC");

        List<Employee> list = (List<Employee>) query.getResultList();
       /* Map<Long, Employee> map = new HashMap<>();

        for (Employee e : list) {
            map.put(e.getId(), e);
        }*/
        return list;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Employee> getAllByDepId(Long id) {
        Query query = em.createQuery("Select e " + "from Employee e " + "where e.department.id = :id " + "ORDER BY e.lastName ASC");
        query.setParameter("id", id);
        List<Employee> list = (List<Employee>) query.getResultList();
        /*Map<Long, Employee> map = new HashMap<>();

        for (Employee e : list) {
            map.put(e.getId(), e);
        }*/
        return list;
    }
}