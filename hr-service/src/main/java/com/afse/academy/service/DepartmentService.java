package com.afse.academy.service;

import com.afse.academy.entities.Department;
import com.afse.academy.DepartmentDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DepartmentService {

    @EJB
    private DepartmentDao departmentDao;

    //@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Department updateDepartment(Department d) { return departmentDao.update(d); }

    public Department findDepartment(Long id) { return departmentDao.find(id); }

    public String deleteDepartment(Long id) { return departmentDao.delete(id); }

    public Department createDepartment(Department d) { return departmentDao.create(d); }

    public List<Department> getAllDepartments() { return departmentDao.getAll(); }
}