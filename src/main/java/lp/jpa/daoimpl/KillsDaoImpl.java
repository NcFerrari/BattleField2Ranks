package lp.jpa.daoimpl;

import lp.business.dto.Kills;
import lp.jpa.EntityManager;
import lp.jpa.dao.KillsDao;
import lp.jpa.entity.KillsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class KillsDaoImpl extends EntityManager implements KillsDao {

    @Override
    public void saveOrUpdate(Kills kills) {
        if (getSession() == null || kills == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(kills));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public Kills getKills(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        KillsEntity entity = getSession().get(KillsEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Kills> getAllKills() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        @SuppressWarnings("unchecked")
        List<KillsEntity> entities = getSession().createQuery("FROM KillsEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Kills> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteKills(Kills kills) {
        if (getSession() == null || kills == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(kills));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public void deleteKills(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM KillsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Kills mapEntityToDto(KillsEntity entity) {
        if (entity == null) {
            return null;
        }
        Kills dto = new Kills();
        dto.setAttacker(entity.getAttackerEntity());
        dto.setVictim(entity.getVictimEntity());
        dto.setCount(entity.getCountEntity());
        return dto;
    }

    private KillsEntity mapDtoToEntity(Kills dto) {
        if (dto == null) {
            return null;
        }
        KillsEntity entity = new KillsEntity();
        entity.setAttackerEntity(dto.getAttacker());
        entity.setVictimEntity(dto.getVictim());
        entity.setCountEntity(dto.getCount());
        return entity;
    }
}