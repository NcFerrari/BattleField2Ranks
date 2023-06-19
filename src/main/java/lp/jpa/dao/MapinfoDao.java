package lp.jpa.dao;

import lp.business.dto.Mapinfo;

import java.util.List;

public interface MapinfoDao {

    void saveOrUpdate(Mapinfo mapinfo);

    Mapinfo getMapinfo(int id);

    List<Mapinfo> getAllMapinfo();

    void deleteMapinfo(Mapinfo mapinfo);

    void deleteMapinfo(int id);

}