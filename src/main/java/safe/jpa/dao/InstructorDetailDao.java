package safe.jpa.dao;

import java.util.List;

public interface InstructorDetailDao<InstructorDetail> {

    void saveOrUpdate(InstructorDetail instructorDetail);

    InstructorDetail getInstructorDetail(int id);

    List<InstructorDetail> getAllInstructorDetail();

    void deleteInstructorDetail(InstructorDetail instructorDetail);

    void deleteInstructorDetail(int id);

}