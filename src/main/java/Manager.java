import jpa.entity.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Manager {

    public Manager() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public static void main(String[] args) {
        new Manager();
    }
}