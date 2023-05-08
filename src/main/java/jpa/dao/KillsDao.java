package jpa.dao;

import java.util.List;

public interface KillsDao<Kills> {

    void saveOrUpdate(Kills kills);

    Kills getKills(int id);

    List<Kills> getAllKills();

    void deleteKills(Kills kills);

    void deleteKills(int id);

}