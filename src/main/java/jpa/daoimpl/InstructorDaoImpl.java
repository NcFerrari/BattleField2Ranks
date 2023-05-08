package jpa.daoimpl;

import dto.Instructor;
import jpa.dao.InstructorDao;

import java.util.List;

public class InstructorDaoImpl extends EntityManager implements InstructorDao<Instructor> {

    @Override
    public void saveOrUpdate(Instructor instructor) {

    }

    @Override
    public Instructor getInstructor(int id) {
        return null;
    }

    @Override
    public List<Instructor> getAllInstructor() {
        return null;
    }

    @Override
    public void deleteInstructor(Instructor instructor) {

    }

    @Override
    public void deleteInstructor(int id) {
    }

}