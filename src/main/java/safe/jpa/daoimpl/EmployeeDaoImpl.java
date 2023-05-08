package safe.jpa.daoimpl;

import safe.dto.Employee;
import safe.jpa.dao.EmployeeDao;

import java.util.List;

public class EmployeeDaoImpl extends EntityManager implements EmployeeDao<Employee> {

    @Override
    public void saveOrUpdate(Employee employee) {

    }

    @Override
    public Employee getEmployee(int id) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public void deleteEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(int id) {
    }

}