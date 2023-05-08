package jpa.daoimpl;

import dto.Unlocks;
import jpa.dao.UnlocksDao;
import jpa.entity.UnlocksEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UnlocksDaoImpl extends EntityManager implements UnlocksDao<Unlocks> {

    @Override
    public void saveOrUpdate(Unlocks unlocks) {
        if (unlocks != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(unlocks));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Unlocks getUnlocks(int id) {
        getSession().beginTransaction();
        UnlocksEntity entity = getSession().get(UnlocksEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Unlocks> getAllUnlocks() {
        getSession().beginTransaction();
        List<UnlocksEntity> entities = getSession().createQuery("FROM UnlocksEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Unlocks> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteUnlocks(Unlocks unlocks) {
        if (unlocks != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(unlocks));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteUnlocks(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM UnlocksEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Unlocks mapEntityToDto(UnlocksEntity entity) {
        if (entity == null) {
            return null;
        }
        Unlocks dto = new Unlocks();
        dto.setId(entity.getIdEntity());
        dto.setKit(entity.getKitEntity());
        dto.setState(entity.getStateEntity());
        return dto;
    }

    private UnlocksEntity mapDtoToEntity(Unlocks dto) {
        UnlocksEntity entity = new UnlocksEntity();
        entity.setIdEntity(dto.getId());
        entity.setKitEntity(dto.getKit());
        entity.setStateEntity(dto.getState());
        return entity;
    }

}