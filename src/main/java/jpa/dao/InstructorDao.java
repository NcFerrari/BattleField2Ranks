package jpa.dao;

import java.util.List;

public interface InstructorDao<Instructor> {

    void saveOrUpdate(Instructor instructor);

    Instructor getInstructor(int id);

    List<Instructor> getAllInstructor();

    void deleteInstructor(Instructor instructor);

    void deleteInstructor(int id);

}