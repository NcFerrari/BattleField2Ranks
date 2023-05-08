package jpa.dao;

import java.util.List;

public interface AwardsDao<Awards> {

    void saveOrUpdate(Awards awards);

    Awards getAwards(int id);

    List<Awards> getAllAwards();

    void deleteAwards(Awards awards);

    void deleteAwards(int id);

}