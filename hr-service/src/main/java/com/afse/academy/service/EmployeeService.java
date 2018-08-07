package com.afse.academy.service;

import com.afse.academy.EmployeeDao;
import com.afse.academy.entities.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmployeeService {

    @EJB
    private EmployeeDao employeeDao;

    @EJB
    EmailService emailService;

    public Employee updateEmployee(Employee e) { return employeeDao.update(e); }

    public Employee findEmployee(Long id) { return employeeDao.find(id); }

    public String deleteEmployee(Long id) { return employeeDao.delete(id); }

    public Employee createEmployee(Employee e) {
        Employee emp = employeeDao.create(e);
        emailService.sendRegistrationEmail(emp);
        return emp;
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    public List<Employee> getAllEmployeesByDepId(Long id) {
        return employeeDao.getAllByDepId(id);
    }
}