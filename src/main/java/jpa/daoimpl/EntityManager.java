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
                .addAnnotatedClass(KillsEntity.class)
                .addAnnotatedClass(MapinfoEntity.class)
                .addAnnotatedClass(MapsEntity.class)
                .addAnnotatedClass(VehiclesEntity.class)
                .addAnnotatedClass(KitsEntity.class)
                .addAnnotatedClass(PlayerHistoryEntity.class)
                .addAnnotatedClass(ArmyEntity.class)
                .addAnnotatedClass(ServersEntity.class)
                .addAnnotatedClass(AwardsEntity.class)
                .addAnnotatedClass(UnlocksEntity.class)
                .addAnnotatedClass(VersionEntity.class)
                .addAnnotatedClass(Ip2nationEntity.class)
                .addAnnotatedClass(RoundHistoryEntity.class)
                .addAnnotatedClass(WeaponsEntity.class)
                .addAnnotatedClass(PlayerEntity.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        if (session == null || !session.isOpen()) {
            session = getFactory().getCurrentSession();
        }
        return session;
    }
}