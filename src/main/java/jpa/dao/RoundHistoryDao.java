package jpa.dao;

import java.util.List;

public interface RoundHistoryDao<RoundHistory> {

    void saveOrUpdate(RoundHistory roundHistory);

    RoundHistory getRoundHistory(int id);

    List<RoundHistory> getAllRoundHistory();

    void deleteRoundHistory(RoundHistory roundHistory);

    void deleteRoundHistory(int id);

}