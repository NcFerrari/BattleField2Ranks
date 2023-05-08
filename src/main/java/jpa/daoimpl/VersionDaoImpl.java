package jpa.daoimpl;

import dto.Version;
import jpa.dao.VersionDao;
import jpa.entity.VersionEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class VersionDaoImpl extends EntityManager implements VersionDao<Version> {

    @Override
    public void saveOrUpdate(Version version) {
        if (version != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(version));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Version getVersion(int id) {
        getSession().beginTransaction();
        VersionEntity entity = getSession().get(VersionEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Version> getAllVersion() {
        getSession().beginTransaction();
        List<VersionEntity> entities = getSession().createQuery("FROM VersionEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Version> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteVersion(Version version) {
        if (version != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(version));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteVersion(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM VersionEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Version mapEntityToDto(VersionEntity entity) {
        if (entity == null) {
            return null;
        }
        Version dto = new Version();
        dto.setDbver(entity.getDbverEntity());
        dto.setDbdate(entity.getDbdateEntity());
        return dto;
    }

    private VersionEntity mapDtoToEntity(Version dto) {
        VersionEntity entity = new VersionEntity();
        entity.setDbverEntity(dto.getDbver());
        entity.setDbdateEntity(dto.getDbdate());
        return entity;
    }

}