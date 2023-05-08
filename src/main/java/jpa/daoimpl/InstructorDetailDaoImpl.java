package jpa.daoimpl;

import dto.InstructorDetail;
import jpa.dao.InstructorDetailDao;

import java.util.List;

public class InstructorDetailDaoImpl extends EntityManager implements InstructorDetailDao<InstructorDetail> {

    @Override
    public void saveOrUpdate(InstructorDetail instructorDetail) {

    }

    @Override
    public InstructorDetail getInstructorDetail(int id) {
        return null;
    }

    @Override
    public List<InstructorDetail> getAllInstructorDetail() {
        return null;
    }

    @Override
    public void deleteInstructorDetail(InstructorDetail instructorDetail) {

    }

    @Override
    public void deleteInstructorDetail(int id) {
    }

}