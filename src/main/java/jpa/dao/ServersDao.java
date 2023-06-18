package jpa.dao;

import business.dto.Servers;

import java.util.List;

public interface ServersDao {

    void saveOrUpdate(Servers servers);

    Servers getServers(int id);

    List<Servers> getAllServers();

    void deleteServers(Servers servers);

    void deleteServers(int id);

}