package jpa.daoimpl;

import business.dto.Army;
import jpa.EntityManager;
import jpa.dao.ArmyDao;
import jpa.entity.ArmyEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ArmyDaoImpl extends EntityManager implements ArmyDao<Army> {

    @Override
    public void saveOrUpdate(Army army) {
        if (army != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(army));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Army getArmy(int id) {
        getSession().beginTransaction();
        ArmyEntity entity = getSession().get(ArmyEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Army> getAllArmy() {
        getSession().beginTransaction();
        List<ArmyEntity> entities = getSession().createQuery("FROM ArmyEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Army> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteArmy(Army army) {
        if (army != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(army));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteArmy(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM ArmyEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Army mapEntityToDto(ArmyEntity entity) {
        if (entity == null) {
            return null;
        }
        Army dto = new Army();
        dto.setId(entity.getIdEntity());
        dto.setTime0(entity.getTime0Entity());
        dto.setWin0(entity.getWin0Entity());
        dto.setLoss0(entity.getLoss0Entity());
        dto.setScore0(entity.getScore0Entity());
        dto.setBest0(entity.getBest0Entity());
        dto.setWorst0(entity.getWorst0Entity());
        dto.setBrnd0(entity.getBrnd0Entity());
        dto.setTime1(entity.getTime1Entity());
        dto.setWin1(entity.getWin1Entity());
        dto.setLoss1(entity.getLoss1Entity());
        dto.setScore1(entity.getScore1Entity());
        dto.setBest1(entity.getBest1Entity());
        dto.setWorst1(entity.getWorst1Entity());
        dto.setBrnd1(entity.getBrnd1Entity());
        dto.setTime2(entity.getTime2Entity());
        dto.setWin2(entity.getWin2Entity());
        dto.setLoss2(entity.getLoss2Entity());
        dto.setScore2(entity.getScore2Entity());
        dto.setBest2(entity.getBest2Entity());
        dto.setWorst2(entity.getWorst2Entity());
        dto.setBrnd2(entity.getBrnd2Entity());
        dto.setTime3(entity.getTime3Entity());
        dto.setWin3(entity.getWin3Entity());
        dto.setLoss3(entity.getLoss3Entity());
        dto.setScore3(entity.getScore3Entity());
        dto.setBest3(entity.getBest3Entity());
        dto.setWorst3(entity.getWorst3Entity());
        dto.setBrnd3(entity.getBrnd3Entity());
        dto.setTime4(entity.getTime4Entity());
        dto.setWin4(entity.getWin4Entity());
        dto.setLoss4(entity.getLoss4Entity());
        dto.setScore4(entity.getScore4Entity());
        dto.setBest4(entity.getBest4Entity());
        dto.setWorst4(entity.getWorst4Entity());
        dto.setBrnd4(entity.getBrnd4Entity());
        dto.setTime5(entity.getTime5Entity());
        dto.setWin5(entity.getWin5Entity());
        dto.setLoss5(entity.getLoss5Entity());
        dto.setScore5(entity.getScore5Entity());
        dto.setBest5(entity.getBest5Entity());
        dto.setWorst5(entity.getWorst5Entity());
        dto.setBrnd5(entity.getBrnd5Entity());
        dto.setTime6(entity.getTime6Entity());
        dto.setWin6(entity.getWin6Entity());
        dto.setLoss6(entity.getLoss6Entity());
        dto.setScore6(entity.getScore6Entity());
        dto.setBest6(entity.getBest6Entity());
        dto.setWorst6(entity.getWorst6Entity());
        dto.setBrnd6(entity.getBrnd6Entity());
        dto.setTime7(entity.getTime7Entity());
        dto.setWin7(entity.getWin7Entity());
        dto.setLoss7(entity.getLoss7Entity());
        dto.setScore7(entity.getScore7Entity());
        dto.setBest7(entity.getBest7Entity());
        dto.setWorst7(entity.getWorst7Entity());
        dto.setBrnd7(entity.getBrnd7Entity());
        dto.setTime8(entity.getTime8Entity());
        dto.setWin8(entity.getWin8Entity());
        dto.setLoss8(entity.getLoss8Entity());
        dto.setScore8(entity.getScore8Entity());
        dto.setBest8(entity.getBest8Entity());
        dto.setWorst8(entity.getWorst8Entity());
        dto.setBrnd8(entity.getBrnd8Entity());
        dto.setTime9(entity.getTime9Entity());
        dto.setWin9(entity.getWin9Entity());
        dto.setLoss9(entity.getLoss9Entity());
        dto.setScore9(entity.getScore9Entity());
        dto.setBest9(entity.getBest9Entity());
        dto.setWorst9(entity.getWorst9Entity());
        dto.setBrnd9(entity.getBrnd9Entity());
        dto.setTime10(entity.getTime10Entity());
        dto.setWin10(entity.getWin10Entity());
        dto.setLoss10(entity.getLoss10Entity());
        dto.setScore10(entity.getScore10Entity());
        dto.setBest10(entity.getBest10Entity());
        dto.setWorst10(entity.getWorst10Entity());
        dto.setBrnd10(entity.getBrnd10Entity());
        dto.setTime11(entity.getTime11Entity());
        dto.setWin11(entity.getWin11Entity());
        dto.setLoss11(entity.getLoss11Entity());
        dto.setScore11(entity.getScore11Entity());
        dto.setBest11(entity.getBest11Entity());
        dto.setWorst11(entity.getWorst11Entity());
        dto.setBrnd11(entity.getBrnd11Entity());
        return dto;
    }

    private ArmyEntity mapDtoToEntity(Army dto) {
        ArmyEntity entity = new ArmyEntity();
        entity.setIdEntity(dto.getId());
        entity.setTime0Entity(dto.getTime0());
        entity.setWin0Entity(dto.getWin0());
        entity.setLoss0Entity(dto.getLoss0());
        entity.setScore0Entity(dto.getScore0());
        entity.setBest0Entity(dto.getBest0());
        entity.setWorst0Entity(dto.getWorst0());
        entity.setBrnd0Entity(dto.getBrnd0());
        entity.setTime1Entity(dto.getTime1());
        entity.setWin1Entity(dto.getWin1());
        entity.setLoss1Entity(dto.getLoss1());
        entity.setScore1Entity(dto.getScore1());
        entity.setBest1Entity(dto.getBest1());
        entity.setWorst1Entity(dto.getWorst1());
        entity.setBrnd1Entity(dto.getBrnd1());
        entity.setTime2Entity(dto.getTime2());
        entity.setWin2Entity(dto.getWin2());
        entity.setLoss2Entity(dto.getLoss2());
        entity.setScore2Entity(dto.getScore2());
        entity.setBest2Entity(dto.getBest2());
        entity.setWorst2Entity(dto.getWorst2());
        entity.setBrnd2Entity(dto.getBrnd2());
        entity.setTime3Entity(dto.getTime3());
        entity.setWin3Entity(dto.getWin3());
        entity.setLoss3Entity(dto.getLoss3());
        entity.setScore3Entity(dto.getScore3());
        entity.setBest3Entity(dto.getBest3());
        entity.setWorst3Entity(dto.getWorst3());
        entity.setBrnd3Entity(dto.getBrnd3());
        entity.setTime4Entity(dto.getTime4());
        entity.setWin4Entity(dto.getWin4());
        entity.setLoss4Entity(dto.getLoss4());
        entity.setScore4Entity(dto.getScore4());
        entity.setBest4Entity(dto.getBest4());
        entity.setWorst4Entity(dto.getWorst4());
        entity.setBrnd4Entity(dto.getBrnd4());
        entity.setTime5Entity(dto.getTime5());
        entity.setWin5Entity(dto.getWin5());
        entity.setLoss5Entity(dto.getLoss5());
        entity.setScore5Entity(dto.getScore5());
        entity.setBest5Entity(dto.getBest5());
        entity.setWorst5Entity(dto.getWorst5());
        entity.setBrnd5Entity(dto.getBrnd5());
        entity.setTime6Entity(dto.getTime6());
        entity.setWin6Entity(dto.getWin6());
        entity.setLoss6Entity(dto.getLoss6());
        entity.setScore6Entity(dto.getScore6());
        entity.setBest6Entity(dto.getBest6());
        entity.setWorst6Entity(dto.getWorst6());
        entity.setBrnd6Entity(dto.getBrnd6());
        entity.setTime7Entity(dto.getTime7());
        entity.setWin7Entity(dto.getWin7());
        entity.setLoss7Entity(dto.getLoss7());
        entity.setScore7Entity(dto.getScore7());
        entity.setBest7Entity(dto.getBest7());
        entity.setWorst7Entity(dto.getWorst7());
        entity.setBrnd7Entity(dto.getBrnd7());
        entity.setTime8Entity(dto.getTime8());
        entity.setWin8Entity(dto.getWin8());
        entity.setLoss8Entity(dto.getLoss8());
        entity.setScore8Entity(dto.getScore8());
        entity.setBest8Entity(dto.getBest8());
        entity.setWorst8Entity(dto.getWorst8());
        entity.setBrnd8Entity(dto.getBrnd8());
        entity.setTime9Entity(dto.getTime9());
        entity.setWin9Entity(dto.getWin9());
        entity.setLoss9Entity(dto.getLoss9());
        entity.setScore9Entity(dto.getScore9());
        entity.setBest9Entity(dto.getBest9());
        entity.setWorst9Entity(dto.getWorst9());
        entity.setBrnd9Entity(dto.getBrnd9());
        entity.setTime10Entity(dto.getTime10());
        entity.setWin10Entity(dto.getWin10());
        entity.setLoss10Entity(dto.getLoss10());
        entity.setScore10Entity(dto.getScore10());
        entity.setBest10Entity(dto.getBest10());
        entity.setWorst10Entity(dto.getWorst10());
        entity.setBrnd10Entity(dto.getBrnd10());
        entity.setTime11Entity(dto.getTime11());
        entity.setWin11Entity(dto.getWin11());
        entity.setLoss11Entity(dto.getLoss11());
        entity.setScore11Entity(dto.getScore11());
        entity.setBest11Entity(dto.getBest11());
        entity.setWorst11Entity(dto.getWorst11());
        entity.setBrnd11Entity(dto.getBrnd11());
        return entity;
    }

}