package com.afse.academy.boundary;

import com.afse.academy.Department;
import com.afse.academy.exception.MyException;
import com.afse.academy.service.DepartmentService;
import com.afse.academy.validation.ValidationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DepartmentBoundary {
    @EJB
    private DepartmentService departmentService;

    @EJB
    private ValidationService validationService;

    public Department create(Department d) throws MyException {
        //if (d.getId() != null && find(d.getId()) == null) { //if department does not exist, create
        if(d != null) {
            validationService.validateDepartment(d);
            return departmentService.createDepartment(d);
        } else {
            throw new MyException();
        }
    }

    public String delete(Long id) throws MyException {
        if(id != null && find(id) != null) { //if department exists, delete
            return departmentService.deleteDepartment(id);
        } else {
            throw new MyException();
        }
    }

    public Department update(Department e) throws MyException {
        if (e.getId() != null && find(e.getId()) != null) { //if department exists, update
            validationService.validateDepartment(e);
            return departmentService.updateDepartment(e);
        } else {
            throw new MyException();
        }
    }

    public Department find (Long id) throws MyException {
        if(id != null) {
            return departmentService.findDepartment(id);
        } else {
            throw new MyException();
        }
    }

    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }
}
