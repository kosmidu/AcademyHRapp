package com.afse.academy.service;

import com.afse.academy.Employee;
import com.afse.academy.EmployeeDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
public class EmployeeService {
    @EJB
    private EmployeeDao employeeDao;

    @EJB
    EmailService emailService;


    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Employee updateEmployee(Employee e) { return employeeDao.update(e); }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee findEmployee(Long id) {
        return employeeDao.find(id);
    }

    public String deleteEmployee(Long id) {
        return employeeDao.delete(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee createEmployee(Employee e) {
        Employee empl = employeeDao.create(e);
        emailService.sendRegistrationEmail(findEmployee(empl.getId()).toString());
        return empl;
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    public List<Employee> getAllEmployeesByDepId(Long id) {
        return employeeDao.getAllByDepId(id);
    }
}