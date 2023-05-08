package jpa.dao;

import java.util.List;

public interface MapinfoDao<Mapinfo> {

    void saveOrUpdate(Mapinfo mapinfo);

    Mapinfo getMapinfo(int id);

    List<Mapinfo> getAllMapinfo();

    void deleteMapinfo(Mapinfo mapinfo);

    void deleteMapinfo(int id);

}