package com.afse.academy;

import java.util.List;
import java.util.Map;

public interface DepartmentDao {
    Department create(Department d);
    Department find(Long id);
    Department update(Department d);
    String delete(Long id);
    List<Department> getAll();
}
