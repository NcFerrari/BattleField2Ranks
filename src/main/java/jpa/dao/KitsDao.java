package jpa.dao;

import java.util.List;

public interface KitsDao<Kits> {

    void saveOrUpdate(Kits kits);

    Kits getKits(int id);

    List<Kits> getAllKits();

    void deleteKits(Kits kits);

    void deleteKits(int id);

}