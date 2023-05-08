package jpa.dao;

import java.util.List;

public interface ServersDao<Servers> {

    void saveOrUpdate(Servers servers);

    Servers getServers(int id);

    List<Servers> getAllServers();

    void deleteServers(Servers servers);

    void deleteServers(int id);

}