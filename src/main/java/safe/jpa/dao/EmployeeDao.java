package safe.jpa.dao;

import java.util.List;

public interface EmployeeDao<Employee> {

    void saveOrUpdate(Employee employee);

    Employee getEmployee(int id);

    List<Employee> getAllEmployee();

    void deleteEmployee(Employee employee);

    void deleteEmployee(int id);

}