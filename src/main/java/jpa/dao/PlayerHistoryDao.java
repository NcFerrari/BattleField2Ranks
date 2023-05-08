package jpa.dao;

import java.util.List;

public interface PlayerHistoryDao<PlayerHistory> {

    void saveOrUpdate(PlayerHistory playerHistory);

    PlayerHistory getPlayerHistory(int id);

    List<PlayerHistory> getAllPlayerHistory();

    void deletePlayerHistory(PlayerHistory playerHistory);

    void deletePlayerHistory(int id);

}