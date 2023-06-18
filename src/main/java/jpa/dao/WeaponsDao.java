package jpa.dao;

import business.dto.Weapons;

import java.util.List;

public interface WeaponsDao {

    void saveOrUpdate(Weapons weapons);

    Weapons getWeapons(int id);

    List<Weapons> getAllWeapons();

    void deleteWeapons(Weapons weapons);

    void deleteWeapons(int id);

}