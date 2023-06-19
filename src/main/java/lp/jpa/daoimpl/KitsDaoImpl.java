package lp.jpa.daoimpl;

import lp.business.dto.Kits;
import lp.jpa.EntityManager;
import lp.jpa.dao.KitsDao;
import lp.jpa.entity.KitsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class KitsDaoImpl extends EntityManager implements KitsDao {

    @Override
    public void saveOrUpdate(Kits kits) {
        if (kits == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(kits));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public Kits getKits(int id) {
        getSession().beginTransaction();
        KitsEntity entity = getSession().get(KitsEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Kits> getAllKits() {
        getSession().beginTransaction();
        @SuppressWarnings("unchecked")
        List<KitsEntity> entities = getSession().createQuery("FROM KitsEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Kits> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteKits(Kits kits) {
        if (kits == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(kits));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public void deleteKits(int id) {
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM KitsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Kits mapEntityToDto(KitsEntity entity) {
        if (entity == null) {
            return null;
        }
        Kits dto = new Kits();
        dto.setId(entity.getIdEntity());
        dto.setTime0(entity.getTime0Entity());
        dto.setKills0(entity.getKills0Entity());
        dto.setDeaths0(entity.getDeaths0Entity());
        dto.setTime1(entity.getTime1Entity());
        dto.setKills1(entity.getKills1Entity());
        dto.setDeaths1(entity.getDeaths1Entity());
        dto.setTime2(entity.getTime2Entity());
        dto.setKills2(entity.getKills2Entity());
        dto.setDeaths2(entity.getDeaths2Entity());
        dto.setTime3(entity.getTime3Entity());
        dto.setKills3(entity.getKills3Entity());
        dto.setDeaths3(entity.getDeaths3Entity());
        dto.setTime4(entity.getTime4Entity());
        dto.setKills4(entity.getKills4Entity());
        dto.setDeaths4(entity.getDeaths4Entity());
        dto.setTime5(entity.getTime5Entity());
        dto.setKills5(entity.getKills5Entity());
        dto.setDeaths5(entity.getDeaths5Entity());
        dto.setTime6(entity.getTime6Entity());
        dto.setKills6(entity.getKills6Entity());
        dto.setDeaths6(entity.getDeaths6Entity());
        return dto;
    }

    private KitsEntity mapDtoToEntity(Kits dto) {
        if (dto == null) {
            return null;
        }
        KitsEntity entity = new KitsEntity();
        entity.setIdEntity(dto.getId());
        entity.setTime0Entity(dto.getTime0());
        entity.setKills0Entity(dto.getKills0());
        entity.setDeaths0Entity(dto.getDeaths0());
        entity.setTime1Entity(dto.getTime1());
        entity.setKills1Entity(dto.getKills1());
        entity.setDeaths1Entity(dto.getDeaths1());
        entity.setTime2Entity(dto.getTime2());
        entity.setKills2Entity(dto.getKills2());
        entity.setDeaths2Entity(dto.getDeaths2());
        entity.setTime3Entity(dto.getTime3());
        entity.setKills3Entity(dto.getKills3());
        entity.setDeaths3Entity(dto.getDeaths3());
        entity.setTime4Entity(dto.getTime4());
        entity.setKills4Entity(dto.getKills4());
        entity.setDeaths4Entity(dto.getDeaths4());
        entity.setTime5Entity(dto.getTime5());
        entity.setKills5Entity(dto.getKills5());
        entity.setDeaths5Entity(dto.getDeaths5());
        entity.setTime6Entity(dto.getTime6());
        entity.setKills6Entity(dto.getKills6());
        entity.setDeaths6Entity(dto.getDeaths6());
        return entity;
    }
}