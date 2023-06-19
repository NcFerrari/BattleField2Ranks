package lp.jpa;

import lp.jpa.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EntityManager {

    private final SessionFactory factory;
    private Session session;

    protected EntityManager() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/bf2stats")
                .addAnnotatedClass(ArmyEntity.class)
                .addAnnotatedClass(AwardsEntity.class)
                .addAnnotatedClass(Ip2nationEntity.class)
                .addAnnotatedClass(KillsEntity.class)
                .addAnnotatedClass(KitsEntity.class)
                .addAnnotatedClass(MapinfoEntity.class)
                .addAnnotatedClass(MapsEntity.class)
                .addAnnotatedClass(PlayerEntity.class)
                .addAnnotatedClass(PlayerHistoryEntity.class)
                .addAnnotatedClass(RoundHistoryEntity.class)
                .addAnnotatedClass(ServersEntity.class)
                .addAnnotatedClass(UnlocksEntity.class)
                .addAnnotatedClass(VehiclesEntity.class)
                .addAnnotatedClass(WeaponsEntity.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        if (session == null || !session.isOpen()) {
            session = factory.getCurrentSession();
        }
        return session;
    }
}