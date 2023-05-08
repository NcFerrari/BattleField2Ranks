package jpa.daoimpl;

import dto.Maps;
import jpa.dao.MapsDao;
import jpa.entity.MapsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MapsDaoImpl extends EntityManager implements MapsDao<Maps> {

    @Override
    public void saveOrUpdate(Maps maps) {
        if (maps != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(maps));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Maps getMaps(int id) {
        getSession().beginTransaction();
        MapsEntity entity = getSession().get(MapsEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Maps> getAllMaps() {
        getSession().beginTransaction();
        List<MapsEntity> entities = getSession().createQuery("FROM MapsEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Maps> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteMaps(Maps maps) {
        if (maps != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(maps));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteMaps(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM MapsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Maps mapEntityToDto(MapsEntity entity) {
        if (entity == null) {
            return null;
        }
        Maps dto = new Maps();
        dto.setId(entity.getIdEntity());
        dto.setMapid(entity.getMapidEntity());
        dto.setTime(entity.getTimeEntity());
        dto.setWin(entity.getWinEntity());
        dto.setLoss(entity.getLossEntity());
        dto.setBest(entity.getBestEntity());
        dto.setWorst(entity.getWorstEntity());
        return dto;
    }

    private MapsEntity mapDtoToEntity(Maps dto) {
        MapsEntity entity = new MapsEntity();
        entity.setIdEntity(dto.getId());
        entity.setMapidEntity(dto.getMapid());
        entity.setTimeEntity(dto.getTime());
        entity.setWinEntity(dto.getWin());
        entity.setLossEntity(dto.getLoss());
        entity.setBestEntity(dto.getBest());
        entity.setWorstEntity(dto.getWorst());
        return entity;
    }

}