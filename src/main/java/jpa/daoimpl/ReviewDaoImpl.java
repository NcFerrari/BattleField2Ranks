package jpa.daoimpl;

import dto.Review;
import jpa.dao.ReviewDao;
import jpa.entity.ReviewEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl extends EntityManager implements ReviewDao<Review> {

    @Override
    public void saveOrUpdate(Review review) {

        if (review != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(review));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Review getReview(int id) {
        getSession().beginTransaction();
        ReviewEntity entity = getSession().get(ReviewEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Review> getAllReview() {
        getSession().beginTransaction();
        List<ReviewEntity> entities = getSession().createQuery("FROM ReviewEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Review> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteReview(Review review) {

        if (review != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(review));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteReview(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM ReviewEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Review mapEntityToDto(ReviewEntity entity) {
        Review dto = new Review();
        dto.setId(entity.getIdEntity());
        dto.setComment(entity.getCommentEntity());
        dto.setCourseId(entity.getCourseIdEntity());
        return dto;
    }

    private ReviewEntity mapDtoToEntity(Review dto) {
        ReviewEntity entity = new ReviewEntity();
        entity.setIdEntity(dto.getId());
        entity.setCommentEntity(dto.getComment());
        entity.setCourseIdEntity(dto.getCourseId());
        return entity;
    }

}