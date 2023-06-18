package jpa.daoimpl;

import business.dto.Mapinfo;
import jpa.EntityManager;
import jpa.dao.MapinfoDao;
import jpa.entity.MapinfoEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MapinfoDaoImpl extends EntityManager implements MapinfoDao {

    @Override
    public void saveOrUpdate(Mapinfo mapinfo) {
        if (mapinfo == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(mapinfo));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public Mapinfo getMapinfo(int id) {
        getSession().beginTransaction();
        MapinfoEntity entity = getSession().get(MapinfoEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Mapinfo> getAllMapinfo() {
        getSession().beginTransaction();
        @SuppressWarnings("unchecked")
        List<MapinfoEntity> entities = getSession().createQuery("FROM MapinfoEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Mapinfo> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteMapinfo(Mapinfo mapinfo) {
        if (mapinfo == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(mapinfo));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public void deleteMapinfo(int id) {
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM MapinfoEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Mapinfo mapEntityToDto(MapinfoEntity entity) {
        if (entity == null) {
            return null;
        }
        Mapinfo dto = new Mapinfo();
        dto.setId(entity.getIdEntity());
        dto.setName(entity.getNameEntity());
        dto.setScore(entity.getScoreEntity());
        dto.setTime(entity.getTimeEntity());
        dto.setTimes(entity.getTimesEntity());
        dto.setKills(entity.getKillsEntity());
        dto.setDeaths(entity.getDeathsEntity());
        dto.setCustom(entity.getCustomEntity());
        return dto;
    }

    private MapinfoEntity mapDtoToEntity(Mapinfo dto) {
        if (dto == null) {
            return null;
        }
        MapinfoEntity entity = new MapinfoEntity();
        entity.setIdEntity(dto.getId());
        entity.setNameEntity(dto.getName());
        entity.setScoreEntity(dto.getScore());
        entity.setTimeEntity(dto.getTime());
        entity.setTimesEntity(dto.getTimes());
        entity.setKillsEntity(dto.getKills());
        entity.setDeathsEntity(dto.getDeaths());
        entity.setCustomEntity(dto.getCustom());
        return entity;
    }
}