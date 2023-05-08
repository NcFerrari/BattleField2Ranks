package safe.jpa.dao;

import java.util.List;

public interface StudentDao<Student> {

    void saveOrUpdate(Student student);

    Student getStudent(int id);

    List<Student> getAllStudent();

    void deleteStudent(Student student);

    void deleteStudent(int id);

}