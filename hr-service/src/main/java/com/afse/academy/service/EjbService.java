package com.afse.academy.service;

import com.afse.academy.Dao;
import com.afse.academy.Employee;
import com.afse.academy.MyException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class EjbService {
    @EJB
    private Dao dao;

//    /* Create EntityManager */
//    @PersistenceContext(unitName = "test")
//    private EntityManager em ;

//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public void test() {
//        /* Create EntityManagerFactory */
////        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
//
//        /* Create and populate Entity */
//        com.afse.academy.Employee employee = new com.afse.academy.Employee();
//        employee.setFirstname("maria");
//        employee.setLastname("kosmi");
//        employee.setEmail("someMail@gmail.com");
//        employee.setIdEmployee(1);
//
//        /* Persist entity */
//        em.getTransaction().begin();
//        em.persist(employee);
//        em.getTransaction().commit();
//
//        /* Retrieve entity */
//        employee = em.find(com.afse.academy.Employee.class, 1);
//        System.out.println(employee);
//
//        /* Update entity */
//        em.getTransaction().begin();
//        employee.setFirstname("joe");
//        System.out.println("com.afse.academy.Employee after updation :- " + employee);
//        em.getTransaction().commit();
//
//        /* Remove entity */
//        em.getTransaction().begin();
//        em.remove(employee);
//        em.getTransaction().commit();
//
//        /* Check whether enittiy is removed or not */
//        employee = em.find(com.afse.academy.Employee.class, 1);
//        System.out.println("com.afse.academy.Employee after removal :- " + employee);
//    }

    /*@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String test() {
        com.afse.academy.Employee emp = createEmployee(1, "Ravi", "Raj", "Textile");
        //com.afse.academy.Employee emp =createEmployee(2, "Amit", "Raj", "IT");
        //createEmployee(3, "Nitish", "Kumar", "Marketing");
        return emp.toString();
    }

    private  com.afse.academy.Employee createEmployee(int id, String firstName, String lastName, String dept) {
        com.afse.academy.Employee emp = new com.afse.academy.Employee(id, firstName, lastName, dept);
        em.persist(emp);
        em.flush();
        return emp;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String testFind(Integer id) {
        com.afse.academy.Employee e = em.find(com.afse.academy.Employee.class, id);
        if(e != null)
            return e.toString();
        return "This entity is not found!";
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String testUpdate(Integer id){
        com.afse.academy.Employee e = em.find(com.afse.academy.Employee.class, id);
        if(e != null) {
            e.setFirstName("Mary");
            e.setDept("SwD");
            com.afse.academy.Employee emmp = em.merge(e);
            return emmp.toString();
        }
        return "No success update";
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String testDelete(Integer id){
        com.afse.academy.Employee e = em.find(com.afse.academy.Employee.class, id);
        if(e != null) {
            em.remove(e);
            return "Success delete";
        }
        return "No Success delete";
        *//*if(testCheck(id))
            return "No Success delete";
        return "Success delete";*//*
    }

    //Check whether entity is removed or not
    private boolean testCheck(Integer id) {
        com.afse.academy.Employee employee = em.find(com.afse.academy.Employee.class, id);
        return employee != null;
    }
*/
    @Inject
    private Validator validator;
    public void validateEmpl(Employee emp){
        validator.validate(emp);
    }

    @EJB
    EmailService emailService;
    //Testing if it is called from Jax-rs
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String getEjbService(Integer msg) throws MyException {
        if(msg!=null && getEmpl(msg) != null) {
            Employee emp = new Employee(8,"","Kos","SW", "kosmi@uth.gr");
            validateEmpl(emp);
            emailService.sendRegistrationEmail(getEmpl(msg));
            return dao.testUpdate(msg);
        }
        else
            throw new MyException();
    }

    private String getEmpl(Integer msg) {
        return dao.testFind(msg);
    }
}