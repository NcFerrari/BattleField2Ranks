package jpa.dao;

import java.util.List;

public interface CourseDao<CourseEntity> {

    void saveOrUpdate(CourseEntity courseEntity);

    CourseEntity getCourseEntity();

    List<CourseEntity> getAllCourseEntity();

    void deleteCourseEntity(CourseEntity courseEntity);

}
