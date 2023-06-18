package jpa.dao;

import business.dto.Maps;

import java.util.List;

public interface MapsDao {

    void saveOrUpdate(Maps maps);

    Maps getMaps(int id);

    List<Maps> getAllMaps();

    void deleteMaps(Maps maps);

    void deleteMaps(int id);

}