package lp.jpa.daoimpl;

import lp.business.dto.Servers;
import lp.jpa.EntityManager;
import lp.jpa.dao.ServersDao;
import lp.jpa.entity.ServersEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ServersDaoImpl extends EntityManager implements ServersDao {

    @Override
    public void saveOrUpdate(Servers servers) {
        if (servers == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(servers));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public Servers getServers(int id) {
        getSession().beginTransaction();
        ServersEntity entity = getSession().get(ServersEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Servers> getAllServers() {
        getSession().beginTransaction();
        @SuppressWarnings("unchecked")
        List<ServersEntity> entities = getSession().createQuery("FROM ServersEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Servers> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteServers(Servers servers) {
        if (servers == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(servers));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public void deleteServers(int id) {
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM ServersEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Servers mapEntityToDto(ServersEntity entity) {
        if (entity == null) {
            return null;
        }
        Servers dto = new Servers();
        dto.setId(entity.getIdEntity());
        dto.setIp(entity.getIpEntity());
        dto.setPrefix(entity.getPrefixEntity());
        dto.setName(entity.getNameEntity());
        dto.setPort(entity.getPortEntity());
        dto.setQueryport(entity.getQueryportEntity());
        dto.setLastupdate(entity.getLastupdateEntity());
        return dto;
    }

    private ServersEntity mapDtoToEntity(Servers dto) {
        if (dto == null) {
            return null;
        }
        ServersEntity entity = new ServersEntity();
        entity.setIdEntity(dto.getId());
        entity.setIpEntity(dto.getIp());
        entity.setPrefixEntity(dto.getPrefix());
        entity.setNameEntity(dto.getName());
        entity.setPortEntity(dto.getPort());
        entity.setQueryportEntity(dto.getQueryport());
        entity.setLastupdateEntity(dto.getLastupdate());
        return entity;
    }
}