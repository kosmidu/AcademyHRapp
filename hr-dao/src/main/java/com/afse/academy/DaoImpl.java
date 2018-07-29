package com.afse.academy;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DaoImpl implements Dao {

    /* Create EntityManager */
    @PersistenceContext(unitName = "test")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String test() {
        Employee emp = createEmployee(1, "Ravi", "Raj", "Textile", "some@ha.com");
        //com.afse.academy.Employee emp =createEmployee(2, "Amit", "Raj", "IT");
        //createEmployee(3, "Nitish", "Kumar", "Marketing");
        return emp.toString();
    }

    private Employee createEmployee(int id, String firstName, String lastName, String dept, String email) {
        Employee emp = new Employee(id, firstName, lastName, dept, email);
        em.persist(emp);
        em.flush();
        return emp;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String testFind(Integer id) {
        Employee e = em.find(Employee.class, id);
        if (e != null)
            return e.toString();
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String testUpdate(Integer id) {
        Employee e = em.find(Employee.class, id);
        if (e != null) {
            e.setFirstName("Mitsaras");
            e.setDept("Driver");
            Employee emmp = em.merge(e);
            return emmp.toString();
        }
        return "No success update";
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String testDelete(Integer id) {
        Employee e = em.find(Employee.class, id);
        if (e != null) {
            em.remove(e);
            return "Success delete";
        }
        return "No Success delete";
        /*if(testCheck(id))
            return "No Success delete";
        return "Success delete";*/
    }

    //Check whether entity is removed or not
    private boolean testCheck(Integer id) {
        Employee employee = em.find(Employee.class, id);
        return employee != null;
    }
}