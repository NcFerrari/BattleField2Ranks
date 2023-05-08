package jpa.dao;

import java.util.List;

public interface ArmyDao<Army> {

    void saveOrUpdate(Army army);

    Army getArmy(int id);

    List<Army> getAllArmy();

    void deleteArmy(Army army);

    void deleteArmy(int id);

}