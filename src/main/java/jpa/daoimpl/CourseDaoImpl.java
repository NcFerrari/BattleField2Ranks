package jpa.daoimpl;

import dto.Course;
import jpa.dao.CourseDao;

import java.util.List;

public class CourseDaoImpl extends EntityManager implements CourseDao<Course> {

    @Override
    public void saveOrUpdate(Course course) {

    }

    @Override
    public Course getCourse(int id) {
        return null;
    }

    @Override
    public List<Course> getAllCourse() {
        return null;
    }

    @Override
    public void deleteCourse(Course course) {

    }

    @Override
    public void deleteCourse(int id) {
    }

}