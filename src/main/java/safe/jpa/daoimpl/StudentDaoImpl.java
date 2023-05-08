package safe.jpa.daoimpl;

import safe.dto.Student;
import safe.jpa.dao.StudentDao;

import java.util.List;

public class StudentDaoImpl extends EntityManager implements StudentDao<Student> {

    @Override
    public void saveOrUpdate(Student student) {

    }

    @Override
    public Student getStudent(int id) {
        return null;
    }

    @Override
    public List<Student> getAllStudent() {
        return null;
    }

    @Override
    public void deleteStudent(Student student) {

    }

    @Override
    public void deleteStudent(int id) {
    }

}