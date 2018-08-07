package com.afse.academy;

import com.afse.academy.entities.Department;

import java.util.List;

public interface DepartmentDao {
    Department create(Department d);
    Department find(Long id);
    Department update(Department d);
    String delete(Long id);
    List<Department> getAll();
}
