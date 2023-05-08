package jpa.daoimpl;

import jpa.entity.*;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Data
class EntityManager {

    private SessionFactory factory;
    private Session session;

    protected EntityManager() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(InstructorEntity.class)
                .addAnnotatedClass(InstructorDetailEntity.class)
                .addAnnotatedClass(StudentEntity.class)
                .addAnnotatedClass(ReviewEntity.class)
                .addAnnotatedClass(CourseEntity.class)
                .addAnnotatedClass(EmployeeEntity.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        if (session == null || !session.isOpen()) {
            session = getFactory().getCurrentSession();
        }
        return session;
    }
}