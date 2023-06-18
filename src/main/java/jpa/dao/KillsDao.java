package jpa.dao;

import business.dto.Kills;

import java.util.List;

public interface KillsDao {

    void saveOrUpdate(Kills kills);

    Kills getKills(int id);

    List<Kills> getAllKills();

    void deleteKills(Kills kills);

    void deleteKills(int id);

}