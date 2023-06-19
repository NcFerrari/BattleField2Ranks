package lp.jpa.dao;

import lp.business.dto.Player;

import java.util.List;

public interface PlayerDao {

    void saveOrUpdate(Player player);

    Player getPlayer(String name);

    Player getPlayer(int id);

    List<Player> getAllPlayer();

    void deletePlayer(Player player);

    void deletePlayer(int id);

}