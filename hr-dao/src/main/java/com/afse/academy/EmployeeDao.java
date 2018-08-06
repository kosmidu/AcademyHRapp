package com.afse.academy;

import java.util.List;

public interface EmployeeDao {
    Employee create(Employee e);
    Employee find(Long id);
    Employee update(Employee e);
    String delete(Long id);
    List<Employee> getAll();
    List<Employee> getAllByDepId(Long id);
}
