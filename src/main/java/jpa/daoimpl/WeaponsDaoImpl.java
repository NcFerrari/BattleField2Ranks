package jpa.daoimpl;

import business.dto.Weapons;
import jpa.EntityManager;
import jpa.dao.WeaponsDao;
import jpa.entity.WeaponsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class WeaponsDaoImpl extends EntityManager implements WeaponsDao<Weapons> {

    @Override
    public void saveOrUpdate(Weapons weapons) {
        if (weapons != null) {
            getSession().beginTransaction();
            getSession().saveOrUpdate(mapDtoToEntity(weapons));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public Weapons getWeapons(int id) {
        getSession().beginTransaction();
        WeaponsEntity entity = getSession().get(WeaponsEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Weapons> getAllWeapons() {
        getSession().beginTransaction();
        List<WeaponsEntity> entities = getSession().createQuery("FROM WeaponsEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<Weapons> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteWeapons(Weapons weapons) {
        if (weapons != null) {
            getSession().beginTransaction();
            getSession().delete(mapDtoToEntity(weapons));
            getSession().getTransaction().commit();
            getSession().close();
        }
    }

    @Override
    public void deleteWeapons(int id) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("DELETE FROM WeaponsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private Weapons mapEntityToDto(WeaponsEntity entity) {
        if (entity == null) {
            return null;
        }
        Weapons dto = new Weapons();
        dto.setId(entity.getIdEntity());
        dto.setTime0(entity.getTime0Entity());
        dto.setTime1(entity.getTime1Entity());
        dto.setTime2(entity.getTime2Entity());
        dto.setTime3(entity.getTime3Entity());
        dto.setTime4(entity.getTime4Entity());
        dto.setTime5(entity.getTime5Entity());
        dto.setTime6(entity.getTime6Entity());
        dto.setTime7(entity.getTime7Entity());
        dto.setTime8(entity.getTime8Entity());
        dto.setKnifetime(entity.getKnifetimeEntity());
        dto.setC4time(entity.getC4timeEntity());
        dto.setHandgrenadetime(entity.getHandgrenadetimeEntity());
        dto.setClaymoretime(entity.getClaymoretimeEntity());
        dto.setShockpadtime(entity.getShockpadtimeEntity());
        dto.setAtminetime(entity.getAtminetimeEntity());
        dto.setTacticaltime(entity.getTacticaltimeEntity());
        dto.setGrapplinghooktime(entity.getGrapplinghooktimeEntity());
        dto.setZiplinetime(entity.getZiplinetimeEntity());
        dto.setKills0(entity.getKills0Entity());
        dto.setKills1(entity.getKills1Entity());
        dto.setKills2(entity.getKills2Entity());
        dto.setKills3(entity.getKills3Entity());
        dto.setKills4(entity.getKills4Entity());
        dto.setKills5(entity.getKills5Entity());
        dto.setKills6(entity.getKills6Entity());
        dto.setKills7(entity.getKills7Entity());
        dto.setKills8(entity.getKills8Entity());
        dto.setKnifekills(entity.getKnifekillsEntity());
        dto.setC4kills(entity.getC4killsEntity());
        dto.setHandgrenadekills(entity.getHandgrenadekillsEntity());
        dto.setClaymorekills(entity.getClaymorekillsEntity());
        dto.setShockpadkills(entity.getShockpadkillsEntity());
        dto.setAtminekills(entity.getAtminekillsEntity());
        dto.setDeaths0(entity.getDeaths0Entity());
        dto.setDeaths1(entity.getDeaths1Entity());
        dto.setDeaths2(entity.getDeaths2Entity());
        dto.setDeaths3(entity.getDeaths3Entity());
        dto.setDeaths4(entity.getDeaths4Entity());
        dto.setDeaths5(entity.getDeaths5Entity());
        dto.setDeaths6(entity.getDeaths6Entity());
        dto.setDeaths7(entity.getDeaths7Entity());
        dto.setDeaths8(entity.getDeaths8Entity());
        dto.setKnifedeaths(entity.getKnifedeathsEntity());
        dto.setC4deaths(entity.getC4deathsEntity());
        dto.setHandgrenadedeaths(entity.getHandgrenadedeathsEntity());
        dto.setClaymoredeaths(entity.getClaymoredeathsEntity());
        dto.setShockpaddeaths(entity.getShockpaddeathsEntity());
        dto.setAtminedeaths(entity.getAtminedeathsEntity());
        dto.setZiplinedeaths(entity.getZiplinedeathsEntity());
        dto.setGrapplinghookdeaths(entity.getGrapplinghookdeathsEntity());
        dto.setTacticaldeployed(entity.getTacticaldeployedEntity());
        dto.setGrapplinghookdeployed(entity.getGrapplinghookdeployedEntity());
        dto.setZiplinedeployed(entity.getZiplinedeployedEntity());
        dto.setFired0(entity.getFired0Entity());
        dto.setFired1(entity.getFired1Entity());
        dto.setFired2(entity.getFired2Entity());
        dto.setFired3(entity.getFired3Entity());
        dto.setFired4(entity.getFired4Entity());
        dto.setFired5(entity.getFired5Entity());
        dto.setFired6(entity.getFired6Entity());
        dto.setFired7(entity.getFired7Entity());
        dto.setFired8(entity.getFired8Entity());
        dto.setKnifefired(entity.getKnifefiredEntity());
        dto.setC4fired(entity.getC4firedEntity());
        dto.setClaymorefired(entity.getClaymorefiredEntity());
        dto.setHandgrenadefired(entity.getHandgrenadefiredEntity());
        dto.setShockpadfired(entity.getShockpadfiredEntity());
        dto.setAtminefired(entity.getAtminefiredEntity());
        dto.setHit0(entity.getHit0Entity());
        dto.setHit1(entity.getHit1Entity());
        dto.setHit2(entity.getHit2Entity());
        dto.setHit3(entity.getHit3Entity());
        dto.setHit4(entity.getHit4Entity());
        dto.setHit5(entity.getHit5Entity());
        dto.setHit6(entity.getHit6Entity());
        dto.setHit7(entity.getHit7Entity());
        dto.setHit8(entity.getHit8Entity());
        dto.setKnifehit(entity.getKnifehitEntity());
        dto.setC4hit(entity.getC4hitEntity());
        dto.setClaymorehit(entity.getClaymorehitEntity());
        dto.setHandgrenadehit(entity.getHandgrenadehitEntity());
        dto.setShockpadhit(entity.getShockpadhitEntity());
        dto.setAtminehit(entity.getAtminehitEntity());
        return dto;
    }

    private WeaponsEntity mapDtoToEntity(Weapons dto) {
        WeaponsEntity entity = new WeaponsEntity();
        entity.setIdEntity(dto.getId());
        entity.setTime0Entity(dto.getTime0());
        entity.setTime1Entity(dto.getTime1());
        entity.setTime2Entity(dto.getTime2());
        entity.setTime3Entity(dto.getTime3());
        entity.setTime4Entity(dto.getTime4());
        entity.setTime5Entity(dto.getTime5());
        entity.setTime6Entity(dto.getTime6());
        entity.setTime7Entity(dto.getTime7());
        entity.setTime8Entity(dto.getTime8());
        entity.setKnifetimeEntity(dto.getKnifetime());
        entity.setC4timeEntity(dto.getC4time());
        entity.setHandgrenadetimeEntity(dto.getHandgrenadetime());
        entity.setClaymoretimeEntity(dto.getClaymoretime());
        entity.setShockpadtimeEntity(dto.getShockpadtime());
        entity.setAtminetimeEntity(dto.getAtminetime());
        entity.setTacticaltimeEntity(dto.getTacticaltime());
        entity.setGrapplinghooktimeEntity(dto.getGrapplinghooktime());
        entity.setZiplinetimeEntity(dto.getZiplinetime());
        entity.setKills0Entity(dto.getKills0());
        entity.setKills1Entity(dto.getKills1());
        entity.setKills2Entity(dto.getKills2());
        entity.setKills3Entity(dto.getKills3());
        entity.setKills4Entity(dto.getKills4());
        entity.setKills5Entity(dto.getKills5());
        entity.setKills6Entity(dto.getKills6());
        entity.setKills7Entity(dto.getKills7());
        entity.setKills8Entity(dto.getKills8());
        entity.setKnifekillsEntity(dto.getKnifekills());
        entity.setC4killsEntity(dto.getC4kills());
        entity.setHandgrenadekillsEntity(dto.getHandgrenadekills());
        entity.setClaymorekillsEntity(dto.getClaymorekills());
        entity.setShockpadkillsEntity(dto.getShockpadkills());
        entity.setAtminekillsEntity(dto.getAtminekills());
        entity.setDeaths0Entity(dto.getDeaths0());
        entity.setDeaths1Entity(dto.getDeaths1());
        entity.setDeaths2Entity(dto.getDeaths2());
        entity.setDeaths3Entity(dto.getDeaths3());
        entity.setDeaths4Entity(dto.getDeaths4());
        entity.setDeaths5Entity(dto.getDeaths5());
        entity.setDeaths6Entity(dto.getDeaths6());
        entity.setDeaths7Entity(dto.getDeaths7());
        entity.setDeaths8Entity(dto.getDeaths8());
        entity.setKnifedeathsEntity(dto.getKnifedeaths());
        entity.setC4deathsEntity(dto.getC4deaths());
        entity.setHandgrenadedeathsEntity(dto.getHandgrenadedeaths());
        entity.setClaymoredeathsEntity(dto.getClaymoredeaths());
        entity.setShockpaddeathsEntity(dto.getShockpaddeaths());
        entity.setAtminedeathsEntity(dto.getAtminedeaths());
        entity.setZiplinedeathsEntity(dto.getZiplinedeaths());
        entity.setGrapplinghookdeathsEntity(dto.getGrapplinghookdeaths());
        entity.setTacticaldeployedEntity(dto.getTacticaldeployed());
        entity.setGrapplinghookdeployedEntity(dto.getGrapplinghookdeployed());
        entity.setZiplinedeployedEntity(dto.getZiplinedeployed());
        entity.setFired0Entity(dto.getFired0());
        entity.setFired1Entity(dto.getFired1());
        entity.setFired2Entity(dto.getFired2());
        entity.setFired3Entity(dto.getFired3());
        entity.setFired4Entity(dto.getFired4());
        entity.setFired5Entity(dto.getFired5());
        entity.setFired6Entity(dto.getFired6());
        entity.setFired7Entity(dto.getFired7());
        entity.setFired8Entity(dto.getFired8());
        entity.setKnifefiredEntity(dto.getKnifefired());
        entity.setC4firedEntity(dto.getC4fired());
        entity.setClaymorefiredEntity(dto.getClaymorefired());
        entity.setHandgrenadefiredEntity(dto.getHandgrenadefired());
        entity.setShockpadfiredEntity(dto.getShockpadfired());
        entity.setAtminefiredEntity(dto.getAtminefired());
        entity.setHit0Entity(dto.getHit0());
        entity.setHit1Entity(dto.getHit1());
        entity.setHit2Entity(dto.getHit2());
        entity.setHit3Entity(dto.getHit3());
        entity.setHit4Entity(dto.getHit4());
        entity.setHit5Entity(dto.getHit5());
        entity.setHit6Entity(dto.getHit6());
        entity.setHit7Entity(dto.getHit7());
        entity.setHit8Entity(dto.getHit8());
        entity.setKnifehitEntity(dto.getKnifehit());
        entity.setC4hitEntity(dto.getC4hit());
        entity.setClaymorehitEntity(dto.getClaymorehit());
        entity.setHandgrenadehitEntity(dto.getHandgrenadehit());
        entity.setShockpadhitEntity(dto.getShockpadhit());
        entity.setAtminehitEntity(dto.getAtminehit());
        return entity;
    }

}