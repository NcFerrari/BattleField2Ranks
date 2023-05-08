package jpa.dao;

import java.util.List;

public interface CourseDao<Course> {

    void saveOrUpdate(Course course);

    Course getCourse(int id);

    List<Course> getAllCourse();

    void deleteCourse(Course course);

    void deleteCourse(int id);

}