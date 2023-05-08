package jpa.dao;

import java.util.List;

public interface UnlocksDao<Unlocks> {

    void saveOrUpdate(Unlocks unlocks);

    Unlocks getUnlocks(int id);

    List<Unlocks> getAllUnlocks();

    void deleteUnlocks(Unlocks unlocks);

    void deleteUnlocks(int id);

}