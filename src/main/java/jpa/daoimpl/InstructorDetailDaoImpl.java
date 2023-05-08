package jpa.daoimpl;

import dto.InstructorDetail;
import jpa.dao.InstructorDetailDao;
import jpa.entity.InstructorDetailEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class InstructorDetailDaoImpl extends EntityManager implements InstructorDetailDao<InstructorDetail> {

    @Override
    public void saveOrUpdate(InstructorDetail instructorDetail) {

        if (instructorDetail != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(instructorDetail));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public InstructorDetail getInstructorDetail(int id) {
        getSession().beginTransaction();
        InstructorDetailEntity entity = getSession().get(InstructorDetailEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<InstructorDetail> getAllInstructorDetail() {
        getSession().beginTransaction();
        List<InstructorDetailEntity> entities = getSession().createQuery("FROM InstructorDetailEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<InstructorDetail> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteInstructorDetail(InstructorDetail instructorDetail) {

        if (instructorDetail != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(instructorDetail));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteInstructorDetail(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM InstructorDetailEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

            private InstructorDetail mapEntityToDto(InstructorDetailEntity entity) {
                InstructorDetail dto = new InstructorDetail();
                dto.setId(entity.getIdEntity());
                dto.setYoutubeChannel(entity.getYoutubeChannelEntity());
                dto.setHobby(entity.getHobbyEntity());
                return dto;
            }

            private InstructorDetailEntity mapDtoToEntity(InstructorDetail dto) {
                InstructorDetailEntity entity = new InstructorDetailEntity();
                entity.setIdEntity(dto.getId());
                entity.setYoutubeChannelEntity(dto.getYoutubeChannel());
                entity.setHobbyEntity(dto.getHobby());
                return entity;
            }

}