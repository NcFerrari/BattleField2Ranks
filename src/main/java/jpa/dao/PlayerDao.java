package jpa.dao;

import java.util.List;

public interface PlayerDao<Player> {

    void saveOrUpdate(Player player);

    Player getPlayer(int id);

    List<Player> getAllPlayer();

    void deletePlayer(Player player);

    void deletePlayer(int id);

}