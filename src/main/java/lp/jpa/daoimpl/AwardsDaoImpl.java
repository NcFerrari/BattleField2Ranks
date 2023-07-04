package lp.jpa.daoimpl;

import lp.business.dto.Awards;
import lp.jpa.EntityManager;
import lp.jpa.dao.AwardsDao;
import lp.jpa.entity.AwardsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AwardsDaoImpl extends EntityManager implements AwardsDao {

    @Override
    public void saveOrUpdate(Awards awards) {
        if (getSession() == null || awards == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(awards));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public Awards getAwards(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        AwardsEntity entity = getSession().get(AwardsEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Awards> getAllAwards() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        @SuppressWarnings("unchecked")
        List<AwardsEntity> entities = getSession().createQuery("FROM AwardsEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Awards> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteAwards(Awards awards) {
        if (getSession() == null || awards == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(awards));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public void deleteAwards(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM AwardsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Awards mapEntityToDto(AwardsEntity entity) {
        if (entity == null) {
            return null;
        }
        Awards dto = new Awards();
        dto.setId(entity.getIdEntity());
        dto.setAwd(entity.getAwdEntity());
        dto.setLevel(entity.getLevelEntity());
        dto.setEarned(entity.getEarnedEntity());
        dto.setFirst(entity.getFirstEntity());
        return dto;
    }

    private AwardsEntity mapDtoToEntity(Awards dto) {
        if (dto == null) {
            return null;
        }
        AwardsEntity entity = new AwardsEntity();
        entity.setIdEntity(dto.getId());
        entity.setAwdEntity(dto.getAwd());
        entity.setLevelEntity(dto.getLevel());
        entity.setEarnedEntity(dto.getEarned());
        entity.setFirstEntity(dto.getFirst());
        return entity;
    }
}