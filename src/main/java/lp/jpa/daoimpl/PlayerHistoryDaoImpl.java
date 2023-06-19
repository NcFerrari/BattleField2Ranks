package lp.jpa.daoimpl;

import lp.business.dto.PlayerHistory;
import lp.jpa.EntityManager;
import lp.jpa.dao.PlayerHistoryDao;
import lp.jpa.entity.PlayerHistoryEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PlayerHistoryDaoImpl extends EntityManager implements PlayerHistoryDao {

    @Override
    public void saveOrUpdate(PlayerHistory playerHistory) {
        if (playerHistory == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(playerHistory));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public PlayerHistory getPlayerHistory(int id) {
        getSession().beginTransaction();
        PlayerHistoryEntity entity = getSession().get(PlayerHistoryEntity.class, id);
        getSession().getTransaction().commit();
        getSession().close();
        return mapEntityToDto(entity);
    }

    @Override
    public List<PlayerHistory> getAllPlayerHistory() {
        getSession().beginTransaction();
        @SuppressWarnings("unchecked")
        List<PlayerHistoryEntity> entities = getSession().createQuery("FROM PlayerHistoryEntity").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        List<PlayerHistory> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deletePlayerHistory(PlayerHistory playerHistory) {
        if (playerHistory == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(playerHistory));
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public void deletePlayerHistory(int id) {
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM PlayerHistoryEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();
    }

    private PlayerHistory mapEntityToDto(PlayerHistoryEntity entity) {
        if (entity == null) {
            return null;
        }
        PlayerHistory dto = new PlayerHistory();
        dto.setId(entity.getIdEntity());
        dto.setTimestamp(entity.getTimestampEntity());
        dto.setTime(entity.getTimeEntity());
        dto.setScore(entity.getScoreEntity());
        dto.setCmdscore(entity.getCmdscoreEntity());
        dto.setSkillscore(entity.getSkillscoreEntity());
        dto.setTeamscore(entity.getTeamscoreEntity());
        dto.setKills(entity.getKillsEntity());
        dto.setDeaths(entity.getDeathsEntity());
        dto.setRank(entity.getRankEntity());
        return dto;
    }

    private PlayerHistoryEntity mapDtoToEntity(PlayerHistory dto) {
        if (dto == null) {
            return null;
        }
        PlayerHistoryEntity entity = new PlayerHistoryEntity();
        entity.setIdEntity(dto.getId());
        entity.setTimestampEntity(dto.getTimestamp());
        entity.setTimeEntity(dto.getTime());
        entity.setScoreEntity(dto.getScore());
        entity.setCmdscoreEntity(dto.getCmdscore());
        entity.setSkillscoreEntity(dto.getSkillscore());
        entity.setTeamscoreEntity(dto.getTeamscore());
        entity.setKillsEntity(dto.getKills());
        entity.setDeathsEntity(dto.getDeaths());
        entity.setRankEntity(dto.getRank());
        return entity;
    }
}