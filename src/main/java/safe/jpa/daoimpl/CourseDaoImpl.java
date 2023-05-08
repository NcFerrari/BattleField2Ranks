package safe.jpa.daoimpl;

import safe.dto.Course;
import safe.jpa.dao.CourseDao;
import safe.jpa.entity.CourseEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl extends EntityManager implements CourseDao<Course> {

    @Override
    public void saveOrUpdate(Course course) {
        if(course != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(course));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Course getCourse(int id) {
        getSession().beginTransaction();
        CourseEntity courseEntity = getSession().get(CourseEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(courseEntity);
    }

    @Override
    public List<Course> getAllCourse() {
        getSession().beginTransaction();
        List<CourseEntity> entities = getSession().createQuery("FROM CourseEntity ").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Course> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteCourse(Course course) {
        if(course != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(course));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteCourse(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM CourseEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Course mapEntityToDto(CourseEntity entity) {
        if(entity != null) {
            Course dto = new Course();
            dto.setId(entity.getIdEntity());
            dto.setTitle(entity.getTitleEntity());
            dto.setInstructorId(entity.getInstructorIdEntity());
            return dto;
        }
        return null;
    }

    private CourseEntity mapDtoToEntity(Course dto) {
        if(dto != null) {
            CourseEntity entity = new CourseEntity();
            entity.setIdEntity(dto.getId());
            entity.setTitleEntity(dto.getTitle());
            entity.setInstructorIdEntity(dto.getInstructorId());
            return entity;
        }
        return null;
    }

}