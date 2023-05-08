package jpa.daoimpl;

import dto.Employee;
import jpa.dao.EmployeeDao;
import jpa.entity.EmployeeEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl extends EntityManager implements EmployeeDao<Employee> {

    @Override
    public void saveOrUpdate(Employee employee) {

        if (employee != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(employee));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Employee getEmployee(int id) {
        getSession().beginTransaction();
        EmployeeEntity entity = getSession().get(EmployeeEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Employee> getAllEmployee() {
        getSession().beginTransaction();
        List<EmployeeEntity> entities = getSession().createQuery("FROM EmployeeEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Employee> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteEmployee(Employee employee) {

        if (employee != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(employee));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM EmployeeEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Employee mapEntityToDto(EmployeeEntity entity) {
        Employee dto = new Employee();
        dto.setId(entity.getIdEntity());
        dto.setFirstName(entity.getFirstNameEntity());
        dto.setLastName(entity.getLastNameEntity());
        dto.setCompany(entity.getCompanyEntity());
        return dto;
    }

    private EmployeeEntity mapDtoToEntity(Employee dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setIdEntity(dto.getId());
        entity.setFirstNameEntity(dto.getFirstName());
        entity.setLastNameEntity(dto.getLastName());
        entity.setCompanyEntity(dto.getCompany());
        return entity;
    }

}