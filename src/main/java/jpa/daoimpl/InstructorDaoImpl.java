package jpa.daoimpl;

import dto.Instructor;
import jpa.dao.InstructorDao;
import jpa.entity.InstructorEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class InstructorDaoImpl extends EntityManager implements InstructorDao<Instructor> {

    @Override
    public void saveOrUpdate(Instructor instructor) {

        if (instructor != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(instructor));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Instructor getInstructor(int id) {
        getSession().beginTransaction();
        InstructorEntity entity = getSession().get(InstructorEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Instructor> getAllInstructor() {
        getSession().beginTransaction();
        List<InstructorEntity> entities = getSession().createQuery("FROM InstructorEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Instructor> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteInstructor(Instructor instructor) {

        if (instructor != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(instructor));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteInstructor(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM InstructorEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

            private Instructor mapEntityToDto(InstructorEntity entity) {
                Instructor dto = new Instructor();
                dto.setId(entity.getIdEntity());
                dto.setFirstName(entity.getFirstNameEntity());
                dto.setLastName(entity.getLastNameEntity());
                dto.setEmail(entity.getEmailEntity());
                dto.setInstructorDetailId(entity.getInstructorDetailIdEntity());
                return dto;
            }

            private InstructorEntity mapDtoToEntity(Instructor dto) {
                InstructorEntity entity = new InstructorEntity();
                entity.setIdEntity(dto.getId());
                entity.setFirstNameEntity(dto.getFirstName());
                entity.setLastNameEntity(dto.getLastName());
                entity.setEmailEntity(dto.getEmail());
                entity.setInstructorDetailIdEntity(dto.getInstructorDetailId());
                return entity;
            }

}