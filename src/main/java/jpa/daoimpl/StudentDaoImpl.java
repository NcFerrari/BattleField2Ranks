package jpa.daoimpl;

import dto.Student;
import jpa.dao.StudentDao;
import jpa.entity.StudentEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends EntityManager implements StudentDao<Student> {

    @Override
    public void saveOrUpdate(Student student) {

        if (student != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(student));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Student getStudent(int id) {
        getSession().beginTransaction();
        StudentEntity entity = getSession().get(StudentEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Student> getAllStudent() {
        getSession().beginTransaction();
        List<StudentEntity> entities = getSession().createQuery("FROM StudentEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Student> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteStudent(Student student) {

        if (student != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(student));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteStudent(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM StudentEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

            private Student mapEntityToDto(StudentEntity entity) {
                Student dto = new Student();
                dto.setId(entity.getIdEntity());
                dto.setFirstName(entity.getFirstNameEntity());
                dto.setLastName(entity.getLastNameEntity());
                dto.setEmail(entity.getEmailEntity());
                return dto;
            }

            private StudentEntity mapDtoToEntity(Student dto) {
                StudentEntity entity = new StudentEntity();
                entity.setIdEntity(dto.getId());
                entity.setFirstNameEntity(dto.getFirstName());
                entity.setLastNameEntity(dto.getLastName());
                entity.setEmailEntity(dto.getEmail());
                return entity;
            }

}