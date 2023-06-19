package lp.jpa.dao;

import lp.business.dto.PlayerHistory;

import java.util.List;

public interface PlayerHistoryDao {

    void saveOrUpdate(PlayerHistory playerHistory);

    PlayerHistory getPlayerHistory(int id);

    List<PlayerHistory> getAllPlayerHistory();

    void deletePlayerHistory(PlayerHistory playerHistory);

    void deletePlayerHistory(int id);

}