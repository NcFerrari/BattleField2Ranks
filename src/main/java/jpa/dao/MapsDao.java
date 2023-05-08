package jpa.dao;

import java.util.List;

public interface MapsDao<Maps> {

    void saveOrUpdate(Maps maps);

    Maps getMaps(int id);

    List<Maps> getAllMaps();

    void deleteMaps(Maps maps);

    void deleteMaps(int id);

}