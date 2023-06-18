package jpa.daoimpl;

import business.dto.Vehicles;
import jpa.EntityManager;
import jpa.dao.VehiclesDao;
import jpa.entity.VehiclesEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class VehiclesDaoImpl extends EntityManager implements VehiclesDao {

    @Override
    public void saveOrUpdate(Vehicles vehicles) {
        if (vehicles == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(vehicles));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public Vehicles getVehicles(int id) {
        getSession().beginTransaction();
        VehiclesEntity entity = getSession().get(VehiclesEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Vehicles> getAllVehicles() {
        getSession().beginTransaction();
        @SuppressWarnings("unchecked")
        List<VehiclesEntity> entities = getSession().createQuery("FROM VehiclesEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Vehicles> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteVehicles(Vehicles vehicles) {
        if (vehicles == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(vehicles));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public void deleteVehicles(int id) {
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM VehiclesEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Vehicles mapEntityToDto(VehiclesEntity entity) {
        if (entity == null) {
            return null;
        }
        Vehicles dto = new Vehicles();
        dto.setId(entity.getIdEntity());
        dto.setTime0(entity.getTime0Entity());
        dto.setTime1(entity.getTime1Entity());
        dto.setTime2(entity.getTime2Entity());
        dto.setTime3(entity.getTime3Entity());
        dto.setTime4(entity.getTime4Entity());
        dto.setTime5(entity.getTime5Entity());
        dto.setTime6(entity.getTime6Entity());
        dto.setTimepara(entity.getTimeparaEntity());
        dto.setKills0(entity.getKills0Entity());
        dto.setKills1(entity.getKills1Entity());
        dto.setKills2(entity.getKills2Entity());
        dto.setKills3(entity.getKills3Entity());
        dto.setKills4(entity.getKills4Entity());
        dto.setKills5(entity.getKills5Entity());
        dto.setKills6(entity.getKills6Entity());
        dto.setDeaths0(entity.getDeaths0Entity());
        dto.setDeaths1(entity.getDeaths1Entity());
        dto.setDeaths2(entity.getDeaths2Entity());
        dto.setDeaths3(entity.getDeaths3Entity());
        dto.setDeaths4(entity.getDeaths4Entity());
        dto.setDeaths5(entity.getDeaths5Entity());
        dto.setDeaths6(entity.getDeaths6Entity());
        dto.setRk0(entity.getRk0Entity());
        dto.setRk1(entity.getRk1Entity());
        dto.setRk2(entity.getRk2Entity());
        dto.setRk3(entity.getRk3Entity());
        dto.setRk4(entity.getRk4Entity());
        dto.setRk5(entity.getRk5Entity());
        dto.setRk6(entity.getRk6Entity());
        return dto;
    }

    private VehiclesEntity mapDtoToEntity(Vehicles dto) {
        if (dto == null) {
            return null;
        }
        VehiclesEntity entity = new VehiclesEntity();
        entity.setIdEntity(dto.getId());
        entity.setTime0Entity(dto.getTime0());
        entity.setTime1Entity(dto.getTime1());
        entity.setTime2Entity(dto.getTime2());
        entity.setTime3Entity(dto.getTime3());
        entity.setTime4Entity(dto.getTime4());
        entity.setTime5Entity(dto.getTime5());
        entity.setTime6Entity(dto.getTime6());
        entity.setTimeparaEntity(dto.getTimepara());
        entity.setKills0Entity(dto.getKills0());
        entity.setKills1Entity(dto.getKills1());
        entity.setKills2Entity(dto.getKills2());
        entity.setKills3Entity(dto.getKills3());
        entity.setKills4Entity(dto.getKills4());
        entity.setKills5Entity(dto.getKills5());
        entity.setKills6Entity(dto.getKills6());
        entity.setDeaths0Entity(dto.getDeaths0());
        entity.setDeaths1Entity(dto.getDeaths1());
        entity.setDeaths2Entity(dto.getDeaths2());
        entity.setDeaths3Entity(dto.getDeaths3());
        entity.setDeaths4Entity(dto.getDeaths4());
        entity.setDeaths5Entity(dto.getDeaths5());
        entity.setDeaths6Entity(dto.getDeaths6());
        entity.setRk0Entity(dto.getRk0());
        entity.setRk1Entity(dto.getRk1());
        entity.setRk2Entity(dto.getRk2());
        entity.setRk3Entity(dto.getRk3());
        entity.setRk4Entity(dto.getRk4());
        entity.setRk5Entity(dto.getRk5());
        entity.setRk6Entity(dto.getRk6());
        return entity;
    }
}