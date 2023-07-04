package lp.jpa;

import generator.service.LoggerService;
import generator.serviceimpl.LoggerServiceImpl;
import lp.jpa.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class EntityManager {

    private SessionFactory factory;
    private Session session;

    protected EntityManager() {
        try {
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
        } catch (Exception exp) {
            LoggerService logger = LoggerServiceImpl.getInstance(EntityManager.class);
            logger.getLog().error("Cannot log to database");
        }
    }

    public Session getSession() {
        if (factory != null && (session == null || !session.isOpen())) {
            session = Objects.requireNonNull(factory).getCurrentSession();
        }
        return session;
    }
}