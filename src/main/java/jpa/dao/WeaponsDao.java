package jpa.dao;

import java.util.List;

public interface WeaponsDao<Weapons> {

    void saveOrUpdate(Weapons weapons);

    Weapons getWeapons(int id);

    List<Weapons> getAllWeapons();

    void deleteWeapons(Weapons weapons);

    void deleteWeapons(int id);

}